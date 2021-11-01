package cscie55.hw5.bank;

@SuppressWarnings("serial")
public class DuplicateAccountException extends BankException
{
    public DuplicateAccountException(int accountId)
    {
        super(String.format("Attempt to add a second account with id %d", accountId));
    }
}
