package bank.entity;

import bank.entity.jsonClasses.JsonPayAcc;
import bank.entity.parentClasses.BankAccount;

import java.text.DecimalFormat;

public class PaymentAccount extends BankAccount {
    private Double amount;

    public PaymentAccount(Integer id, User user, Bank bank) {
        super(id,user,bank);
        this.amount = 0.0;
    }

    @Override
    public String toString() {
        return "Имя банка: " + super.getBank().getName() + "\nФИО пользователя: " + super.getUser().getFullName()
                + "\nСумма денег: " + new DecimalFormat("#0.00").format(amount);
    }

    public void updateFromJsonClass(JsonPayAcc jsonPayAcc) {
        this.setId(jsonPayAcc.getId());
        this.getBank().setId(jsonPayAcc.getBankID());
        this.getUser().setId(jsonPayAcc.getUserID());
        this.setAmount(jsonPayAcc.getAmount());
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
