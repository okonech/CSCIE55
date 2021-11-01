package cscie55.hw2;


/** {@code Elevator} is a class used to move and track passengers
 *  bound for the floor of a building
 *
 *  @author Alex Okonechnikov
 *  @version 1.1
 *  @since February 28, 2015
 */

public class Elevator {
    /** 
     * Elevator object contents.
     * floor is the current floor the elevator is on. Bound = [1,FLOORS]
     * direction is the elevator's current direction, up = true, down = false.
     * floorsToStop denotes which floors require a stop.
     * passengersToFloors counts how many passengers are bound for a floor.
     * building is the Building containing the elevator, used to communicate with Floors
     * 
     * Note: The 0 offset is not used for floorsToStop or for passengersToFloors.
     *
     */
    public static final int CAPACITY = 10;
    private int floor;
    private boolean direction;
    private boolean[] floorsToStop;
    private int[] passengersToFloors;
    private Building building;

    /**
     * Constructor for Elevator with building argument
     */
    public Elevator(Building building) {
        floor = 1;
        direction = true;
        this.building=building;
        // set up arrays based on building's height
        floorsToStop = new boolean[Building.FLOORS+1];
        passengersToFloors = new int[Building.FLOORS+1];
    }

    /**
     * @return String of combined current floor and count of current passengers
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "Floor " + floor + ": " + passengers()+ " passengers";
    }

    /**
     * Board a passenger going to the given floor 
     * @param floor the floor to which the passenger is going
     * If elevator is full, throw ElevatorFullException
     */
    public void boardPassenger(int floor) throws ElevatorFullException {
        if(passengers()>= CAPACITY){
            throw new ElevatorFullException("Elevator is full");
        } else {
            floorsToStop[floor] = true;
            passengersToFloors[floor]++;  
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
        // switch direction if necessary
        determineDirection();
        // move up or down one floor depending on direction
        moveFloor();
        // board as many passengers as capacity allows
        // do nothing if no stop is required
        board();
        // release passengers going to current floor
        passengersToFloors[floor] = 0;
    }

    /**
     * @return count of current passengers
     */
    public int passengers() {
        int sum = 0;
        for (int i : passengersToFloors)
            sum += i;
        return sum;
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
        if (direction) {
            floor++;
        } else {
            floor--;
        }
    }
    
    /**
     * If a stop is required, board passengers (if any) from current floor
     * If no passengers remain to board, mark current floor as not requiring a stop
     */
    private void board() {
        if (floorsToStop[floor]){
            // elevator needs to stop, check floor to see if anyone is waiting to board
            // and board them up to max capacity
            while ((building.floor(floor).passengersWaiting() >0) && (passengers() < CAPACITY)) {
                building.floor(floor).boardPassenger();
                try {
                    boardPassenger(1);
                } catch (ElevatorFullException e) {
                    System.out.println(e);
                }
            }
            // if all passengers boarded, elevator no longer needs to stop at this floor
            if (building.floor(floor).passengersWaiting() == 0) {
                floorsToStop[floor] = false;
            }
        }   
    }
    
    /**
     @param floor number at which people are waiting to board the elevator
     * Mark a floor as requiring a stop
     */
    public void setStopFloor(int floorNUmber) {
        floorsToStop[floorNUmber] = true;
    }
}
