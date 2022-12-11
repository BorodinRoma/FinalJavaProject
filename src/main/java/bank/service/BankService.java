package bank.service;

import bank.entity.Bank;
import bank.exceptions.AtmBankException;
import bank.exceptions.UserBankException;
import bank.exceptions.OfficeBankException;
import bank.exceptions.EmployeeBankException;

public interface BankService {
    void create(Integer id, String name);
    void update(Bank bank);
    void delete();
    Bank getBank();

    void addBankOffice(BankOfficeService bankOffice) throws OfficeBankException;
    void delBankOffice(BankOfficeService bankOffice) throws OfficeBankException;
    void addBankATM(AtmService bankATM) throws AtmBankException;
    void delBankATM(AtmService bankATM) throws AtmBankException;
    void addEmployee(EmployeeService employee) throws EmployeeBankException;
    void delEmployee(EmployeeService employee) throws EmployeeBankException;
    void addUser(UserService user) throws UserBankException;
    void delUser(UserService user) throws UserBankException;

    void addMoney(Bank bank, Double sumMoney);
    Boolean subtractMoney(Bank bank, Double sumMoney);
}