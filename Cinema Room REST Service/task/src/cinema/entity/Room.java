package cinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Room {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    private int total_rows;
    private int total_columns;

    @ManyToMany
    @JsonManagedReference
    private List<Seat> available_seats = new ArrayList<>();

    public Room(int total_rows, int total_columns) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }


    public List<Seat> getAvailable_seats() {
        return available_seats;
    }


    public void setAvailable_seats(List<Seat> available_seats) {
        this.available_seats = available_seats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (id != room.id) return false;
        if (total_rows != room.total_rows) return false;
        if (total_columns != room.total_columns) return false;
        return available_seats.equals(room.available_seats);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + total_rows;
        result = 31 * result + total_columns;
        result = 31 * result + available_seats.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "total_rows = " + total_rows + ", " +
                "total_columns = " + total_columns + ")";
    }
}
