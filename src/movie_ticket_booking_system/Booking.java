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
      public  synchronized Ticket  bookTickets(Show show, List<Integer> seats, User user, LocalDateTime bookingTime)  {
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
     public void cancelTicket(Ticket ticket){
            Show show=ticket.getShow();
         KeyLockMap<Integer,Seat>showSeats=show.getSeats();
           List<Integer>calcelledTickets=ticket.getSeats();
            for(Integer seat:calcelledTickets){
                  showSeats.get(seat).setStatus(SeatStatus.AVAILABLE);
            }
     }

     public boolean areSeatsAvalable(Show show,List<Integer>seats){
         KeyLockMap<Integer,Seat>showSeats=show.getSeats();
           for(Integer seat:seats){

                 if(showSeats.get(seat).getStatus()!= SeatStatus.AVAILABLE){
                       return false;
                 }
           }

           return true;
     }

      public void markSeatsWithPending(Show show,List<Integer>seats){
          KeyLockMap<Integer,Seat>showSeats=show.getSeats();
            for(Integer seat:seats){
                SeatStatus status = showSeats.get(seat).getStatus();
//                if (status == SeatStatus.PENDING || status == SeatStatus.BOOKED) {
//
//                    throw new IllegalStateException("Seat " + seat + " is already Occupied. Booking cannot proceed");
//
//                }
                  showSeats.get(seat).setStatus(SeatStatus.PENDING);
            }
            return ;
      }
    public void markSeatsWithBooked(Show show,List<Integer>seats){
        KeyLockMap<Integer,Seat>showSeats=show.getSeats();
        for(Integer seat:seats){
            showSeats.get(seat).setStatus(SeatStatus.BOOKED);
        }
        return ;
    }
      /* roll back the transactoin but here also there is a problem even if we rollback aslo original states might be changed by others
      * public void markSeatsWithPending(Show show,List<Integer>seats){
          Map<Integer, Seat> showSeats = show.getSeats();
          Map<Integer, SeatStatus> originalStatuses = new HashMap<>(); // To store original statuses

          try {
              for (Integer seat : seats) {
                  Seat seatObj = showSeats.get(seat);
                  SeatStatus status = seatObj.getStatus();

                  // Check if the seat is already occupied
                  if (status == SeatStatus.PENDING || status == SeatStatus.BOOKED) {
                      throw new IllegalStateException("Seat " + seat + " is already occupied. Booking cannot proceed.");
                  }

                  // Save the original status before making changes
                  originalStatuses.put(seat, status);

                  // Mark the seat as pending
                  seatObj.setStatus(SeatStatus.PENDING);
              }
          } catch (Exception e) {
              // Rollback changes if an exception occurs
              for (Map.Entry<Integer, SeatStatus> entry : originalStatuses.entrySet()) {
                  Integer seat = entry.getKey();
                  SeatStatus originalStatus = entry.getValue();
                  showSeats.get(seat).setStatus(originalStatus); // Revert to original status
              }
              // Rethrow the exception after rollback
              throw e;
          }
            return ;
      }
      * */





}
