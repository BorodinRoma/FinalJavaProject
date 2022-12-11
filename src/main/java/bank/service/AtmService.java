package bank.service;

import bank.entity.Bank;
import bank.entity.BankATM;
import bank.entity.BankOffice;
import bank.entity.Employee;
import bank.entity.enums.StatusATM;

public interface AtmService {
    void create(Integer id, String name, StatusATM status, Boolean workPayMoney, Boolean workDepositMoney,
                Double maintenanceCost, Bank bank, BankOffice bankOffice, Employee employee);
    void update(BankATM bankATM);
    void delete();
    BankATM getBankATM();

    Boolean addMoney(Double sumMoney);
    Boolean subtractMoney(Double sumMoney);
}