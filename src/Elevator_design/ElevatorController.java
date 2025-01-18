package Elevator_design;

import java.util.ArrayList;
import java.util.List;

public class ElevatorController {

    private final List<Elevator> elevators;

    public ElevatorController(int noOfelevators,int capacity){
        elevators=new ArrayList<>();
        for(int i=0;i<noOfelevators;i++){
            Elevator elevator = new Elevator(i + 1, capacity);
            elevators.add(elevator);
            new Thread(()->{elevator.run();}).start();
        }

    }

    public void addRequest(int source,int destination){
       Elevator elevator=getOptimalElevator(source,destination);
        elevator.addRequest(new Request(source,destination));
    }

    public Elevator getOptimalElevator(int source,int destination){
        Elevator optimalElevator=null;
        int dist=Integer.MAX_VALUE;

        for(Elevator ele:elevators){
          //we can add extra logic here based on direction if destination is greater direction is UP
            //if current ele direction is down then inlcude in optimal elevator
            if(Math.abs(source-ele.getCurrentfloor())<dist){
                dist=Math.abs(source-ele.getCurrentfloor());
                optimalElevator=ele;
            }

        }
                return optimalElevator;

    }

}
