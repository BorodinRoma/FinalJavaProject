package bank.service.impl;

import bank.entity.Bank;
import java.time.LocalDate;
import bank.entity.Employee;
import bank.entity.BankOffice;
import bank.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {
    private Employee employee = null;

    /*Создание экземпляра сотрудника на основе входных данных, в виде id, имени, фамилии, даты рождения, банка,
    должности и зарплаты сотрудника*/
    @Override
    public void create(Integer id, String name, String surname, LocalDate birthday, Bank bank, BankOffice bankOffice,
                       String job, Double salary) {
        bank.setCountEmployees(bank.getCountEmployees() + 1);
        this.employee = new Employee(id, name, surname, birthday, bank, bankOffice, job, salary);
    }

    /*Обновление экземпляра сотрудника на основе входных данных, в виде другого экземпляра сотрудника*/
    @Override
    public void update(Employee employee) {
        this.employee = employee;
    }

    /*Обнуление экземпляра сотрудника*/
    @Override
    public void delete() {
        this.employee = null;
    }

    /*Возврат экземпляра сотрудника*/
    @Override
    public Employee getEmployee() {
        return this.employee;
    }

    /*Отправка работника на удалённую работу*/
    @Override
    public void toDistantWork() {
        this.employee.setDistantWork(Boolean.TRUE);
    }

    /*Отправка работника на работу в офисе*/
    @Override
    public void toOfficeWork() {
        this.employee.setDistantWork(Boolean.FALSE);
    }
}