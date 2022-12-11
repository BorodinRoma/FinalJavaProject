package bank.exceptions;

public class UserBankException extends Exception {
    public UserBankException() {
        super("Клиент принадлежит другому банку");
    }
}