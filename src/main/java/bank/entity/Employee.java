package bank.entity;

import bank.entity.parentClasses.Human;

import java.text.DecimalFormat;
import java.time.LocalDate;

public class Employee extends Human {
    private Bank bank;
    private BankOffice bankOffice;
    private String job;
    private Boolean distantWork;
    private Boolean canLend;
    private Double salary;

    public Employee(Integer id, String name, String surname, LocalDate birthday, Bank bank, BankOffice bankOffice, String job,
                    Double salary) {
        super(id, name, surname, birthday);
        this.bank = bank;
        this.bankOffice = bankOffice;
        this.job = job;
        this.distantWork = true;
        this.canLend = true;
        this.salary = salary;
    }

    public Employee(Integer id, String name, String surname,  String middleName, LocalDate birthday, Bank bank, BankOffice bankOffice, String job,
                    Double salary) {
        super(id, name, surname, middleName, birthday);
        this.bank = bank;
        this.bankOffice = bankOffice;
        this.job = job;
        this.distantWork = true;
        this.canLend = true;
        this.salary = salary;
    }

    @Override
    public String toString() {
        String str = "ФИО: " + super.getFullName() + "\nДата рождения: " + super.getBirthDay() +
                "\nДолжность: " + job + "\nИмя банка: " + bank.getName();
        if (distantWork)
            str += "\nРаботает в офисе";
        else
            str += "\nРаботает удалённо";
        if (canLend)
            str += "\nМожет выдавать кредиты";
        else
            str += "\nНе Может выдавать кредиты";
        str += "\nИмя офиса: " + bankOffice.getName() + "\nЗарплата: " +
                new DecimalFormat("#0.00").format(salary);
        return str;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public BankOffice getBankOffice() {
        return bankOffice;
    }

    public void setBankOffice(BankOffice bankOffice) {
        this.bankOffice = bankOffice;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Boolean getDistantWork() {
        return distantWork;
    }

    public void setDistantWork(Boolean distantWork) {
        this.distantWork = distantWork;
    }

    public Boolean getCanLend() {
        return canLend;
    }

    public void setCanLend(Boolean canLend) {
        this.canLend = canLend;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
