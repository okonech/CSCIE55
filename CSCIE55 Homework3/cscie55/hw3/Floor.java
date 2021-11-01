package cscie55.hw3;

import java.util.LinkedHashSet;
import java.util.Set;

/** {@code Floor} is a class used to track passengers on a specific floor
 *
 *  @author Alex Okonechnikov
 *  @version 1.1
 *  @since April 7, 2015
 */

public class Floor {
    /** 
     * Floor object contents.
     * floorNumber is the current floor
     * building is the Building containing this Floor, used to communicate with Elevator (not needed)
     * residentPassengers is a LinkedHashSet of passengers that are residents (don't want to move from this floor)
     * waitingUpPassengers is a LinkedHashSet of passengers wanting to move up from this floor
     * waitingDownPassengers is a LinkedHashSet of passengers wanting to move down from this floor
     */
    
    private final int floorNumber;
    private final Building building;
    private Set<Passenger> residentPassengers;
    private Set<Passenger> waitingUpPassengers;
    private Set<Passenger> waitingDownPassengers;
    
    
    /**
     * @param building, Building object containing this floor
     * @param floorNumber, integer of this floor's floor number
     * Constructor for Floor with building it's in and floor # it represents
     */
    public Floor(Building building, int floorNumber){
        this.floorNumber=floorNumber;
        this.building=building;
        residentPassengers = new LinkedHashSet<Passenger>();
        waitingUpPassengers = new LinkedHashSet<Passenger>();
        waitingDownPassengers = new LinkedHashSet<Passenger>();
    }
    
    /**
     * @param passenger, Passenger object on this floor waiting for the elevator
     * @param destinationFloor, integer of the floor the passenger wants to go
     * Add a waiting passenger to this floor (or resident if going to this floor)
     */
    public void waitForElevator(Passenger passenger, int destinationFloor) {
        passenger.waitForElevator(destinationFloor); // set passenger's destination floor
        if (floorNumber > destinationFloor) {  // passenger going down
            waitingDownPassengers.add(passenger);
            // remove passenger from resident list (if passenger was a resident)
            residentPassengers.remove(passenger);
        } else if (floorNumber < destinationFloor) { // passenger going up
            waitingUpPassengers.add(passenger);
            // remove passenger from resident list (if passenger was a resident)
            residentPassengers.remove(passenger);
        } else { // passenger going to this floor (treat as resident of this floor)
            passenger.arrive();
            residentPassengers.add(passenger);
        }
    }
    
    /**
     * @param Passenger object that wanted to arrive on this floor and is now a resident
     * This extra method is cleaner than adding residents through waitForElevator
     * 
     * Due to the restriction on additional public methods, this is protected instead.
     */
    protected void addResidentPassenger(Passenger passenger){
        // set destination floor to this floor (will become current floor when arrive() is called)
        passenger.waitForElevator(floorNumber);
        // set current floor to this floor, and destination floor to undefined
        passenger.arrive();
        // add passenger as resident of this floor
        residentPassengers.add(passenger);
    }
    
    /**
     * @return Set of Passenger objects waiting to go up on elevator
     * These patients will only board an elevator moving up
     * 
     * Due to the restriction on additional public methods, this is protected instead.
     */
    protected Set<Passenger> getWaitingUpPassengers() {
        return waitingUpPassengers;
    }
    
    /**
     * @param Set of Passenger objects waiting to go up on elevator
     * These patients will only board an elevator moving up
     * 
     * Due to the restriction on additional public methods, this is protected instead.
     */
    protected void setWaitingUpPassengers(Set<Passenger> waitingUpPassengers) {
        this.waitingUpPassengers=waitingUpPassengers;
    }
    
    /**
     * @return Set of Passenger objects waiting to go down on elevator
     * These patients will only board an elevator moving down
     * 
     * Due to the restriction on additional public methods, this is protected instead.
     */
    protected Set<Passenger> getWaitingDownPassengers() {
        return waitingDownPassengers;
    }
    
    /**
     * @param Set of Passenger objects waiting to go down on elevator
     * These patients will only board an elevator moving down
     * 
     * Due to the restriction on additional public methods, this is protected instead.
     */
    protected void setWaitingDownPassengers(Set<Passenger> waitingDownPassengers) {
        this.waitingDownPassengers=waitingDownPassengers;
    }
    
    /**
     * @param passenger, Passenger object
     * @return True if the passenger is a resident of the floor (not waiting to go up and not waiting to go down)
     */
    public boolean isResident(Passenger passenger) {
        return residentPassengers.contains(passenger);
    }
    
    /**
     * Adds the passenger to floor as a resident
     * Intended to only be called on ground floor
     */
    void enterGroundFloor(Passenger passenger) {
        residentPassengers.add(passenger);
    }
    
    /**
     * Used for debugging
     * @return string of floor number and current floor's residents
     */
    public String toString() {
        return "Floor:" + floorNumber + " Residents: " + residentPassengers.toString();
    }
}
