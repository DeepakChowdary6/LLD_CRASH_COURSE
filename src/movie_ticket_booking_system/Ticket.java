package movie_ticket_booking_system;

import movie_ticket_booking_system.seat.Seat;

import java.time.LocalDateTime;
import java.util.List;

public class Ticket {

    private final String ownerName;

    private final LocalDateTime bookingTime;
    private final Show show;
    private final List<Integer> seats;

    public Ticket(String ownerName, LocalDateTime bookingTime, Show show, List<Integer> seats) {
        this.ownerName = ownerName;
        this.bookingTime = bookingTime;
        this.show = show;
        this.seats = seats;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public Show getShow() {
        return show;
    }

    public List<Integer> getSeats() {
        return seats;
    }
}
