package bank.service.impl;

import bank.entity.*;
import bank.exceptions.AtmBankException;
import bank.exceptions.EmployeeBankException;
import bank.exceptions.OfficeBankException;
import bank.exceptions.UserBankException;
import bank.service.*;
import java.util.Random;
import java.util.Objects;
import java.util.ArrayList;

public class BankServiceImpl implements BankService {
    private Bank bank = null;

    /*Создание экземпляра банка на основе входных данных, в виде id и имени банка*/
    @Override
    public void create(Integer id, String name) {
        this.bank = new Bank(id, name);
        calcRating();
        calcMoney();
        calcRate();
    }

    /*Обновление экземпляра банка на основе входных данных, в виде другого экземпляра банка*/
    @Override
    public void update(Bank bank) {
        this.bank = bank;
    }

    /*Обнуление экземпляра банка*/
    @Override
    public void delete() {
        this.bank = null;
    }

    /*Возврат экземпляра банка*/
    @Override
    public Bank getBank() {
        return this.bank;
    }

    /*Расчёт рейтинга банка рандомным способом*/
    private void calcRating() {
        Random random = new Random();
        this.bank.setRating(random.nextInt(0, 100));
    }

    /*Расчёт суммы денег банка рандомным способом*/
    private void calcMoney() {
        Random random = new Random();
        this.bank.setMoney(random.nextDouble(0, 1000000));
    }

    /*Расчёт процентной ставки банка, с учётом рейтинга банка*/
    private void calcRate() {
        this.bank.setInterestRate(20.0 - this.bank.getRating() / 5.0);
    }

    /*Добавление офиса в список офисов банка*/
    @Override
    public void addBankOffice(BankOfficeService bankOffice) throws OfficeBankException {
        if (!Objects.equals(bankOffice.getBankOffice().getBank(), this.bank))
            throw new OfficeBankException();
        ArrayList<BankOffice> offices = this.bank.getOffices();
        offices.add(bankOffice.getBankOffice());
        this.bank.setOffices(offices);
        bankOffice.getBankOffice().setBank(this.bank);
    }

    /*Удаление офиса из списка офисов банка*/
    @Override
    public void delBankOffice(BankOfficeService bankOffice) throws OfficeBankException {
        if (!Objects.equals(bankOffice.getBankOffice().getBank(), this.bank))
            throw new OfficeBankException();
        ArrayList<BankOffice> offices = this.bank.getOffices();
        offices.remove(bankOffice.getBankOffice());
        this.bank.setOffices(offices);
        bankOffice.getBankOffice().setBank(this.bank);
    }

    /*Добавление банкомата в список банкоматов банка*/
    @Override
    public void addBankATM(AtmService bankATM) throws AtmBankException {
        if (!Objects.equals(bankATM.getBankATM().getBank(), this.bank))
            throw new AtmBankException();
        ArrayList<BankATM> atms = this.bank.getATMS();
        atms.add(bankATM.getBankATM());
        this.bank.setATMS(atms);
        bankATM.getBankATM().setBank(this.bank);
    }

    /*Удаление банкомата из списка банкоматов банка*/
    @Override
    public void delBankATM(AtmService bankATM) throws AtmBankException {
        if (!Objects.equals(bankATM.getBankATM().getBank(), this.bank))
            throw new AtmBankException();
        ArrayList<BankATM> atms = this.bank.getATMS();
        atms.remove(bankATM.getBankATM());
        this.bank.setATMS(atms);
        bankATM.getBankATM().setBank(this.bank);
    }

    /*Добавление работника в список работников банка*/
    @Override
    public void addEmployee(EmployeeService employee) throws EmployeeBankException {
        if (!Objects.equals(employee.getEmployee().getBank(), this.bank))
            throw new EmployeeBankException();
        ArrayList<Employee> bankEmployees = this.bank.getEmployees();
        bankEmployees.add(employee.getEmployee());
        this.bank.setEmployees(bankEmployees);
        employee.getEmployee().setBank(this.bank);
    }

    /*Удаление работника из списка работников банка*/
    @Override
    public void delEmployee(EmployeeService employee) throws EmployeeBankException {
        if (!Objects.equals(employee.getEmployee().getBank(), this.bank))
            throw new EmployeeBankException();
        ArrayList<Employee> bankEmployees = this.bank.getEmployees();
        bankEmployees.remove(employee.getEmployee());
        this.bank.setEmployees(bankEmployees);
        employee.getEmployee().setBank(this.bank);
    }

    /*Добавление клиента в список клиентов банка*/
    @Override
    public void addUser(UserService user) throws UserBankException {
        if (this.bank.getClients().contains(user.getUser()))
            throw new UserBankException();
        ArrayList<User> clients = this.bank.getClients();
        clients.add(user.getUser());
        this.bank.setCountClients(this.bank.getCountClients() + 1);
        this.bank.setClients(clients);
        user.addBank(this.bank);
    }

    /*Удаление клиента из списка клиентов банка*/
    @Override
    public void delUser(UserService user) throws UserBankException {
        if (!this.bank.getClients().contains(user.getUser()))
            throw new UserBankException();
        ArrayList<User> clients = this.bank.getClients();
        clients.remove(user.getUser());
        this.bank.setCountClients(this.bank.getCountClients() + 1);
        this.bank.setClients(clients);
        user.delBank(this.bank);
    }

    /*Добавление суммы денег в банк, путём взятия суммы из банка, добавления к ней суммы, которую хотим внести
     * и внесения в банк новой получившейся суммы*/
    @Override
    public void addMoney(Bank bank, Double sumMoney) {
        Double sum = bank.getMoney();
        bank.setMoney(sum + sumMoney);
    }

    /*Вычитание суммы денег из банка, путём взятия денег из банка, сравнения её с суммой списания, вычитание суммы
     * списания из суммы, взятой из банка, и запись в банк новой суммы*/
    @Override
    public Boolean subtractMoney(Bank bank, Double sumMoney) {
        Double sum = bank.getMoney();
        if (sumMoney > sum)
            return Boolean.FALSE;
        bank.setMoney(sum - sumMoney);
        return Boolean.TRUE;
    }

    /*Вывод полной информации о банке, его офисах, банкоматах, сотрудниках и клиентах*/
    @Override
    public String toString() {
        StringBuilder returnStr = new StringBuilder(this.bank.toString());
        returnStr.append("\n\nОфисы банка:");
        for (int i_1 = 0; i_1 < this.bank.getOffices().size(); i_1++) {
            returnStr.append(String.format("\n\n#№%d\n", i_1));
            returnStr.append(bank.getOffices().get(i_1).toString());
        }
        returnStr.append("\n\nБанкоматы банка:");
        for (int i_1 = 0; i_1 < this.bank.getATMS().size(); i_1++) {
            returnStr.append(String.format("\n\n#№%d\n", i_1));
            returnStr.append(bank.getATMS().get(i_1).toString());
        }
        returnStr.append("\n\nСотрудники банка:");
        for (int i_1 = 0; i_1 < this.bank.getEmployees().size(); i_1++) {
            returnStr.append(String.format("\n\n#№%d\n", i_1));
            returnStr.append(bank.getEmployees().get(i_1).toString());
        }
        returnStr.append("\n\nКлиенты банка:");
        for (int i_1 = 0; i_1 < this.bank.getClients().size(); i_1++) {
            returnStr.append(String.format("\n\n#№%d\n", i_1));
            returnStr.append(bank.getClients().get(i_1).toString());
        }
        return returnStr.toString();
    }
}