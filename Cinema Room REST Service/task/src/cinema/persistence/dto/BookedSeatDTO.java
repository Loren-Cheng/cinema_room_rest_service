package cinema.persistence.dto;

import cinema.entity.Seat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
public class BookedSeatDTO {
    String token;
    Seat ticket;

    @Override
    public String toString() {
        return "BookedSeatDTO{" +
                "token='" + token + '\'' +
                ", ticket=" + ticket +
                '}';
    }
}
