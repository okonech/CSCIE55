package cscie55.hw2;

/** {@code ElevatorTest} is a class used to test the Elevator class
 *
 *  @author Alex Okonechnikov
 *  @version 1.0
 *  @since February 9, 2015
 */

public class ElevatorTest {
    public static void main(String[] args) {
        // initialize elevator
        Building building = new Building();
        // board passengers
        try {
            building.elevator().boardPassenger(3);
            building.elevator().boardPassenger(3);
            building.elevator().boardPassenger(5);
        } catch (ElevatorFullException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // print initial state
        System.out.println(building.elevator());
        // move elevator up and down through all floors
        for (int i = 1; i < Building.FLOORS*2-1; i++) {
            building.elevator().move();
            System.out.println(building.elevator());
        }
    }
}
