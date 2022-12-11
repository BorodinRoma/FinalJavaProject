package bank.entity;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Bank {
    private Integer id;
    private String name;
    private Integer countOffice;
    private Integer countATM;
    private Integer countEmployees;
    private Integer countClients;
    private Integer rating;
    private Double money;
    private Double interestRate;

    private ArrayList<BankOffice> offices;
    private ArrayList<BankATM> atms;
    private ArrayList<Employee> employees;
    private ArrayList<User> clients;

    public Bank(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.countOffice = 0;
        this.countATM = 0;
        this.countEmployees = 0;
        this.countClients = 0;
        this.rating = null;
        this.money = null;
        this.interestRate = null;

        this.offices = new ArrayList<>();
        this.atms = new ArrayList<>();
        this.employees = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Имя банка: " + name + "\nКоличество офисов: " + countOffice + "\nКоличество банкоматов: " + countATM +
                "\nКоличество сотрудников: " + countEmployees + "\nКоличество клиентов: " + countClients +
                "\nРейтинг: " + rating + "\nКоличество денег: " + new DecimalFormat("#0.00").format(money) +
                "\nПроцентная ставка: " + interestRate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCountOffice() {
        return countOffice;
    }

    public void setCountOffice(Integer countOffice) {
        this.countOffice = countOffice;
    }

    public Integer getCountATM() {
        return countATM;
    }

    public void setCountATM(Integer countATM) {
        this.countATM = countATM;
    }

    public Integer getCountEmployees() {
        return countEmployees;
    }

    public void setCountEmployees(Integer countEmployees) {
        this.countEmployees = countEmployees;
    }

    public Integer getCountClients() {
        return countClients;
    }

    public void setCountClients(Integer countClients) {
        this.countClients = countClients;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public void setOffices(ArrayList<BankOffice> offices) {
        this.offices = offices;
    }

    public ArrayList<BankOffice> getOffices() {
        return offices;
    }

    public void setATMS(ArrayList<BankATM> atms) {
        this.atms = atms;
    }

    public ArrayList<BankATM> getATMS() {
        return atms;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setClients(ArrayList<User> clients) {
        this.clients = clients;
    }

    public ArrayList<User> getClients() {
        return clients;
    }
}