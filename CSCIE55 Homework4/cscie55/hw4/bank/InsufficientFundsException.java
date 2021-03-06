package cscie55.hw4.bank;

@SuppressWarnings("serial")
public class InsufficientFundsException extends Exception
{
    public InsufficientFundsException(Account account, long withdrawal)
    {
        super(String.format("Attempt to withdraw %d from %s", withdrawal, account));
    }
}
