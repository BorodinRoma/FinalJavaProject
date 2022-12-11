package bank.entity;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import bank.entity.parentClasses.Human;

public class User extends Human {
    private String work;
    private Double monthSalary;
    private Integer creditRating;

    private ArrayList<Bank> banks;
    private ArrayList<CreditAccount> creditAccounts;
    private ArrayList<PaymentAccount> paymentAccounts;

    public User(Integer id, String name, String surname, LocalDate birthDay, String work) {
        super(id, name, surname, birthDay);
        this.work = work;
        this.monthSalary = null;
        this.creditRating = null;

        this.banks = new ArrayList<>();
        this.creditAccounts = new ArrayList<>();
        this.paymentAccounts = new ArrayList<>();
    }

    public User(Integer id, String name, String surname, String middleName, LocalDate birthDay, String work) {
        super(id, name, surname, middleName, birthDay);
        this.work = work;
        this.monthSalary = null;
        this.creditRating = null;
    }

    @Override
    public String toString() {
        return "ФИО: " + super.getFullName() + "\nДата рождения: " + super.getBirthDay() + "\nРабота: " +
                work + "\nЗарплата: " + new DecimalFormat("#0.00").format(monthSalary) +
                "\nКредитный рейтинг: "+ creditRating;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public Double getMonthSalary() {
        return monthSalary;
    }

    public void setMonthSalary(Double monthSalary) {
        this.monthSalary = monthSalary;
    }

    public Integer getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(Integer creditRating) {
        this.creditRating = creditRating;
    }

    public ArrayList<Bank> getBanks() {
        return banks;
    }

    public void setBanks(ArrayList<Bank> banks) {
        this.banks = banks;
    }

    public ArrayList<CreditAccount> getCreditAccounts() {
        return creditAccounts;
    }

    public void setCreditAccounts(ArrayList<CreditAccount> creditAccounts) {
        this.creditAccounts = creditAccounts;
    }

    public ArrayList<PaymentAccount> getPaymentAccounts() {
        return paymentAccounts;
    }

    public void setPaymentAccounts(ArrayList<PaymentAccount> paymentAccounts) {
        this.paymentAccounts = paymentAccounts;
    }
}