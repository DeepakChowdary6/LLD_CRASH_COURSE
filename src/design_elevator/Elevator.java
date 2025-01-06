package design_elevator;

import java.util.ArrayList;
import java.util.List;

public class Elevator {

    private final int id;
    private final List<Request> requestList;
    private final int capacity;
    private int currentfloor;
    private Direction currDirection;

    public Elevator(int id,int capacity) {
        this.id=id;
        this.capacity = capacity;
        this.requestList=new ArrayList<>();
        this.currDirection=Direction.UP;
        this.currentfloor=1;
    }

    public synchronized void addRequest(Request request){

        if(requestList.size()<capacity){
            requestList.add(request);
            System.out.println("Request is added to the Elevator: "+id);
            notifyAll();
        }

    }

    public synchronized void processRequests(){
        while (true){
            while (requestList.isEmpty()){
                try {
                    wait();
                }
                catch (Exception e){
                   e.printStackTrace();
                }
            }
            Request request=requestList.remove(0);
            processRequest(request);

        }
    }

    public void processRequest(Request request){

        //can extend this code by taking only source in request class and destination from the user input
        // with many no of inputs with direction up and down with close button take 2 maps and find max of each direction
        //if the map the current floor is found in map open the door so that user leaves from lift check in both directions
        int startFloor=currentfloor;
        int endFloor=request.getDestination();
       if(endFloor>startFloor){
           currDirection=Direction.UP;
           for(int i=startFloor;i<=endFloor;i++){
               currentfloor=i;
               System.out.println("Lift with id "+this.id+" is at the floor : "+i);
               try {
                   Thread.sleep(1000);
               }catch (Exception e){
                   e.printStackTrace();
               }
           }
           System.out.println("Elevator "+this.id+" Destination Reached");
       }else if(endFloor<startFloor){
           currDirection=Direction.DOWN;
           for(int i=startFloor;i>=endFloor;i--){
               currentfloor=i;
               System.out.println("Lift with id "+this.id+" is at the floor : "+i);
               try {
                   Thread.sleep(1000);
               }catch (Exception e){
                   e.printStackTrace();
               }

           }
           System.out.println("Elevator "+this.id+" Destination Reached");
       }





    }



    void run(){
        processRequests();
    }

    public int getCurrentfloor() {
        return currentfloor;
    }
}
