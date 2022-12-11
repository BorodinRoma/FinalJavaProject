package bank.service.impl;

import bank.entity.Bank;
import bank.entity.BankATM;
import bank.entity.BankOffice;
import bank.entity.Employee;
import bank.entity.enums.StatusOffice;
import bank.exceptions.AtmOfficeException;
import bank.exceptions.EmployeeOfficeException;
import bank.service.AtmService;
import bank.service.BankOfficeService;
import bank.service.EmployeeService;

import java.util.ArrayList;
import java.util.Objects;

public class BankOfficeServiceImpl implements BankOfficeService {
    private BankOffice bankOffice = null;

    /*Создание экземпляра офиса на основе входных данных, в виде id, имени, банка, адреса, статуса и стоимости
    аренды офиса*/
    @Override
    public void create(Integer id, String name, Bank bank, String address, StatusOffice status, Double rentCost) {
        bank.setCountOffice(bank.getCountOffice() + 1);
        this.bankOffice = new BankOffice(id, name, bank, address, status, rentCost);
    }

    /*Обновление экземпляра офиса на основе входных данных, в виде другого экземпляра офиса*/
    @Override
    public void update(BankOffice bankOffice) {
        this.bankOffice = bankOffice;
    }

    /*Обнуление экземпляра офиса*/
    @Override
    public void delete() {
        this.bankOffice = null;
    }

    /*Возврат экземпляра офиса*/
    @Override
    public BankOffice getBankOffice() {
        return this.bankOffice;
    }

    /*Добавление банкомата в список банкоматов офиса*/
    @Override
    public void addBankATM(AtmService atm) throws AtmOfficeException {
        if (!Objects.equals(atm.getBankATM().getBankOffice(), this.bankOffice))
            throw new AtmOfficeException();
        ArrayList<BankATM> bankATMS = this.bankOffice.getBankATMS();
        bankATMS.add(atm.getBankATM());
        this.bankOffice.setBankATMS(bankATMS);
        atm.getBankATM().setBankOffice(this.bankOffice);
    }

    /*Удаление банкомата из списка банкоматов офиса*/
    @Override
    public void delBankATM(AtmService atm) throws AtmOfficeException {
        if (!Objects.equals(atm.getBankATM().getBankOffice(), this.bankOffice))
            throw new AtmOfficeException();
        ArrayList<BankATM> bankATMS = this.bankOffice.getBankATMS();
        bankATMS.remove(atm.getBankATM());
        this.bankOffice.setBankATMS(bankATMS);
        atm.getBankATM().setBankOffice(this.bankOffice);
    }

    /*Добавление работника в список работников офиса*/
    @Override
    public void addEmployee(EmployeeService employee) throws EmployeeOfficeException {
        if (!Objects.equals(employee.getEmployee().getBankOffice(), this.bankOffice))
            throw new EmployeeOfficeException();
        ArrayList<Employee> employees = this.bankOffice.getEmployees();
        employees.add(employee.getEmployee());
        this.bankOffice.setEmployees(employees);
        employee.getEmployee().setBankOffice(this.bankOffice);
    }

    /*Удаление работника из списка работников офиса*/
    @Override
    public void delEmployee(EmployeeService employee) throws EmployeeOfficeException {
        if (!Objects.equals(employee.getEmployee().getBankOffice(), this.bankOffice))
            throw new EmployeeOfficeException();
        ArrayList<Employee> employees = this.bankOffice.getEmployees();
        employees.remove(employee.getEmployee());
        this.bankOffice.setEmployees(employees);
        employee.getEmployee().setBankOffice(this.bankOffice);
    }

    /*Добавление суммы денег в офис, и, соответственно, добавление суммы денег в банк, которому
    принадлежит данный офис*/
    @Override
    public void addMoney(Double sumMoney) {
        Double sumBank = this.bankOffice.getBank().getMoney();
        Double sumOffice = this.bankOffice.getMoney();
        this.bankOffice.getBank().setMoney(sumBank + sumMoney);
        this.bankOffice.setMoney(sumOffice + sumMoney);
    }

    /*Вычитание суммы денег из офиса, и, соответственно, вычитание суммы денег из банка, которому принадлежит
     * данный офис, с проверкой того, достаточно ли денег в офисе, чтобы их вычесть. Если не достаточно, то
     * возвращается false, иначе true*/
    @Override
    public Boolean subtractMoney(Double sumMoney) {
        Double sumBank = this.bankOffice.getBank().getMoney();
        Double sumOffice = this.bankOffice.getMoney();
        if (sumOffice < sumMoney)
            return Boolean.FALSE;
        this.bankOffice.getBank().setMoney(sumBank - sumMoney);
        this.bankOffice.setMoney(sumOffice - sumMoney);
        return Boolean.TRUE;
    }

    /*Добавление нового банкомата в офис, и, соответственно, добавление нового банкомата в банк, которому
     * принадлежит данный офис, с проверкой того, можно ли добавить в этот офис новый банкомат.
     * Если нельзя достаточно, то возвращается false, иначе true*/
    @Override
    public Boolean addATM() {
        if (!this.bankOffice.getMaySetATM())
            return Boolean.FALSE;
        this.bankOffice.setCountATM(this.bankOffice.getCountATM() + 1);
        this.bankOffice.getBank().setCountATM(this.bankOffice.getBank().getCountATM() + 1);
        return Boolean.TRUE;
    }

    /*Вычитание банкомата из офиса, и, соответственно, вычитание банкомата из банка, которому
     * принадлежит данный офис*/
    @Override
    public void subtractATM(BankATM bankATM) {
        this.bankOffice.setCountATM(this.bankOffice.getCountATM() - 1);
        this.bankOffice.getBank().setCountATM(this.bankOffice.getCountATM() - 1);
        bankATM.setBankOffice(null);
    }
}