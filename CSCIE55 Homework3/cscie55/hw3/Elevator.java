package cscie55.hw3;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import cscie55.hw3.Building;


/** {@code Elevator} is a class used to move and track passengers
 *  bound for the floor of a building
 *
 *  @author Alex Okonechnikov
 *  @version 1.3
 *  @since April 6, 2015
 */

public class Elevator {
    /** 
     * Elevator object contents.
     * floor is the current floor the elevator is on. Bound = [1,FLOORS]
     * direction is the elevator's current direction, up = true, down = false.
     * passengersToFloors counts how many passengers are bound for a floor.
     * building is the Building containing the elevator, used to communicate with Floors
     * passengers is a LinkedHashSet of Passenger objects currently inside the elevator
     * 
     * Note: The 0 offset is not used for floorsToStop or for passengersToFloors.
     *
     */
    public static final int CAPACITY = 10;
    private int floor;
    private boolean direction;
    private ArrayList<LinkedHashSet<Passenger>> passengersToFloors;
    private Building building;

    /**
     * @param building, Building in which the elevator exists
     * Constructor for Elevator with building argument
     */
    public Elevator(Building building) {
        floor = 1;
        direction = true;
        this.building=building;
        // initialize ArrayList of LinkedHashSets of Passengers
        passengersToFloors = new ArrayList<LinkedHashSet<Passenger>>(Building.FLOORS+1);
        // add a LinkedHashSet to each slot of ArrayList (and extra LinkedHashSet for position 0 (unused))
        for (int i = 1; i <= Building.FLOORS+1; i++) {
            passengersToFloors.add(new LinkedHashSet<Passenger>());
        }
    }

    /**
     * @return String of combined current floor and count of current passengers
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "Floor " + floor + ": " + passengers().size()+ " passengers";
    }

    /**
     * Board a passenger going to the given floor by adding to 
     * ArrayList of LinkedHashSets of Passengers (index = passenger's destination floor), element = the passenger
     * @param passenger, the Passenger object to board
     * @throws ElevatorFullException
     * If elevator is full, throw ElevatorFullException
     */
    public void boardPassenger(Passenger passenger) throws ElevatorFullException {
        if(passengers().size()>= CAPACITY){
            throw new ElevatorFullException("Elevator is full");
        } else {
            // add passengers by destination floor
            passengersToFloors.get(passenger.destinationFloor()).add(passenger);
            // set current floor as undefined, passenger is on elevator
            passenger.boardElevator();
        }
    }

    /**
     * This method handles all movement and elevator actions.
     * Move a single floor in a direction determined by the elevator state.
     * Switch direction if top or bottom floor is reached
     * Reset stop flag for the new floor, now that it has been reached
     * Release any passengers going to the new floor
     */
    public void move() {
        // move up or down one floor depending on direction
        moveFloor();
        // switch direction if necessary
        determineDirection();
        // board as many passengers as capacity allows
        // do nothing if no stop is required
        board();
        // release passengers going to current floor (set as residents on floor)
        releasePassengers();
    }

    /**
     * @return Set of Passenger objects currently in the elevator
     * Loops though ArrayList of LinkedHashSets (indexed by destination floor of passengers)
     * and adds each passenger to the output 
     */
    public Set<Passenger> passengers() {
        Set<Passenger> passengers = new LinkedHashSet<Passenger>();
        for (LinkedHashSet<Passenger> i : passengersToFloors) {
            passengers.addAll(i);
        }
        return passengers;
    }
    
    /**
     * @return current floor of elevator
     */
    public int currentFloor() {
        return floor;
    }

    /**
     * Changes the direction if top or bottom floor is reached
     */
    private void determineDirection() {
        if (Building.FLOORS == floor) {
            direction = false;
        } else if (floor == 1) {
            direction = true;
        }
    }

    /**
     * Move a single floor in a direction determined by the elevator state.
     * If the current direction is true (up) move a floor up.
     * If the current direction is false (down) move a floor down.
     */
    private void moveFloor() {
        if (goingUp()) {
            floor++;
        } else {
            floor--;
        }
    }
    
    /**
     * Board passengers (if any) from current floor going in the current direction
     *
     */
    private void board() {
        Set<Passenger> remainingPassengers;
        if (goingUp()) { // board passengers going up
            remainingPassengers=boardByDirection(building.floor(floor).getWaitingUpPassengers());
            // update current Floor's remaining set of passengers waiting to go up
            building.floor(floor).setWaitingUpPassengers(remainingPassengers);
        } else { // board passengers going down
            remainingPassengers=boardByDirection(building.floor(floor).getWaitingDownPassengers());
            // update current Floor's remaining set of passengers waiting to go down
            building.floor(floor).setWaitingDownPassengers(remainingPassengers);
        }
    }
    
    /**
    * @param Set of passengers waiting to board the elevator, for it's current direction
    * @return Set of waiting passengers remaining after done boarding
    * 
    * Board passengers trying to go in elevator's current direction until capacity is reached
    * or no passengers trying to board remain. Update the Floor's Set of Passengers when finished
    */
    private Set<Passenger> boardByDirection(Set<Passenger> waitingPassengers) {
        while ((waitingPassengers.size() > 0) && (passengers().size() < CAPACITY)) {
            try {
                // get first passenger in list and try to board
                Passenger boardingPassenger = waitingPassengers.iterator().next();
                boardPassenger(boardingPassenger);
                // if boarded, delete this passenger from waitingPassengers
                waitingPassengers.remove(boardingPassenger);
            } catch (ElevatorFullException e) {
                System.out.println(e);
            }
        }
        return waitingPassengers;
    }
    
    /**
     * @return true if elevator is going up, else false
     */
    public boolean goingUp() {
        return direction;
    }
    
    /**
     * @return true if elevator is going down, else false
     */
    public boolean goingDown() {
        return !direction;
    }
    
    /**
     * Release passengers that were bound for current floor
     * Set as residents of current floor and mark as arrived
     *
     */
    private void releasePassengers() {
        for (Passenger passenger : passengersToFloors.get(floor)) {
            // set passenger as resident on current floor
            building.floor(floor).addResidentPassenger(passenger);
        }
        // clear all passengers from Set of passengers bound for this floor
        passengersToFloors.get(floor).clear();
    }
    
}
