package movie_ticket_booking_system;

import movie_ticket_booking_system.seat.Seat;
import movie_ticket_booking_system.seat.SeatStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class Booking {

      private static Booking instance;
      public synchronized static Booking getBookingIstance(){
            if(instance==null){
                  instance =new Booking();
            }
            return instance;
      }
      //if we add synchronized here only one user at a time will use this app
      public  Ticket  bookTickets(Show show, List<Integer> seats, User user, LocalDateTime bookingTime)  throws Exception{
            if (areSeatsAvalable(show, seats)) {
                markSeatsWithPending(show, seats);
                  try {

                        Thread.sleep(2000); // Simulating delay for payment
                  } catch (Exception e) {
                        System.out.println(e.getMessage());

                  }
                markSeatsWithBooked(show, seats);
                  System.out.println("Payment done and Tickets booked for user : "+user.getName());
                  return new Ticket(user.getName(), bookingTime, show, seats);
            }else {
                throw new IllegalArgumentException("Tickets are not available for these seats,booking failed for the user :"+user.getName());
            }


      }
     public void cancelTicket(Ticket ticket) {
            Show show=ticket.getShow();
         Map<Integer,Seat>showSeats=show.getSeats();
       //  KeyLockMap<Integer,Seat>showSeats=show.getSeats();
           List<Integer>calcelledTickets=ticket.getSeats();
            for(Integer seat:calcelledTickets){
                try {
                    showSeats.get(seat).setStatus(SeatStatus.AVAILABLE);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

            }
     }

     public boolean areSeatsAvalable(Show show,List<Integer>seats) throws Exception{
         Map<Integer,Seat>showSeats=show.getSeats();
         //  KeyLockMap<Integer,Seat>showSeats=show.getSeats();
           for(Integer seat:seats){
                   if(showSeats.get(seat).getStatus()!= SeatStatus.AVAILABLE){
                       return false;
                   }
           }

           return true;
     }

      public void markSeatsWithPending(Show show,List<Integer>seats)throws Exception{
          Map<Integer,Seat>showSeats=show.getSeats();
          //  KeyLockMap<Integer,Seat>showSeats=show.getSeats();
            for(Integer seat:seats){
                    SeatStatus status = showSeats.get(seat).getStatus();
                    showSeats.get(seat).setStatus(SeatStatus.PENDING);

            }
            return ;
      }
    public void markSeatsWithBooked(Show show,List<Integer>seats)throws Exception{
        Map<Integer,Seat>showSeats=show.getSeats();
        //  KeyLockMap<Integer,Seat>showSeats=show.getSeats();
        for(Integer seat:seats){
                showSeats.get(seat).setStatus(SeatStatus.BOOKED);
        }
        return ;
    }





}
