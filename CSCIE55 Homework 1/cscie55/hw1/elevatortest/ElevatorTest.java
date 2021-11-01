package cscie55.hw1.elevatortest;

import static cscie55.hw1.elevator.Elevator.MAX_FLOORS;
import cscie55.hw1.elevator.Elevator;

/** {@code ElevatorTest} is a class used to test the Elevator class
 *
 *  @author Alex Okonechnikov
 *  @version 1.0
 *  @since February 9, 2015
 */

public class ElevatorTest {
    public static void main(String[] args) {
        // initialize elevator
        Elevator elevator = new Elevator();
        // board passengers
        elevator.boardPassenger(3);
        elevator.boardPassenger(3);
        elevator.boardPassenger(5);
        // print initial state
        System.out.println(elevator);
        // move elevator up and down through all floors
        for (int i = 1; i < MAX_FLOORS*2-1; i++) {
            elevator.move();
            System.out.println(elevator);
        }
        
        String[] values = {"A",
                "B",
                "C"};
        String[] set = values;
        // add each possible replacement of "?" for the triple
        for (int i=0; i<values.length;i++) {
            for (int j=0; j<values.length-1;j++) {
                if(j==1){
                    set[i]="?";
                } else {
                    set[i]=values[i];
                }
                System.out.println((String.join(" ", set)) + " " + i + " " + j);   
            }
        }
        
    }
}
