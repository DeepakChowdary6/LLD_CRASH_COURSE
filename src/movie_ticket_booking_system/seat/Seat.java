package movie_ticket_booking_system.seat;

import java.util.concurrent.locks.ReentrantLock;

public class Seat {

    private final Integer seatNo; // Immutable field
    private SeatStatus status;

    private final ReentrantLock lock = new ReentrantLock();

    // Constructor
    public Seat(Integer seatNo, SeatStatus status) {
        this.seatNo = seatNo;
        this.status = status;
    }

    // Getter with tryLock logic
    public SeatStatus getStatus() throws Exception {
        if (lock.tryLock()) { // Try to acquire the lock
            try {
                return status; // Perform read operation
            } finally {
                lock.unlock(); // Release lock
            }
        } else {
            throw new Exception("Seat status is currently locked. Please wait for some time.");
        }
    }

    // Setter with tryLock logic
    public void setStatus(SeatStatus status) throws Exception {
        if (lock.tryLock()) { // Try to acquire the lock
            try {
                this.status = status; // Perform write operation
            } finally {
                lock.unlock(); // Release lock
            }
        } else {
            throw new Exception("Seat status is currently locked. Please wait for some time.");
        }
    }

    // Getter for seat number (no lock required since seatNo is immutable)
    public Integer getSeatNo() {
        return seatNo;
    }
}
