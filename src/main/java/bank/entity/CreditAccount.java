package bank.entity;

import bank.entity.jsonClasses.JsonCreditAcc;
import bank.entity.parentClasses.BankAccount;

import java.text.DecimalFormat;
import java.time.LocalDate;

public class CreditAccount extends BankAccount {
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer countMonth;
    private Double amount;
    private Double monthlyAmount;
    private Double interestRate;
    private Employee employee;
    private PaymentAccount paymentAccount;

    public CreditAccount(Integer id, User user, Bank bank, Employee employee, PaymentAccount paymentAccount,
                         LocalDate startDate, Integer countMonth, Double amount) {
        super(id, user, bank);
        this.startDate = startDate;
        this.countMonth = countMonth;
        this.endDate = startDate.plusMonths(this.countMonth);
        this.amount = amount;
        this.interestRate = bank.getInterestRate();
        this.monthlyAmount = null;
        this.employee = employee;
        this.paymentAccount = paymentAccount;
    }

    @Override
    public String toString() {
        return "Имя банка: " + super.getBank().getName() + "\nИмя пользователя: " + super.getUser().getFullName() +
                "\nКоличество месяцев: " + countMonth + "\nДата взятия кредита: " + startDate.toString() +
                "\nПредполагаемая дата погашения кредита:" + endDate.toString() + "\nСумма кредита: " +
                new DecimalFormat("#0.00").format(amount) + "\nПроцентная ставка: " + interestRate + "%" +
                "\nЕжемесячный платёж: " + countMonth + "\nСотрудник, выдавший кредит: " + employee.getFullName() +
                "\nId платёжного счёта: " + paymentAccount.getId().toString();
    }

    public void updateFromJsonClass(JsonCreditAcc jsonCreditAcc) {
        this.setId(jsonCreditAcc.getId());
        this.getBank().setId(jsonCreditAcc.getBankID());
        this.getUser().setId(jsonCreditAcc.getUserID());
        this.getPaymentAccount().setId(jsonCreditAcc.getPayAccID());
        this.getEmployee().setId(jsonCreditAcc.getEmployeeID());
        this.setStartDate(LocalDate.parse(jsonCreditAcc.getStartDate()));
        this.setEndDate(LocalDate.parse(jsonCreditAcc.getEndDate()));
        this.setCountMonth(jsonCreditAcc.getCountMonth());
        this.setAmount(jsonCreditAcc.getAmount());
        this.setMonthlyAmount(jsonCreditAcc.getMonthlyAmount());
        this.setInterestRate(jsonCreditAcc.getInterestRate());
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getCountMonth() {
        return countMonth;
    }

    public void setCountMonth(Integer countMonth) {
        this.countMonth = countMonth;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getMonthlyAmount() {
        return monthlyAmount;
    }

    public void setMonthlyAmount(Double monthlyAmount) {
        this.monthlyAmount = monthlyAmount;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public PaymentAccount getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(PaymentAccount paymentAccount) {
        this.paymentAccount = paymentAccount;
    }
}
