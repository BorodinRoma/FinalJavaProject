package bank.entity.parentClasses;

import bank.entity.Bank;
import bank.entity.User;

abstract public class BankAccount {
    private Integer id;
    private User user;
    private Bank bank;

    public BankAccount(Integer id, User user, Bank bank){
        this.id = id;
        this.user = user;
        this.bank = bank;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
