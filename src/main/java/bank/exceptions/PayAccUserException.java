package bank.exceptions;

public class PayAccUserException extends Exception {
    public PayAccUserException() {
        super("Платёжный счёт принадлежит другому пользователю");
    }
}