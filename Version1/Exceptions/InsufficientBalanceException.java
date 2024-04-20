package Version1.Exceptions;

public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String msg){
        super(msg);
    }
}