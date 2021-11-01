package cscie55.hw6.bank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import cscie55.hw6.bank.command.Command;

/** {@code CommandExecutionThread} is an extension of Thread
 *
 *  @author Alex Okonechnikov
 *  @version 1.0
 *  @since May 18, 2015
 */

public class CommandExecutionThread extends Thread{
    /** 
     * bank is the Bank which contains the Accounts for the thread to execute commands on
     * socketInput is a BufferedReader for commands this thread reads from the network
     * socketOutput is a PrintWriter for commands this thread writes to the network
     */
    private final Bank bank;
    private final BufferedReader socketInput;
    private final PrintWriter socketOutput;

    /**
     * Constructor for CommandExecutionThread
     * @param bank, the Bank for the BankServer to execute commands on
     * @param socket, used to set up input and output streams
     * @throws IOException if can't set up socket streams
     */
    public CommandExecutionThread(Bank bank, Socket socket) throws IOException {
        this.bank=bank;
        this.socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.socketOutput =  new PrintWriter(socket.getOutputStream(),true);
    }

    /**
     * Execute the command read from the network and write a response
     * This will run until the client thread closes the socket (or a different IO error)
     * The caught bank exceptions are sent as output to the client, this is needed as client blocks until a response is received
     */
    public void run() {
        String commandString;
        try {
            // run until client disconnects
            while ((commandString=socketInput.readLine()) != null){
                try {
                    // parse and execute read command
                    Command command = Command.parse(commandString);
                    String output = command.execute(bank);
                    socketOutput.println(output);
                    System.out.println("Command: "+command.asString()+"      Returning: '"+output+"'");
                } catch (InsufficientFundsException e) {
                    socketOutput.println(e.getMessage());
                    System.out.println(e.getMessage());
                } catch (DuplicateAccountException e) {
                    socketOutput.println(e.getMessage());
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            // handles all exceptions including SocketExcerption
            // this handles the client disconnection
        }
    }
}
