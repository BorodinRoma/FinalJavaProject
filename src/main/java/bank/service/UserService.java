package bank.service;

import bank.entity.*;
import bank.exceptions.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface UserService {
    void create(Integer id, String name, String surname, LocalDate birthDay, String work);
    void update(User user);
    void delete();
    User getUser();

    void addBank(Bank bank);
    void delBank(Bank bank);
    void addCreditAcc(CreditAccountService creditAcc) throws CredAccUserException;
    void delCreditAcc(CreditAccountService creditAcc) throws CredAccUserException;
    void addPayAcc(PaymentAccountService payAcc) throws PayAccUserException;
    void delPayAcc(PaymentAccountService payAcc) throws PayAccUserException;
    void applyForLoan(BankService bank, BankOffice workOffice, Employee workEmployee, BankATM atm, Double loanSum,
                      LocalDate startDate, Integer countMonth, PaymentAccountService payAcc,
                      CreditAccountService creditAcc) throws CreditExtension, BadUserRatingException,
            PayAccUserException, UserBankException, CredAccUserException;
    void saveToFile(String fileName, BankService bank) throws IOException;
    void updateFromFile(String fileName) throws IOException;
    void changeWork(String newWork, Double newMonthSalary);
}