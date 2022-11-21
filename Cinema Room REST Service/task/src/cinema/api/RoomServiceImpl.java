package cinema.api;

import cinema.entity.Room;
import cinema.entity.Seat;
import cinema.persistence.dao.RoomRepository;
import cinema.persistence.dto.RoomStatsDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("Room")
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {
    SeatService seatService;

    @Override
    public Room addANewMovieRoom(int totalRow, int totalColumn) {
        Room room = new Room();

        room.setTotal_rows(9);
        room.setTotal_columns(9);

        List<Seat> seats = seatService.setSeatsInRoom(room);
        room.setAvailable_seats(seats);

        RoomRepository.setRoom(room);

        return RoomRepository.getRoom();
    }

    @Override
    public Room listAvailableSeatsInRoom(Room room) {
        Room viewRoom = room;
        List<Seat> allSeats = room.getAvailable_seats();
        List<Seat> availableSeats = new ArrayList<>();

        for (int i = 0; i < allSeats.size(); i++) {
            Seat thisSeat = allSeats.get(i);
            if (thisSeat.isAvailable()) {
                availableSeats.add(thisSeat);
            }
        }

        viewRoom.setAvailable_seats(availableSeats);

        return viewRoom;
    }

    @Override
    public boolean isPasswordValid(String password) {
        if ("super_secret".equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public RoomStatsDto listStats() {
        Room thisRoom = RoomRepository.getRoom();
        List<Seat> availableSeats = thisRoom.getAvailable_seats();
        int income = 0;
        int count_avialable = 0;
        int count_purchased = 0;
        for (Seat seat:availableSeats
             ) {
            if(seat.isAvailable()) {
                count_avialable++;
            }else {
                count_purchased++;
                income += seat.getPrice();
            }
        }
        RoomStatsDto roomStatsDto = new RoomStatsDto(income,count_avialable,count_purchased);
        return roomStatsDto;
    }
}
