package bank.exceptions;

public class CredAccUserException extends Exception {
    public CredAccUserException() {
        super("Кредитный аккаунт принадлежит другому пользователю");
    }
}