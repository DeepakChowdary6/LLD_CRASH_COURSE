package movie_ticket_booking_system;

import movie_ticket_booking_system.seat.Seat;
import movie_ticket_booking_system.seat.SeatStatus;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Show {

    private static int idcounter=0;
    private final Movie movie;
    private final Theatre theatre;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    private final Integer showid;
    private KeyLockMap<Integer, Seat> seats;
    public Show(Movie movie, Theatre theatre, LocalDateTime startTime, LocalDateTime endTime) {
        this.movie = movie;
        this.theatre = theatre;
        this.startTime = startTime;
        this.endTime = endTime;
        this.showid=idcounter++;

        Integer capacity=theatre.getCapacity();
        this.seats=new KeyLockMap<Integer, Seat>();
        for(int i=1;i<=capacity;i++){
            seats.put(i,new Seat(i, SeatStatus.AVAILABLE));
        }


    }

    public Movie getMovie() {
        return movie;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public KeyLockMap<Integer, Seat> getSeats() {
        return seats;
    }
}
