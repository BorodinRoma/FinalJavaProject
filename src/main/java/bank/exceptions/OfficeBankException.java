package bank.exceptions;

public class OfficeBankException extends Exception {
    public OfficeBankException() {
        super("Банковский офис принадлежит другому банку");
    }
}