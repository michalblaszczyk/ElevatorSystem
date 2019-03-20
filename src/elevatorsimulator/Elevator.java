package elevatorsimulator;

import java.util.Comparator;
import java.util.TreeSet;


public class Elevator implements IElevator{
    private final Integer id;
    private Integer currentFloor;
    
    private TreeSet<Integer> upDestinationFloors; //sorted in ASC
    private TreeSet<Integer> downDestinationFloors;//sorted in DESC
    
    
    private ElevatorStatus elevatorStatus;
    ElevatorDirection direction;

    public Elevator(Integer currentFloor, Integer id) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.upDestinationFloors = new TreeSet<Integer>();
        this.downDestinationFloors = new TreeSet<Integer>(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        this.elevatorStatus = ElevatorStatus.STATIONARY;
        direction = ElevatorDirection.NONE;
    }
    
    public int getTotalRequests(){
        return upDestinationFloors.size() + downDestinationFloors.size();
    }
     
    public ElevatorStatus getElevatorStatus(){
        return this.elevatorStatus;
    }

    public int getId(){
        return this.id;
    }


    public boolean updateStatus(ElevatorStatus elevatorStatus){
        if(getTotalRequests() > 0){
            return false;
        }
        this.elevatorStatus = elevatorStatus;
        return true;
    }

    public int getNextDestionationFloor(){
        if(direction == ElevatorDirection.DOWN){
            return this.downDestinationFloors.first();
        }else if(direction == ElevatorDirection.UP){
            return this.upDestinationFloors.first();
        }else{
            return 0;
        }
    }

    public int getCurrentFloor(){
        return this.currentFloor;
    }

    public void addNewDestinatoin(Integer destination) {
        if(destination > currentFloor){
            upDestinationFloors.add(destination);
        }else{
            downDestinationFloors.add(destination);
        }
    }

    public boolean moveAndCheckIfServed() {
        direction();
        if(direction == ElevatorDirection.UP){
            if(upDestinationFloors.first() == currentFloor){
                return popUpDestionation();
            }else {
                currentFloor++;
            }
        }else if(direction == ElevatorDirection.DOWN){
            if(downDestinationFloors.first() == currentFloor){
                return popDownDestionation();
            }else {
                currentFloor--;
            }
        }else{
            //Do Nothing. Elevator is not moving.
        }
        return false;
    }

    public ElevatorDirection getDirection(){
        return this.direction;
    }

    private void direction() {
        if(direction == ElevatorDirection.NONE){
            if(upDestinationFloors.size() > 0 && downDestinationFloors.size() > 0){
                if(Math.abs(currentFloor - upDestinationFloors.first()) < Math.abs(currentFloor - downDestinationFloors.first())){
                    direction = ElevatorDirection.UP;
                }else{
                    direction = ElevatorDirection.DOWN;
                }
            }else if(upDestinationFloors.size() > 0){
                direction = ElevatorDirection.UP;
            }else if(downDestinationFloors.size() > 0){
                direction = ElevatorDirection.DOWN;
            }
        }
    }

    private boolean popUpDestionation() {
        upDestinationFloors.remove(upDestinationFloors.first());
        if (upDestinationFloors.size() == 0) {
            direction = ElevatorDirection.NONE;
        }
        return true;
    }

    private boolean popDownDestionation() {
        downDestinationFloors.remove(downDestinationFloors.first());
        if(downDestinationFloors.size() == 0){
            direction = ElevatorDirection.NONE;
        }
        return true;
    }

   
}
