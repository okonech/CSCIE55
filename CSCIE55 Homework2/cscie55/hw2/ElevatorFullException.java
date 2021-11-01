package cscie55.hw2;

/** {@code ElevatorFullException} is an exception thrown when elevator capacity is reached
 *
 *  @author Alex Okonechnikov
 *  @version 1.1
 *  @since March 18, 2015
 */

@SuppressWarnings("serial")
public class ElevatorFullException extends java.lang.Exception {

    /**
     * Constructor for ElevatorFullException with string
     */
    ElevatorFullException(String msg) {
         super("ElevatorFullException: " + msg);
    }
}
