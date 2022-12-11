package bank.entity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import bank.entity.enums.StatusOffice;

public class BankOffice {
    private Integer id;
    private String name;
    private Bank bank;
    private String address;
    private StatusOffice status;
    private Boolean maySetATM;
    private Integer countATM;
    private Boolean mayApplyLoan;
    private Boolean mayWithdrawMoney;
    private Boolean mayDepositMoney;
    private Double money;
    private Double rentCost;

    private ArrayList<BankATM> bankATMS;
    private ArrayList<Employee> employees;

    public BankOffice(Integer id, String name, Bank bank, String address, StatusOffice status, Double rentCost) {
        this.id = id;
        this.name = name;
        this.bank = bank;
        this.address = address;
        this.status = status;
        this.maySetATM = true;
        this.countATM = 0;
        this.mayApplyLoan = true;
        this.mayWithdrawMoney = true;
        this.mayDepositMoney = true;
        this.money = 0.0;
        this.rentCost = rentCost;

        this.bankATMS = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    @Override
    public String toString() {
        String str =  "Название офиса: " + name + "\nИмя банка: " + bank.getName() + "\nАдрес: " + address +
                "\nСтатус: ";
        if (status == StatusOffice.Work)
            str+= "работает";
        else
            str+= "не работает";
        if (maySetATM)
            str += "\nМожно добавить банкомат";
        else
            str += "\nНельзя добавить банкомат";
        if (maySetATM)
            str += "\nКоличество банкоматов: " + countATM;
        if (mayWithdrawMoney)
            str += "\nРаботает на выдачу денег";
        else
            str += "\nНе работает на выдачу денег";
        if (mayDepositMoney)
            str += "\nМожно внести деньги";
        else
            str += "\nНельзя внести деньги";
        str += "\nДенежная сумма: " + new DecimalFormat("#0.00").format(money) +
                "\nАрендная плата: " + new DecimalFormat("#0.00").format(rentCost);
        return str;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer idBank) {
        this.id = idBank;
    }

    public String getName() {
        return name;
    }

    public void setName(String nameBank) {
        this.name = nameBank;
    }

    public String getAddress() {
        return address;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public StatusOffice getStatus() {
        return status;
    }

    public void setStatus(StatusOffice status) {
        this.status = status;
    }

    public Boolean getMaySetATM() {
        return maySetATM;
    }

    public void setMaySetATM(Boolean maySetATM) {
        this.maySetATM = maySetATM;
    }

    public Integer getCountATM() {
        return countATM;
    }

    public void setCountATM(Integer countATM) {
        this.countATM = countATM;
    }

    public Boolean getMayApplyLoan() {
        return mayApplyLoan;
    }

    public void setMayApplyLoan(Boolean mayApplyLoan) {
        this.mayApplyLoan = mayApplyLoan;
    }

    public Boolean getMayWithdrawMoney() {
        return mayWithdrawMoney;
    }

    public void setMayWithdrawMoney(Boolean mayWithdrawMoney) {
        this.mayWithdrawMoney = mayWithdrawMoney;
    }

    public Boolean getMayDepositMoney() {
        return mayDepositMoney;
    }

    public void setMayDepositMoney(Boolean mayDepositMoney) {
        this.mayDepositMoney = mayDepositMoney;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getRentCost() {
        return rentCost;
    }

    public void setRentCost(Double rentCost) {
        this.rentCost = rentCost;
    }

    public ArrayList<BankATM> getBankATMS() {
        return bankATMS;
    }

    public void setBankATMS(ArrayList<BankATM> bankATMS) {
        this.bankATMS = bankATMS;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }
}