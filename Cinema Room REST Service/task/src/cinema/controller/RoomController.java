package cinema.controller;

import cinema.api.RoomService;
import cinema.entity.Room;
import cinema.exception.ErrorMessage;
import cinema.exception.ExceptionInMovieRoom;
import cinema.persistence.dao.RoomRepository;
import cinema.persistence.dto.RoomStatsDto;
import lombok.Synchronized;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class RoomController {
    RoomService roomService;

    RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/seats")
    @ResponseBody
    @Synchronized
    public Room listRoom() {
        Room thisRoom;
        if (RoomRepository.getRoom() != null) {
            thisRoom = RoomRepository.getRoom();

        } else {
            thisRoom = roomService.addANewMovieRoom(9, 9);
        }
        Room showRoom = roomService.listAvailableSeatsInRoom(thisRoom);
        return showRoom;
    }

    @PostMapping("/stats")
    @ResponseBody
    public RoomStatsDto getStats(String password){
        if(roomService.isPasswordValid(password)){
            return roomService.listStats();
        }
        throw new ExceptionInMovieRoom.WrongPasswordException();
    }

    @ExceptionHandler({ExceptionInMovieRoom.WrongPasswordException.class})
    public ResponseEntity<ErrorMessage> handleException(
            java.lang.Exception e) {
        ErrorMessage body = new ErrorMessage(e.getMessage());
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

}
