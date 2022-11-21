package cinema.persistence.dao;

import cinema.entity.Room;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Synchronized;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@ToString
public class RoomRepository extends Thread {

    private static Room room;


    @Synchronized
    public static Room getRoom() {
        return room;
    }

    @Synchronized
    public static void setRoom(Room room) {
        RoomRepository.room = room;
    }
}
