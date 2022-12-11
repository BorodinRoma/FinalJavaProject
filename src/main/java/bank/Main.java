package bank;

import bank.exceptions.*;
import java.util.Scanner;
import bank.service.impl.*;
import java.time.LocalDate;
import java.util.ArrayList;
import bank.entity.Employee;
import bank.entity.BankOffice;
import bank.service.BankService;
import bank.service.UserService;
import bank.entity.enums.StatusATM;
import bank.entity.enums.StatusOffice;

public class Main {
    static void mainLab_1() {
        //Bank
        System.out.println("Bank:");
        BankServiceImpl bankService = new BankServiceImpl();
        bankService.create(1, "bank_name");
        System.out.println(bankService.getBank());

        //Bank Office
        System.out.println("\n\nOffice:");
        BankOfficeServiceImpl bankOfficeService = new BankOfficeServiceImpl();
        bankOfficeService.create(1, "office_name", bankService.getBank(), "address_1",
                StatusOffice.Work, 15000.0);
        System.out.println(bankOfficeService.getBankOffice());

        //Employee
        System.out.println("\n\nEmployee:");
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        employeeService.create(1, "Ivan", "Ivanov", LocalDate.of(2000, 10,
                        11),
                bankService.getBank(), bankOfficeService.getBankOffice(), "job_1", 100.0);
        System.out.println(employeeService.getEmployee());

        //Bank ATM
        System.out.println("\n\nATM:");
        AtmServiceImpl atmService = new AtmServiceImpl();
        atmService.create(1, "ATM_1", StatusATM.Work, Boolean.TRUE, Boolean.TRUE,
                100.0, bankService.getBank(), bankOfficeService.getBankOffice(),
                employeeService.getEmployee());
        System.out.println(atmService.getBankATM());

        //User
        System.out.println("\n\nUser:");
        UserServiceImpl userService = new UserServiceImpl();
        userService.create(1, "Maxim", "Maximovich", LocalDate.of(2000, 10,
                        11),
                "work_1");
        System.out.println(userService.getUser());

        //Payment Account
        System.out.println("\n\nPayment Account:");
        PaymentAccountServiceImpl paymentAccountService = new PaymentAccountServiceImpl();
        paymentAccountService.create(1, userService.getUser(), bankService.getBank());
        System.out.println(paymentAccountService.getPayAcc());

        //Credit Account
        System.out.println("\n\nCredit Account:");
        CreditAccountServiceImpl creditAccountService = new CreditAccountServiceImpl();
        creditAccountService.create(1, userService.getUser(), bankService.getBank(), employeeService.getEmployee(),
                paymentAccountService.getPayAcc(), LocalDate.of(2022, 11, 11), 12,
                150.0);
        System.out.println(creditAccountService.getCreditAcc());
    }

    static void mainLab_2() throws CredAccUserException, PayAccUserException, OfficeBankException, AtmBankException,
            EmployeeBankException, UserBankException, AtmOfficeException, EmployeeOfficeException {
        ArrayList<BankServiceImpl> banks = new ArrayList<>();
        ArrayList<UserServiceImpl> users = new ArrayList<>();
        for (int i_1 = 0; i_1 < 5; i_1++) {
            BankServiceImpl bankService = new BankServiceImpl();
            bankService.create(i_1, String.format("bank_№%d", i_1));
            for (int i_2 = 0; i_2 < 3; i_2++) {
                BankOfficeServiceImpl bankOfficeService = new BankOfficeServiceImpl();
                bankOfficeService.create(i_2 + i_1, String.format("office_№%d", i_2), bankService.getBank(),
                        String.format("address_%d", i_2), StatusOffice.Work, 15000.0);
                for (int i_3 = 0; i_3 < 5; i_3++) {
                    EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
                    employeeService.create(i_3 + i_2 + i_1, String.format("Ivan_%d", i_3 + i_2 + i_1),
                            "Ivanov", LocalDate.of(2000, 10, 11), bankService
                                    .getBank(),
                            bankOfficeService.getBankOffice(), String.format("job_%d", i_3), 100.0);
                    bankOfficeService.addEmployee(employeeService);
                    bankService.addEmployee(employeeService);
                }
                AtmServiceImpl atmService = new AtmServiceImpl();
                atmService.create(i_2 + i_1, String.format("ATM_%d", i_2 + i_1), StatusATM.Work, Boolean.TRUE,
                        Boolean.TRUE, 100.0, bankOfficeService.getBankOffice().getBank(),
                        bankOfficeService.getBankOffice(), bankOfficeService.getBankOffice().getEmployees().get(1));
                bankOfficeService.addBankATM(atmService);
                bankService.addBankATM(atmService);
                bankService.addBankOffice(bankOfficeService);
            }

            UserServiceImpl userService = new UserServiceImpl();
            userService.create(i_1, String.format("Maxim_%d", i_1), "Maximovich", LocalDate.of(2000,
                    10, 11), String.format("work_%d", i_1));
            for (int i_2 = 0; i_2 < 2; i_2++) {
                PaymentAccountServiceImpl paymentAccountService = new PaymentAccountServiceImpl();
                paymentAccountService.create(i_2 + i_1, userService.getUser(), bankService.getBank());

                CreditAccountServiceImpl creditAccountService = new CreditAccountServiceImpl();
                creditAccountService.create(i_2 + i_1, userService.getUser(), bankService.getBank(),
                        bankService.getBank().getEmployees().get(1), paymentAccountService.getPayAcc(),
                        LocalDate.of(2022, 11, 11), 12, 150.0);

                userService.addPayAcc(paymentAccountService);
                userService.addCreditAcc(creditAccountService);
            }
            bankService.addUser(userService);
            banks.add(bankService);
            users.add(userService);
        }

        System.out.println("Банк");
        System.out.println(banks.get(0));
        System.out.println("\n\nКлиент");
        System.out.println(users.get(0));
    }

