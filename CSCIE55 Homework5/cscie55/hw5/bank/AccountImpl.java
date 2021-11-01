package cscie55.hw5.bank;

/** {@code AccountImpl} is an implementation of the provided Account interface
 *
 *  @author Alex Okonechnikov
 *  @version 1.0
 *  @since April 13, 2015
 */

public class AccountImpl implements Account {
    /** 
     * AccountImpl class contents.
     * id is an integer representing the Account's unique identifier
     * balance is a long representing the Account's balance
     */

    private int id;
    private long balance;
    
    /**
     * Constructor for AccountImpl, account is created with a balance of 0
     * @param id, the id of the account
     */
    public AccountImpl(int id) {
        this.id=id;
        balance=0;
    }

    /**
     * Return the the id of the account
     * @return the the id of the account
     */
    public int id() {
        // TODO Auto-generated method stub
        return id;
    }

    /**
     * Return the current balance of the account
     * @return the account's balance
     */
    public long balance() {
        // TODO Auto-generated method stub
        return balance;
    }

    /**
     * Deposit a set amount into the account, as long as the amount is greater than 0
     * @param amount, the amount to deposit into the account
     * @throws IllegalArgumentException the test case requires a deposit of less than 0 fails
     */
    public void deposit(long amount) throws IllegalArgumentException{
        if (amount <= 0) {
            throw new IllegalArgumentException();
        } else {
            balance+=amount;
        }
    }

    /**
     * Deposit a set amount into the account, as long as the amount is less than 0
     * @param amount, the amount to withdraw from the account
     * @throws IllegalArgumentException the test case requires a deposit of less than 0 fails
     * @throws InsufficientFundsException if the account's balance is less than the requested withdrawal amount
     */
    public void withdraw(long amount) throws InsufficientFundsException,IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        } else if(amount > balance){
            throw new InsufficientFundsException(this,amount);
        } else {
            balance-=amount;
        }
        
    }
    
    /**
     * Return the account represented as a string (id + balance)
     * @return a string representing the account
     */
    public String toString() {
        return "Id: " + id() + " Balance: " + balance;
    }

}
