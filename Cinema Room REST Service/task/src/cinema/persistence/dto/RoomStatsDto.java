package cinema.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
public class RoomStatsDto {
    private int current_income;
    private int number_of_available_seats;
    private int number_of_purchased_tickets;

    @Override
    public String toString() {
        return "RoomStatsDto{" +
                "current_income=" + current_income +
                ", number_of_available_seats=" + number_of_available_seats +
                ", number_of_purchased_tickets=" + number_of_purchased_tickets +
                '}';
    }
}
