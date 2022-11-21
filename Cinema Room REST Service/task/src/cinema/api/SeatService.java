package cinema.api;

import cinema.entity.Room;
import cinema.entity.Seat;
import cinema.persistence.dto.BookedSeatDTO;
import cinema.persistence.dto.ReturnedSeatDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface SeatService {
    List<Seat> setSeatsInRoom(Room room);
    BookedSeatDTO bookSeat(Seat requestSeat);
    ReturnedSeatDTO returnSeat(Object requestReturnToken) throws JsonProcessingException;
}
