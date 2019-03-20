
package elevatorsimulator;



public class ElevatorControlSystem implements IElevatorControlSystem{
    
    private Elevator[] elevators;
    private final int elevatorCount;
    
    private static final int MAX_ELEVATORS = 16;
    private int activeRequests;


    public ElevatorControlSystem(int elevatorCount){
        this.elevatorCount = elevatorCount > MAX_ELEVATORS ? MAX_ELEVATORS : elevatorCount;
        this.elevators = new Elevator[elevatorCount];
        for(int i = 0; i < elevatorCount; i++){
            elevators[i] = new Elevator(0, i);
        }
    }

    public Elevator[] getElevators(){
        return elevators;
    }

    public int getActiveRequests(){
        return activeRequests;
    }

    
    public boolean updateStatus(ElevatorStatus elevatorStatus, int elevatorId) {
        if(elevatorId < 0 || elevatorId > elevatorCount-1){
            return false;
        }
        return elevators[elevatorId].updateStatus(elevatorStatus);
    }

    public boolean pickUpRequest(int fromFloor) {
        int maxRequestPerElevator = activeRequests/elevatorCount+1;
        int minUp = Integer.MAX_VALUE;
        Elevator minUpElevator = null;
        int minDown = Integer.MAX_VALUE;
        Elevator minDownElevator = null;
        for(Elevator elevator : elevators){
            if(elevator.getElevatorStatus() == ElevatorStatus.MAINTAINANCE || elevator.getTotalRequests() >= maxRequestPerElevator){
                continue;
            }
            if((elevator.getDirection() == ElevatorDirection.UP
                    || elevator.getDirection() == ElevatorDirection.NONE)
                    && elevator.getCurrentFloor() < fromFloor){
                if(minUp > fromFloor - elevator.getCurrentFloor()){
                    minUp = fromFloor - elevator.getCurrentFloor();
                    minUpElevator = elevator;
                }
            }else if((elevator.getDirection() == ElevatorDirection.DOWN
                    || elevator.getDirection() == ElevatorDirection.NONE)
                    && elevator.getCurrentFloor() > fromFloor){
                if(minDown > elevator.getCurrentFloor() - fromFloor){
                    minDown = elevator.getCurrentFloor() - fromFloor;
                    minDownElevator = elevator;
                }
            }
        }
        
        //If we found 2 elevators in both up and down direction. Assign the pickUp requests to closest elevator.
        if(minDownElevator != null && minUpElevator != null){
            if(minDown < minUp){
                minDownElevator.addNewDestinatoin(fromFloor);
            }else{
                minUpElevator.addNewDestinatoin(fromFloor);
            }
        }
        
       // If we found only 1 closest elevator in down direction. Assign the pickUp requests to it.
        else if(minDownElevator != null){
            minDownElevator.addNewDestinatoin(fromFloor);
        }
        
       // If we found only 1 closest elevator in up direction. Assign the pickUp requests to it.
        else if(minUpElevator != null){
            minUpElevator.addNewDestinatoin(fromFloor);
        }
        
        else{
            return false;
        }

        activeRequests++;
        return true;
    }

    public void step() {
        //Loop though every elevator and call move
        for (Elevator currElevator : elevators) {
            if(currElevator.moveAndCheckIfServed()){
                activeRequests--;
            }
        }
    }

 
}
