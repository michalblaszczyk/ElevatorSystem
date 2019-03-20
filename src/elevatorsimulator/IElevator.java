
package elevatorsimulator;


public interface IElevator {
   
    public ElevatorStatus getElevatorStatus(); // Get the elevator's status.
    public ElevatorDirection getDirection();
    
    public boolean updateStatus(ElevatorStatus elevatorStatus); //Update the status of elevator.
    public int getNextDestionationFloor(); //Get the next destination floor which will be served by elevator
    public int getCurrentFloor(); 
    public void addNewDestinatoin(Integer destination);
    public int getTotalRequests();

    public boolean moveAndCheckIfServed(); //moves the elevator 1 floor at a time based on pickUp
   
   
}
