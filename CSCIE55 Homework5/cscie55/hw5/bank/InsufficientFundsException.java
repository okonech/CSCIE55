package cscie55.hw5.bank;

@SuppressWarnings("serial")
public class InsufficientFundsException extends BankException
{
    public InsufficientFundsException(Account account, long withdrawal)
    {
        super(String.format("Attempt to withdraw %d from %s", withdrawal, account));
    }
}
