package cscie55.hw5.bank;

@SuppressWarnings("serial")
public abstract class BankException extends Exception
{
    protected BankException(String message)
    {
        super(message);
    }
}
