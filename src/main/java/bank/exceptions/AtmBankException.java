package bank.exceptions;

public class AtmBankException extends Exception {
    public AtmBankException() {
        super("Банкомат принадлежит другому банку");
    }
}