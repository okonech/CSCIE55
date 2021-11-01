package cscie55.hw2;


/** {@code Building} is a class used to track floors and the elevator the building contains
 *
 *  @author Alex Okonechnikov
 *  @version 1.0
 *  @since February 28, 2015
 */


public class Building {
    /** 
     * Building object contents.
    * static integer FLOORS can be referenced in calling class.
    * the Elevator inside the building, used to transport passengers
    * an array of floors in the building ,each element is a Floor object
    */
    public static final int FLOORS=7;
    private Elevator elevator;
    private Floor[] floors;
    
    /**
     * Constructor for Building with no args
     * Creates elevator and floor objects for each floor
     */
    public Building() {
        // create elevator and pass reference to this building
        this.elevator = new Elevator(this);
        floors = new Floor[Building.FLOORS+1];
        // create floor objects for each floor
        for (int i = 1; i <= FLOORS; i++) {
            this.floors[i]= new Floor(this,i);
        }
    }
    
    /**
     * @return the building's Elevator object
     */
    public Elevator elevator() {
        return elevator;
    }
    
    /**
     * @param floor number of desired Floor object
     * @return Floor object for the given floor
     */
    public Floor floor(int floorNumber) {
        return floors[floorNumber];
    }
}
