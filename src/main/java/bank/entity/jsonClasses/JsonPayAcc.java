package bank.entity.jsonClasses;

import bank.entity.PaymentAccount;

public class JsonPayAcc {
    private Integer id;
    private Integer bankID;
    private Integer userID;
    private Double amount;

    public JsonPayAcc(PaymentAccount payAcc) {
        this.id = payAcc.getId();
        this.bankID = payAcc.getBank().getId();
        this.userID = payAcc.getUser().getId();
        this.amount = payAcc.getAmount();
    }

    public Integer getId() {return this.id;}

    public void setId(Integer id) {this.id = id;}

    public Integer getBankID() {return this.bankID;}

    public void setBankID(Integer bankID) {this.bankID = bankID;}

    public Integer getUserID() {return this.userID;}

    public void setUserID(Integer userID) {this.userID = userID;}

    public Double getAmount() {return this.amount;}

    public void setAmount(Double amount) {this.amount = amount;}
}
