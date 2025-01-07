package movie_ticket_booking_system;


import java.time.Duration;

public class Movie {
    private final String name;
    private final Integer movieid;
    private final Integer duration;

    public Movie(String name, Integer movieid, Integer duration) {
        this.name = name;
        this.movieid = movieid;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public Integer getMovieid() {
        return movieid;
    }

    public Integer getDuration() {
        return duration;
    }
}
