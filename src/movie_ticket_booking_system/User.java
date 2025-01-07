package movie_ticket_booking_system;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User
{
    private final String name;
    private final UUID id;
    List<Ticket>userBookings;

    public User(String name) {
        this.name = name;
        this.id=UUID.randomUUID();
        userBookings=new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addBooking(Ticket ticket){
        userBookings.add(ticket);
    }

    public List<Ticket> getUserBookings() {
        return userBookings;
    }
}
