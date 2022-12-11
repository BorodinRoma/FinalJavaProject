package bank.exceptions;

public class AtmOfficeException extends Exception {
    public AtmOfficeException() {
        super("Банкомат принадлежит другому банковскому офису");
    }
}