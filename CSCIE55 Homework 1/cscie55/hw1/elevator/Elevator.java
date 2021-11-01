package cscie55.hw1.elevator;

/** {@code Elevator} is a class used to move and track passengers
 *  bound for the floor of a building
 *
 *  @author Alex Okonechnikov
 *  @version 1.0
 *  @since February 9, 2015
 */

public class Elevator {
    /** 
     * Elevator object contents.
     * static integer MAX_FLOORS can be referenced in calling class.
     * floor is the current floor the elevator is on. Bound = [1,MAX_FLOORS]
     * MAX_FLOORS defines the height of the building.
     * direction is the elevator's current direction, up = true, down = false.
     * floorsToStop denotes which floors require a stop.
     * passengersToFloors counts how many passengers are bound for a floor.
     * 
     * Note: The 0 offset is not used for floorsToStop or for passengersToFloors.
     *
     */
    public static final int MAX_FLOORS = 7;
    private int floor;
    private boolean direction;
    private boolean[] floorsToStop;
    private int[] passengersToFloors;

    /**
     * Constructor for Elevator with no args
     */
    public Elevator() {
        floor = 1;
        direction = true;
        floorsToStop = new boolean[MAX_FLOORS+1];
        passengersToFloors = new int[MAX_FLOORS+1];
    }

    /**
     * @return String of combined current floor and count of current passengers
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "Floor " + floor + ": " + passengerCount()+ " passengers";
    }

    /**
     * Board a passenger going to the given floor 
     * @param floor the floor to which the passenger is going
     */
    public void boardPassenger(int floor) {
        floorsToStop[floor] = true;
        passengersToFloors[floor]++;
    }

    /**
     * This method handles all movement and elevator actions.
     * Move a single floor in a direction determined by the elevator state.
     * Switch direction if top or bottom floor is reached
     * Reset stop flag for the new floor, now that it has been reached
     * Release any passengers going to the new floor
     */
    public void move() {
        // switch direction if necessary
        determineDirection();
        // move up or down one floor depending on direction
        moveFloor();
        // reset stop flag for the current floor
        floorsToStop[floor] = false;
        // release passengers on current floor
        passengersToFloors[floor] = 0;
    }

    /**
     * @return count of current passengers
     */
    private int passengerCount() {
        int sum = 0;
        for (int i : passengersToFloors)
            sum += i;
        return sum;
    }

    /**
     * Changes the direction if top or bottom floor is reached
     */
    private void determineDirection() {
        if (MAX_FLOORS == floor) {
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
        if (direction) {
            floor++;
        } else {
            floor--;
        }
    }
}
