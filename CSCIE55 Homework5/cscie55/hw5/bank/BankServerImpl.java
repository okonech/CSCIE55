package cscie55.hw5.bank;

import java.util.LinkedList;
import java.util.Queue;

import cscie55.hw5.bank.command.Command;
import cscie55.hw5.bank.command.CommandStop;

/** {@code BankServerImpl} is an implementation of the provided BankServer interface
*
*  @author Alex Okonechnikov
*  @version 1.0
*  @since May 11, 2015
*/

public class BankServerImpl implements BankServer {
    /** 
     * BankServerImpl class contents.
     * commandQueue is a Queue of Commands that threads will execute
     * commandThreads is an Array of threads that execute Commands
     * bank is the Bank which contains the Accounts for the threads to execute commands on
     * threads is the number of threads to start
     * executeCommandInsideMonitor is true if the command should execute within the synchronized block (monitor of commandQueue)
     */
    
    Queue<Command> commandQueue;
    CommandExecutionThread[] commandThreads;
    Bank bank;
    int threads;
    boolean executeCommandInsideMonitor;
    
    /**
     * Constructor for BankServerImpl, starts the given number of threads
     * @param bank, the Bank for the BankServer to execute commands on
     * @param threads, the number of threads to start
     * @param executeCommandInsideMonitor controls if commands are executed in a synchronized block
     */
    public BankServerImpl(Bank bank, int threads, boolean executeCommandInsideMonitor){
        this.bank=bank;
        this.threads=threads;
        this.executeCommandInsideMonitor=executeCommandInsideMonitor;
        this.commandQueue = new LinkedList<Command>();
        commandThreads = new CommandExecutionThread[threads];
        // build array of CommandExecutionThreads
        for (int i=0;i<threads;i++) {
            commandThreads[i] = new CommandExecutionThread(bank,commandQueue,executeCommandInsideMonitor);
        }
        // Start the command threads
        for (CommandExecutionThread thread : commandThreads) {
            thread.start();
        }
    }

    /**
     * Add the given command to the commandQueue
     * @param command, the command to add
     */
    public void execute(Command command) {
        synchronized (commandQueue) {
            commandQueue.add(command);
            //System.out.println(commandQueue);
            commandQueue.notifyAll();
        }
    }

    /**
     * Stop all BankServer threads by adding a stop request on the queue for each thread
     * Notifies all waiting threads, and waits for all threads to stop
     * @throws InterruptedException
     */
    public void stop() throws InterruptedException {
        // TODO Auto-generated method stub
        // add a stop command, 1 for each thread
        synchronized (commandQueue) {
            for (int i=0;i<threads;i++) {
                commandQueue.add(new CommandStop());
            }
            commandQueue.notifyAll();
        }
        // Wait for the threads to complete
        for (CommandExecutionThread thread : commandThreads) {
            thread.join();
        }
    }

    /**
     * Return the balance total of all accounts in the Bank
     * @return the total of all balances
     */
    public long totalBalances() {
        // TODO Auto-generated method stub
        return bank.totalBalances();
    }
}
