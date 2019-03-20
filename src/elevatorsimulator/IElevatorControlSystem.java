/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevatorsimulator;

/**
 *
 * @author mblas
 */
public interface IElevatorControlSystem {
    
    public boolean updateStatus(ElevatorStatus elevatorStatus, int elevatorId); //update the status Elevator
    public boolean pickUpRequest(int fromFloor); //add a request to handle
    public void step(); // Calls moveAndCheckIfServed on all Elevators on the system.
    
}
