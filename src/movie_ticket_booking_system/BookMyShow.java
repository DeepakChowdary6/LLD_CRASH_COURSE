package movie_ticket_booking_system;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookMyShow {
    public static Map<String, List<Show>> movieMap=new HashMap<>();



    public static void generateMovieMap(List<Theatre>theatreList){

        for(Theatre theatre:theatreList){

            for(Show show:theatre.getShows()){
                String movieName=show.getMovie().getName();
                if(movieMap.containsKey(movieName)){
                    movieMap.get(movieName).add(show);
                }else{

                    movieMap.put(movieName,new ArrayList<>());
                    movieMap.get(movieName).add(show);
                }

            }

        }

    }

    public static List<Show> searchMovieMap(String cinema) throws Exception{

        if(movieMap.containsKey(cinema)){
            return movieMap.get(cinema);
        }else{
            throw new Exception("No shows ");
        }

    }





    public static void main(String[] args) throws Exception {

        User user1=new User("Seshu");
        User user2=new User("Vinod");

        Theatre theatre1=new Theatre("sandya","Hyderabad",50);
        Theatre theatre2=new Theatre("prasads","Hyderabad",70);

        Movie movie1=new Movie("Avatar",2,123);
        Movie movie2=new Movie("Avengers",1,140);

        Show show1=new Show(movie1,theatre1, LocalDateTime.now(),LocalDateTime.now().plusMinutes(movie1.getDuration()));
        Show show2=new Show(movie1,theatre1, LocalDateTime.now().plusMinutes(180),LocalDateTime.now().plusMinutes(180+movie1.getDuration()));


        Show show3=new Show(movie2,theatre2, LocalDateTime.now(),LocalDateTime.now().plusMinutes(movie1.getDuration()));
        Show show4=new Show(movie2,theatre2, LocalDateTime.now().plusMinutes(180),LocalDateTime.now().plusMinutes(180+movie1.getDuration()));

        theatre1.addShow(show1);theatre1.addShow(show2);
        theatre2.addShow(show3);theatre2.addShow(show4);

        generateMovieMap(List.of(theatre1,theatre2));

        Booking booking=Booking.getBookingIstance();
         try {
             List<Show>avalableshows=searchMovieMap("Avatar");

         }catch (Exception e){
             e.printStackTrace();
         }




     Thread thread1=new Thread(()->{
         try {
             Ticket ticket1= booking.bookTickets(show1,List.of(3,5),user1,LocalDateTime.now());
         //    booking.cancelTicket(ticket1);
         }catch (Exception e){
             System.out.println(e.getMessage());
         }
     });

        Thread thread2=new Thread(()->{
            try {
                Ticket ticket2=booking.bookTickets(show1,List.of(3,5),user2,LocalDateTime.now());
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        });
        thread1.start();
        thread2.start();



//        thread2.join();
//        thread1.join();





    }

}
