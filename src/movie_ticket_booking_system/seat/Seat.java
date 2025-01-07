package movie_ticket_booking_system.seat;

public class Seat {

    private final Integer seatNo;
    private SeatStatus status;

    public Seat(Integer seatNo, SeatStatus status) {
        this.seatNo = seatNo;
        this.status = status;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }
}
