package cscie55.hw2;

/** {@code Floor} is a class used to track passengers on a specific floor
 *
 *  @author Alex Okonechnikov
 *  @version 1.1
 *  @since February 28, 2015
 */

public class Floor {
    /** 
     * Floor object contents.
     * floorNumber is the current floor
     * building is the Building containing this Floor, used to communicate with Elevator
     * waitingToBoard denotes the number of passengers waiting on this floor
     *
     */
    
    private final int floorNumber;
    private final Building building;
    private int waitingToBoard;
    
    
    /**
     * Constructor for Floor with building it's in and floor # it represents
     */
    public Floor(Building building, int floorNumber){
        this.floorNumber=floorNumber;
        this.building=building;
    }
    
    /**
     * @return count of passengers waiting to be boarded on this floor
     */
    public int passengersWaiting() {
        return waitingToBoard;
    }
    
    /**
     * Add a waiting passenger to this floor (assumed to be going to ground floor)
     */
    public void waitForElevator() {
        waitingToBoard++;
        building.elevator().setStopFloor(floorNumber);
    }
    
    /**
     * Called to remove a waiting passenger from floor
     * Intended to be called from Elevator after a passenger was boarded.
     * Due to the restriction on additional public methods, this is protected instead.
     */
    protected void boardPassenger() {
        if (waitingToBoard > 0) {
            waitingToBoard--;
        }
    }
}
