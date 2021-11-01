package cscie55.hw5.bank;

import java.util.Queue;

import cscie55.hw5.bank.command.Command;

/** {@code CommandExecutionThread} is an extension of Thread
*
*  @author Alex Okonechnikov
*  @version 1.0
*  @since May 11, 2015
*/

public class CommandExecutionThread extends Thread{
    /** 
     * bank is the Bank which contains the Accounts for the thread to execute commands on
     * commandQueue is a Queue of Commands that threads will execute
     * executeCommandInsideMonitor is true if the command should execute within the synchronized block (monitor of commandQueue)
     */
    
    Bank bank;
    Queue<Command> commandQueue;
    boolean executeCommandInsideMonitor;
    
    /**
     * Constructor for CommandExecutionThread
     * @param bank, the Bank for the BankServer to execute commands on
     * @param commandQueue, the queue this thread will read
     * @param executeCommandInsideMonitor controls if commands are executed in a synchronized block
     */
    public CommandExecutionThread(Bank bank, Queue<Command> commandQueue, boolean executeCommandInsideMonitor){
        this.bank=bank;
        this.commandQueue = commandQueue;
        this.executeCommandInsideMonitor=executeCommandInsideMonitor;
    }
    
    /**
     * Execute the command read from the thread
     */
    public void run() {
        boolean shouldStop = false;
        try {
            while (shouldStop == false) {
                shouldStop = executeCommand();
            }
        } catch (InsufficientFundsException e) {
            // do nothing for insufficient funds exception
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Synchronize on commandQueue, wait until notified, remove a command and execute
     * If executeCommandInsideMonitor is true, execute within the synchronized block
     * @throws InterruptedException
     * @throws InsufficientFundsException
     */
    private boolean executeCommand() throws InsufficientFundsException, InterruptedException {
        Command command;
        synchronized (commandQueue) {
            while (commandQueue.isEmpty()) {
                commandQueue.wait();
            }
            command = commandQueue.remove();
            if (executeCommandInsideMonitor) {
                command.execute(bank);
            }
        }
        if (!executeCommandInsideMonitor) {
            command.execute(bank);
        }
        return command.isStop();
    }
}
