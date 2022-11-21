package cinema.api;

import cinema.entity.Room;
import cinema.persistence.dto.RoomStatsDto;

public interface RoomService {
    Room addANewMovieRoom(int totalRow, int totalColumn);
    Room listAvailableSeatsInRoom(Room room);
    boolean isPasswordValid(String password);
    RoomStatsDto listStats();
}
