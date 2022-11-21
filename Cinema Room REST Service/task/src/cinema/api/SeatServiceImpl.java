package cinema.api;

import cinema.entity.Room;
import cinema.entity.Seat;
import cinema.exception.ExceptionInMovieRoom;
import cinema.persistence.dao.RoomRepository;
import cinema.persistence.dto.BookedSeatDTO;
import cinema.persistence.dto.ReturnedSeatDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("Seat")
public class SeatServiceImpl implements SeatService {
    @Override
    public List<Seat> setSeatsInRoom(Room room) {
        List<Seat> allSeats = new ArrayList<>();
        int rowsInRoom = room.getTotal_rows();
        int columnsInRoom = room.getTotal_columns();

        for (int i = 0; i < rowsInRoom; i++) {
            for (int j = 0; j < columnsInRoom; j++) {
                allSeats.add(new Seat(i + 1, j + 1, i <= 3 ? 10 : 8, true,""));
            }
        }

        room.setAvailable_seats(allSeats);

        return allSeats;
    }

    @Override
    public BookedSeatDTO bookSeat(Seat requestSeat) {
        Room thisRoom = RoomRepository.getRoom();
        List<Seat> availableSeats = thisRoom.getAvailable_seats();
        for (Seat thisSeat : availableSeats) {

            boolean isRowEqual = thisSeat.getRow() == requestSeat.getRow();
            boolean isColumnEqual = thisSeat.getColumn() == requestSeat.getColumn();

            if (isRowEqual && isColumnEqual
                    && thisSeat.isAvailable()) {
                thisSeat.setAvailable(false);//book this seat
                String token = UUID.randomUUID().toString();//create token
                thisSeat.setToken(token);
                thisRoom.setAvailable_seats(availableSeats);
                RoomRepository.setRoom(thisRoom);
                return new BookedSeatDTO(thisSeat.getToken(), thisSeat);
            } else if (isRowEqual && isColumnEqual &&
                    !thisSeat.isAvailable()) {
                throw new ExceptionInMovieRoom.SeatBeenBookedException();
            }
        }
        throw new ExceptionInMovieRoom.RowOrColumnOutOfBoundsException();
    }

    @Override
    public ReturnedSeatDTO returnSeat(Object requestReturnToken) throws JsonProcessingException {
        Room thisRoom = RoomRepository.getRoom();
        List<Seat> availableSeats = thisRoom.getAvailable_seats();
        ObjectMapper objectMapper = new ObjectMapper();
        String postAsString = objectMapper.writeValueAsString(requestReturnToken);

        postAsString = postAsString.replace("\":\"", "\"");
        String[] a = postAsString.split("\"");
        final String[] token = {""};
        for (int i = 0; i < a.length; i++) {
            if ("token".equals(a[i])) {
                token[0] = a[i + 1];
            }
        }

        for (int i = 0; i < availableSeats.size(); i++) {
            Seat thisSeat = availableSeats.get(i);
            String thisToken = thisSeat.getToken();
            boolean isTokenEqual = thisToken.equals(token[0]);
            if (isTokenEqual && !thisSeat.isAvailable()) {
                thisSeat.setAvailable(true);//release this seat
                thisSeat.setToken("");
                thisRoom.setAvailable_seats(availableSeats);
                RoomRepository.setRoom(thisRoom);
                return new ReturnedSeatDTO(thisSeat);
            }
        }
        throw new ExceptionInMovieRoom.WrongTokenException();
    }


}
