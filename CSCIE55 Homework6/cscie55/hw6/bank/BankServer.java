package cscie55.hw6.bank;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/** {@code BankServer} is the main class that runs the BankServer the junit tester client communicates with
*
*  @author Alex Okonechnikov
*  @version 1.0
*  @since May 18, 2015
*/

public class BankServer {
    /** 
     * main class, runs the BankServer.
     * Indefinitely runs a ServerSocket accepting connections.
     * When a socket is accepted, passes it off to a new CommandExecutionThread and continues listening.
     * This runs indefinitely because no stop command was in the requirements (If one was, a stop command would stop the server)
     * @param args is an unused array of args that could be passed in
     * @throws IOException in case of unforseen socket errors
     */
    public static void main(String[] args) throws IOException {
        // Create Bank
        Bank bank = new BankImpl();
        ServerSocket serverSocket = new ServerSocket(9090);
        System.out.println("Started new server process, waiting for command...");
        while (true) {
            // accept new connection socket and pass off to new thread
            Socket socket = serverSocket.accept();
            new CommandExecutionThread(bank,socket).start();
        }
    }
}
