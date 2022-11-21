package cinema.controller;

import cinema.api.SeatService;
import cinema.entity.Seat;
import cinema.exception.ErrorMessage;
import cinema.exception.ExceptionInMovieRoom;
import cinema.persistence.dto.BookedSeatDTO;
import cinema.persistence.dto.ReturnedSeatDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class SeatController {
    SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PostMapping("/purchase")
    @ResponseBody
    public BookedSeatDTO bookSeat(@RequestBody Seat requestSeat) {
        return seatService.bookSeat(requestSeat);
    }

    @PostMapping("/return")
    @ResponseBody
    public ReturnedSeatDTO returnSeat(@RequestBody Object requestReturnToken) throws InterruptedException, JsonProcessingException {
        return seatService.returnSeat(requestReturnToken);
    }

    @ExceptionHandler({ExceptionInMovieRoom.SeatBeenBookedException.class, ExceptionInMovieRoom.RowOrColumnOutOfBoundsException.class, ExceptionInMovieRoom.WrongTokenException.class})
    public ResponseEntity<ErrorMessage> handleException(
            java.lang.Exception e) {
        ErrorMessage body = new ErrorMessage(e.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }


}
