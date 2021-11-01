package cscie55.hw3;

/** {@code Passenger} represents a person traveling to a floor in a building
 *
 *  @author Alex Okonechnikov
 *  @version 1.0
 *  @since April 6, 2015
 */

public class Passenger {
    
    /** 
     * Passenger object contents.
     * UNDEFINED_FLOOR is -1, an undefined floor
     * id is the identifier passed in (used to identify passengers in toString, and required by test code)
     * currentFloor is the current floor the passenger is on
     * destinationFloor is the floor the passenger wants to be on
     */
    private static final int UNDEFINED_FLOOR = -1;
    private int id;
    private int currentFloor;
    private int destinationFloor;
    
    /**
     * @param id, integer of this passenger's id number
     * Constructor for Passenger
     */
    public Passenger(int id) {
        this.id=id;
    }
    
    /**
     * @return The Passenger's current floor.
     * 
     */
    public int currentFloor() {
        return currentFloor;
    }
    
    /**
     * @return The Passenger's destination floor.
     * 
     */
    public int destinationFloor() {
        return destinationFloor;
    }
    
    /**
     * Sets the Passenger's destination floor to newDestinationFloor.
     * @param newDestinationFloor, integer of the floor the passenger wishes to move to
     */
    //Sets the Passenger's destination floor to newDestinationFloor.
    public void waitForElevator(int newDestinationFloor){ 
        destinationFloor=newDestinationFloor;
    }
    
    /**
     * Sets the Passenger's current floor to be undefined.
     */
    public void boardElevator() {
        currentFloor=UNDEFINED_FLOOR;
    }
    
    /**
     * The Passenger is on an elevator and arrives at his or her destination. 
     * Copy the value of the destination floor to the current floor, and set the destination floor to be undefined.
     */
    public void arrive() {
        currentFloor=destinationFloor;
        destinationFloor=UNDEFINED_FLOOR;
    }
    
    /**
     * Used for debugging
     * @return string of floor number and current floor's residents
     */
    public String toString() {
        return "Passenger #" + id + " CF: "+ currentFloor + " DF: " + destinationFloor;
    }
}
