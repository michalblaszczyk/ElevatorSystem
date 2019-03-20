package elevatorsimulator;

import java.util.Random;


public class ElevatorSimulator {

    public static void main(String[] args) {
        Random rand = new Random();
        
        ElevatorControlSystem elevatorControlSystem = new ElevatorControlSystem(4);
    
        
         elevatorControlSystem.pickUpRequest(6);
         elevatorControlSystem.pickUpRequest(2);
         elevatorControlSystem.pickUpRequest(4);
         elevatorControlSystem.pickUpRequest(1);
         elevatorControlSystem.pickUpRequest(7);
         elevatorControlSystem.pickUpRequest(1);
         
        System.out.println("REQUEST");
        for(Elevator elevator : elevatorControlSystem.getElevators()){
           
            System.out.println("Elevator " + (elevator.getId()+1) + " -> " + elevator.getTotalRequests());
        }

        System.out.println("================================");

        System.out.println("START SIMULATION");
        while(elevatorControlSystem.getActiveRequests() > 0){
            elevatorControlSystem.step();
            System.out.println("================================");
            for(Elevator elevator : elevatorControlSystem.getElevators()){                 
                System.out.println("Elevator " + (elevator.getId()+1) + ". -> Current Floor " + elevator.getCurrentFloor());
            }
        }
    }
    
}
