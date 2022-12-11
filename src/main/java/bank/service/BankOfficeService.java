package bank.service;

import bank.entity.Bank;
import bank.entity.BankATM;
import bank.entity.BankOffice;
import bank.entity.enums.StatusOffice;
import bank.exceptions.AtmOfficeException;
import bank.exceptions.EmployeeOfficeException;

public interface BankOfficeService {
    void create(Integer id, String name, Bank bank, String address, StatusOffice status, Double rentCost);
    void update(BankOffice bankOffice);
    void delete();
    BankOffice getBankOffice();

    void addBankATM(AtmService atm) throws AtmOfficeException;
    void delBankATM(AtmService atm) throws AtmOfficeException;
    void addEmployee(EmployeeService employee) throws EmployeeOfficeException;
    void delEmployee(EmployeeService employee) throws EmployeeOfficeException;

    void addMoney(Double sumMoney);
    Boolean subtractMoney(Double sumMoney);
    Boolean addATM();
    void subtractATM(BankATM bankATM);
}