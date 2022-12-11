package bank.exceptions;

public class EmployeeBankException extends Exception {
    public EmployeeBankException() {
        super("Сотрудник принадлежит другому Банку");
    }
}