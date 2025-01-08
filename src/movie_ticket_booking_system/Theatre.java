package movie_ticket_booking_system;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Theatre {
    private static AtomicInteger cnt = new AtomicInteger(0);

    private final String theatreName;
    private final String location;
    private List<Show>shows;

    private final Integer capacity;
    private final Integer theatreId;

    public Theatre(String theatreName, String location,Integer capacity) {
        this.theatreName = theatreName;
        this.location = location;
        this.shows = new ArrayList<>();
        this.capacity=capacity;
        this.theatreId = cnt.incrementAndGet();
    }

    public String getTheatreName() {
        return theatreName;
    }
    public void addShow(Show show){
        this.shows.add(show);
    }
    public String getLocation() {
        return location;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public List<Show> getShows() {
        return shows;
    }
}
