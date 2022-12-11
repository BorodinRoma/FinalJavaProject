package bank.exceptions;

public class EmployeeOfficeException extends Exception {
    public EmployeeOfficeException() {
        super("Работник принадлежит другому банковскому офису");
    }
}