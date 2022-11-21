package cinema.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "seat")
@Getter
@Setter
@NoArgsConstructor
public class Seat {
    @Id
    @Column
    @JsonIgnore
    private long id;

    @JsonProperty
    @Column(name = "row_")
    private int row;
    @JsonProperty
    @Column(name = "column_")
    private int column;

    @JsonProperty
    @Column
    private int price;

    @Column
    @JsonIgnore
    private boolean isAvailable;

    @Column
    @JsonIgnore
    private String token;

    @ManyToOne
    @JsonBackReference
    private Room room;

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Seat(long id, int row, int column, String token, Room room) {
        this.id = id;
        this.row = row;
        this.column = column;
        this.token = token;
        this.room = room;
    }

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Seat(int row, int column,int price, boolean isAvailable, String token) {
        this.row = row;
        this.column = column;
        this.price = price;
        this.isAvailable = isAvailable;
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Seat seat = (Seat) o;

        if (id != seat.id) return false;
        if (row != seat.row) return false;
        if (column != seat.column) return false;
        if (price != seat.price) return false;
        if (isAvailable != seat.isAvailable) return false;
        if (!token.equals(seat.token)) return false;
        return room.equals(seat.room);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + row;
        result = 31 * result + column;
        result = 31 * result + price;
        result = 31 * result + (isAvailable ? 1 : 0);
        result = 31 * result + token.hashCode();
        result = 31 * result + room.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", row=" + row +
                ", column=" + column +
                ", price=" + price +
                ", room=" + room +
                '}';
    }
}
