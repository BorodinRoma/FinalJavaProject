package bank.service.impl;

import bank.entity.*;
import bank.service.CreditAccountService;

import java.time.LocalDate;
import java.util.Objects;

public class CreditAccountServiceImpl implements CreditAccountService {
    private CreditAccount creditAcc = null;

    /*Создание экземпляра кредитного счёта на основе входных данных, в виде id, пользователя, банка, сотрудника,
    платёжного счёта, даты начала кредита, количества месяцев кредита, суммы кредита*/
    @Override
    public void create(Integer id, User user, Bank bank, Employee employee, PaymentAccount paymentAccount,
                       LocalDate startDate, Integer countMonth, Double amount) {
        this.creditAcc = new CreditAccount(id, user, bank, employee, paymentAccount, startDate, countMonth, amount);
        calcMonthlyAmount();
    }

    /*Обновление экземпляра кредитного счёта на основе входных данных, в виде другого экземпляра кредитного счёта*/
    @Override
    public void update(CreditAccount creditAcc) {
        this.creditAcc = creditAcc;
    }

    /*Обнуление экземпляра кредитного счёта*/
    @Override
    public void delete() {
        this.creditAcc = null;
    }

    /*Возврат экземпляра кредитного счёта*/
    @Override
    public CreditAccount getCreditAcc() {
        return this.creditAcc;
    }

    /*Расчёт ежемесячной суммы кредита*/
    private void calcMonthlyAmount() {
        this.creditAcc.setMonthlyAmount((1 + this.creditAcc.getInterestRate() / 100) * this.creditAcc.getAmount()
                / this.creditAcc.getCountMonth());
    }

    /*Одобрение заявки на кредит. В случае одобрения, если выбранный сотрудник совпадает с сотрудником,
     * фактически оформляющим кредит, если банк имеет достаточную сумму для выдачи кредита и если выбранный
     * сотрудник может оформлять кредиты, то на платёжный счёт пользователя поступает запрошенная сумма. В
     * случае оформления возвращается true, иначе false.*/
    @Override
    public Boolean applyLoan(PaymentAccount paymentAccount, Employee employee) {
        if ((this.creditAcc.getAmount() > this.creditAcc.getBank().getMoney()) || (!employee.getCanLend())
                || (!Objects.equals(this.creditAcc.getEmployee(), employee)))
            return Boolean.FALSE;
        this.creditAcc.getBank().setMoney(this.creditAcc.getBank().getMoney() - this.creditAcc.getAmount());
        paymentAccount.setAmount(paymentAccount.getAmount() + this.creditAcc.getAmount());
        return Boolean.TRUE;
    }
}