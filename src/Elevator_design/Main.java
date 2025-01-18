package Elevator_design;

public class Main {
    public static void main(String[] args) {
     ElevatorController elevatorController=new ElevatorController(4,6);

//        Scanner sc=new Scanner(System.in);
//        int src,dest;
//
     elevatorController.addRequest(2,5);
    try {
        Thread.sleep(3000);
    }catch (Exception e){
        e.printStackTrace();
    }
     elevatorController.addRequest(1,9);
        try {
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        elevatorController.addRequest(3,7);

    }
}

