package cscie55.hw6.bank;

@SuppressWarnings("serial")
public abstract class BankException extends Exception
{
    protected BankException(String message)
    {
        super(message);
    }
}
