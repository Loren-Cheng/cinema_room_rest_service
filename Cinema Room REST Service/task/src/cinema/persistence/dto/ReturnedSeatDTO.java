package cinema.persistence.dto;

import cinema.entity.Seat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
public class ReturnedSeatDTO {
    Seat returned_ticket;

    @Override
    public String toString() {
        return "ReturnedSeatDTO{" +
                "returned_ticket=" + returned_ticket +
                '}';
    }
}