    static ArrayList<BankService> sortBanksByCriteria(ArrayList<BankService> banks, Double loanSum) {
        ArrayList<BankService> banksWithMoney = new ArrayList<>();
        ArrayList<Double> criteria = new ArrayList<>();
        for (BankService bank : banks) {
            if (bank.getBank().getMoney() >= loanSum) {
                banksWithMoney.add(bank);
                criteria.add(bank.getBank().getCountOffice() + bank.getBank().getCountATM() +
                        bank.getBank().getCountEmployees() + (20 - bank.getBank().getInterestRate()));
            }
        }
        for (int i = 0; i < criteria.size(); i++) {
            for (int j = 0; j < criteria.size(); j++) {
                if (criteria.get(j) < criteria.get(i)) {
                    Double crit = criteria.get(i);
                    BankService bank = banksWithMoney.get(i);

                    criteria.set(i, criteria.get(j));
                    banksWithMoney.set(i, banksWithMoney.get(j));
                    criteria.set(j, crit);
                    banksWithMoney.set(j, bank);
                }
            }
        }
        return banksWithMoney;
    }

    static void mainLab_3() throws CredAccUserException, PayAccUserException, OfficeBankException, AtmBankException,
            EmployeeBankException, UserBankException, AtmOfficeException, EmployeeOfficeException, CreditExtension,
            BadUserRatingException {
        ArrayList<BankService> banks = new ArrayList<>();
        ArrayList<UserService> users = new ArrayList<>();
        for (int i_1 = 0; i_1 < 5; i_1++) {
            BankServiceImpl bankService = new BankServiceImpl();
            bankService.create(i_1, String.format("bank_№%d", i_1));
            for (int i_2 = 0; i_2 < 3; i_2++) {
                BankOfficeServiceImpl bankOfficeService = new BankOfficeServiceImpl();
                bankOfficeService.create(i_2 + i_1, String.format("office_№%d", i_2), bankService.getBank(),
                        String.format("address_%d", i_2), StatusOffice.Work, 15000.0);
                bankOfficeService.addMoney(bankService.getBank().getMoney()/3);
                for (int i_3 = 0; i_3 < 5; i_3++) {
                    EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
                    employeeService.create(i_3 + 5 * i_2 + 3 * i_1, String.format("Ivan_%d", i_3 + 5 * i_2
                                    + 3 * i_1), "Ivanov",
                            LocalDate.of(2000, 10, 11), bankService.getBank(),
                            bankOfficeService.getBankOffice(), String.format("job_%d", i_3), 100.0);
                    bankOfficeService.addEmployee(employeeService);
                    bankService.addEmployee(employeeService);
                }
                AtmServiceImpl atmService = new AtmServiceImpl();
                atmService.create(i_2 + i_1, String.format("ATM_%d", i_2 + i_1), StatusATM.Work, Boolean.TRUE,
                        Boolean.TRUE,
                        100.0, bankOfficeService.getBankOffice().getBank(),
                        bankOfficeService.getBankOffice(), bankOfficeService.getBankOffice().getEmployees().get(1));
                atmService.addMoney(bankOfficeService.getBankOffice().getMoney());
                bankOfficeService.addBankATM(atmService);
                bankService.addBankATM(atmService);
                bankService.addBankOffice(bankOfficeService);
            }

            UserServiceImpl userService = new UserServiceImpl();
            userService.create(i_1, String.format("Maxim_%d", i_1), "Maximovich", LocalDate.of(2000,
                    10, 11), String.format("work_%d", i_1));
            for (int i_2 = 0; i_2 < 2; i_2++) {
                PaymentAccountServiceImpl paymentAccountService = new PaymentAccountServiceImpl();
                paymentAccountService.create(i_2 + i_1, userService.getUser(), bankService.getBank());

                CreditAccountServiceImpl creditAccountService = new CreditAccountServiceImpl();
                creditAccountService.create(i_2 + i_1, userService.getUser(), bankService.getBank(),
                        bankService.getBank().getEmployees().get(1), paymentAccountService.getPayAcc(),
                        LocalDate.of(2022, 11, 11), 12, 150.0);

                userService.addPayAcc(paymentAccountService);
                userService.addCreditAcc(creditAccountService);
            }
            bankService.addUser(userService);
            banks.add(bankService);
            users.add(userService);
        }

        System.out.println("Клиент");
        UserService workUser = users.get(0);
        System.out.println(workUser.getUser());
        System.out.println("\nПопытка получения нового кредита");
        Scanner input = new Scanner(System.in);
        System.out.println("Введите сумму кредита: ");
        double loanSum = input.nextDouble();
        System.out.println("Введите количество месяцев: ");
        int countMonth = input.nextInt();
        ArrayList<BankService> banksWithMoney = sortBanksByCriteria(banks, loanSum);
        System.out.println("\nПредложенные банки:");
        for (int i = 0; i < banksWithMoney.size(); i++) {
            if (i != 0) {
                System.out.printf("\nБанк №%d%n", i+1);
            }
            else {
                System.out.printf("Банк №%d%n", i+1);
            }
            System.out.println(banksWithMoney.get(i).getBank());
        }
        System.out.println("\nВыберите из предложенных банков: ");
        int bankID = input.nextInt();
        BankService workBank = banksWithMoney.get(bankID - 1);

        System.out.println("\nПредложенные банковские офисы:");
        for (int i = 0; i < workBank.getBank().getOffices().size(); i++) {
            if (i != 0) {
                System.out.printf("\nОфис №%d%n", i+1);
            }
            else {
                System.out.printf("Офис №%d%n", i+1);
            }
            System.out.println(workBank.getBank().getOffices().get(i));
        }
        System.out.println("\nВыберите из предложенных офисов: ");
        int officeID = input.nextInt();
        BankOffice workOffice = workBank.getBank().getOffices().get(officeID - 1);

        System.out.println("\nПредложенные сотрудники:");
        for (int i = 0; i < workOffice.getEmployees().size(); i++) {
            if (i != 0) {
                System.out.printf("\nСотрудник №%d%n", i+1);
            }
            else {
                System.out.printf("Сотрудник №%d%n", i+1);
            }
            System.out.printf("id %d%n", workOffice.getEmployees().get(i).getId());
            System.out.printf("Имя %s", workOffice.getEmployees().get(i).getName());
            if (workOffice.getEmployees().get(i).getCanLend()) {
                System.out.println("\nМожет выдавать кредиты");
            }
            else {
                System.out.println("\nНе может выдавать кредиты");
            }
        }
        System.out.println("\nВыберите из предложенных сотрудников: ");
        int employeeID = input.nextInt();
        Employee workEmployee = workOffice.getEmployees().get(employeeID);
        //Берём кредит
        PaymentAccountServiceImpl payAcc = new PaymentAccountServiceImpl();
        CreditAccountServiceImpl creditAcc = new CreditAccountServiceImpl();
        workUser.applyForLoan(workBank, workOffice, workEmployee, workOffice.getBankATMS().get(0), loanSum,
                LocalDate.of(2022, 11, 11), countMonth, payAcc, creditAcc);
        System.out.println("Кредит успешно оформлен.");
        int size = workUser.getUser().getCreditAccounts().size();
        System.out.println(workUser.getUser().getCreditAccounts().get(size - 1));
    }

    public static void main(String[] args) throws CredAccUserException, PayAccUserException, OfficeBankException,
            AtmBankException, EmployeeBankException, UserBankException, AtmOfficeException, EmployeeOfficeException,
            CreditExtension, BadUserRatingException {
        mainLab_3();
    }
}