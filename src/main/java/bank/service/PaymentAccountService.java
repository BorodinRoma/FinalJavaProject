package bank.service;

import bank.entity.Bank;
import bank.entity.PaymentAccount;
import bank.entity.User;

public interface PaymentAccountService {
    void create(Integer id, User user, Bank bank);
    void update(PaymentAccount payAcc);
    void delete();
    PaymentAccount getPayAcc();

    void addMoney(Double sumMoney);
    Boolean subtractMoney(Double sumMoney);
}