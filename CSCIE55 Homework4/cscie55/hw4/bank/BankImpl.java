package cscie55.hw4.bank;

/** {@code BankImpl} is an implementation of the provided Bank interface
*
*  @author Alex Okonechnikov
*  @version 1.0
*  @since April 13, 2015
*/

import java.util.ArrayList;

public class BankImpl implements Bank{
    /** 
     * BankImpl class contents.
     * accounts is an ArrayList of Accounts indexed by the unique id of each Account object
     */
    private ArrayList<Account> accounts;

    /**
     * Constructor for BankImpl with no arguments
     */
    public BankImpl() {
        accounts = new ArrayList<Account>();
    }

    /**
     * Add an account to the bank, index in ArrayList of accounts
     * @param account, the account to add to the bank
     * @throws DuplicateAccountException if an account with an existing id is added
     */
    public void addAccount(Account account) throws DuplicateAccountException {
        if (accounts.contains(account)) {
            throw new DuplicateAccountException(account.id());
        } else {
            accounts.add(account);
        }

    }

    /**
     * Transfers an amount from one account to another, without locking
     * @param fromId, the id of the account from which to withdraw funds
     * @param toId, the id of the account to which funds will be deposited
     * @param amount, the amount to withdraw from an account and to deposit into the other
     * @throws InsufficientFundsException if the account represented by id fromId does not have at least the desired amount in the balance
     */
    public void transferWithoutLocking(int fromId, int toId, long amount) throws InsufficientFundsException {
        accounts.get(fromId).withdraw(amount);
        accounts.get(toId).deposit(amount);
    }

    /**
     * Transfers an amount from one account to another, by locking the entire bank (only one thread can work at a time)
     * @param fromId, the id of the account from which to withdraw funds
     * @param toId, the id of the account to which funds will be deposited
     * @param amount, the amount to withdraw from an account and to deposit into the other
     * @throws InsufficientFundsException if the account represented by id fromId does not have at least the desired amount in the balance
     */
    public void transferLockingBank(int fromId, int toId, long amount) throws InsufficientFundsException {
        synchronized(this) {
            accounts.get(fromId).withdraw(amount);
            accounts.get(toId).deposit(amount);
        }

    }

    /**
     * Transfers an amount from one account to another, by locking the 2 accounts used in the transfer (other accounts can be accessed by other threads)
     * @param fromId, the id of the account from which to withdraw funds
     * @param toId, the id of the account to which funds will be deposited
     * @param amount, the amount to withdraw from an account and to deposit into the other
     * @throws InsufficientFundsException if the account represented by id fromId does not have at least the desired amount in the balance
     */
    public void transferLockingAccounts(int fromId, int toId, long amount) throws InsufficientFundsException {
        Account fromAccount = accounts.get(fromId);
        Account toAccount = accounts.get(toId);
        synchronized(fromAccount) {
            fromAccount.withdraw(amount);
        }
        synchronized(toAccount){
            toAccount.deposit(amount);
        }

    }

    /**
     * Return the sum total of all balances of all accounts in the bank
     * @return the sum of all balances of accounts in the bank
     */
    public long totalBalances() {
        long sum = 0;
        // TODO Auto-generated method stub
        for (Account i : accounts) {
            sum+=i.balance();
        }
        return sum;
    }

    /**
     * Return the number of accounts in the bank
     * @return the number of accounts in the bank
     */
    public int numberOfAccounts() {
        // TODO Auto-generated method stub
        return accounts.size();
    }

}
