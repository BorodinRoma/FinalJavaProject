package bank.entity.jsonClasses;

import bank.entity.CreditAccount;

public class JsonCreditAcc {
    private Integer id;
    private Integer bankID;
    private Integer userID;
    private Integer payAccID;
    private Integer employeeID;
    private String startDate;
    private String endDate;
    private Integer countMonth;
    private Double amount;
    private Double monthlyAmount;
    private Double interestRate;

    public JsonCreditAcc(CreditAccount creditAcc) {
        this.id = creditAcc.getId();
        this.bankID = creditAcc.getBank().getId();
        this.userID = creditAcc.getUser().getId();
        this.payAccID = creditAcc.getPaymentAccount().getId();
        this.employeeID = creditAcc.getEmployee().getId();
        this.startDate = creditAcc.getStartDate().toString();
        this.endDate = creditAcc.getEndDate().toString();
        this.countMonth = creditAcc.getCountMonth();
        this.amount = creditAcc.getAmount();
        this.monthlyAmount = creditAcc.getMonthlyAmount();
        this.interestRate = creditAcc.getInterestRate();
    }

    public Integer getId() {return this.id;}

    public void setId(Integer id) {this.id = id;}

    public Integer getBankID() {return this.bankID;}

    public void setBankID(Integer bankID) {this.bankID = bankID;}

    public Integer getUserID() {return this.userID;}

    public void setUserID(Integer userID) {this.userID = userID;}

    public Integer getPayAccID() {return this.payAccID;}

    public void setPayAccID(Integer payAccID) {this.payAccID = payAccID;}

    public Integer getEmployeeID() {return this.employeeID;}

    public void setEmployeeID(Integer employeeID) {this.employeeID = employeeID;}

    public String getStartDate() {return this.startDate;}

    public void setStartDate(String startDate) {this.startDate = startDate;}

    public String getEndDate() {return this.endDate;}

    public void setEndDate(String endDate) {this.endDate = endDate;}

    public Integer getCountMonth() {return this.countMonth;}

    public void setEndDate(Integer countMonth) {this.countMonth = countMonth;}

    public Double getAmount() {return this.amount;}

    public void setAmount(Double amount) {this.amount = amount;}

    public Double getMonthlyAmount() {return this.monthlyAmount;}

    public void setMonthlyAmount(Double monthlyAmount) {this.monthlyAmount = monthlyAmount;}

    public Double getInterestRate() {return this.interestRate;}

    public void setInterestRate(Double interestRate) {this.interestRate = interestRate;}
}
