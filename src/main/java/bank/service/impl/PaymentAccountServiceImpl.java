package bank.service.impl;

import bank.entity.Bank;
import bank.entity.PaymentAccount;
import bank.entity.User;
import bank.service.PaymentAccountService;

public class PaymentAccountServiceImpl implements PaymentAccountService {
    private PaymentAccount payAcc = null;

    /*Создание экземпляра платёжного счёта пользователя на основе входных данных, в виде id, пользователя
    и банка*/
    @Override
    public void create(Integer id, User user, Bank bank) {
        this.payAcc = new PaymentAccount(id, user, bank);
    }

    /*Обновление экземпляра платёжного счёта пользователя на основе входных данных, в виде другого экземпляра
    платёжного счёта пользователя*/
    @Override
    public void update(PaymentAccount payAcc) {
        this.payAcc = payAcc;
    }

    /*Обнуление экземпляра платёжного счёта пользователя*/
    @Override
    public void delete() {
        this.payAcc = null;
    }

    /*Возврат экземпляра платёжного счёта пользователя*/
    @Override
    public PaymentAccount getPayAcc() {
        return this.payAcc;
    }

    /*Добавление суммы денег на платёжный счёт, и, соответственно, добавление суммы денег в банк,
        которому принадлежит данный платёжный счёт*/
    @Override
    public void addMoney(Double sumMoney) {
        this.payAcc.setAmount(this.payAcc.getAmount() + sumMoney);
        this.payAcc.getBank().setMoney(this.payAcc.getBank().getMoney() + sumMoney);
    }

    /*Вычитание суммы денег с платёжного счёта, и, соответственно, вычитание суммы денег из банка, которому
     * принадлежит данный платёжный счёт, с проверкой того, достаточно ли денег на платёжном счету, чтобы их
     * вычесть. Если не достаточно, то возвращается false, иначе true*/
    @Override
    public Boolean subtractMoney(Double sumMoney) {
        if (this.payAcc.getAmount() < sumMoney)
            return Boolean.FALSE;
        this.payAcc.setAmount(this.payAcc.getAmount() - sumMoney);
        this.payAcc.getBank().setMoney(this.payAcc.getBank().getMoney() - sumMoney);
        return Boolean.TRUE;
    }
}