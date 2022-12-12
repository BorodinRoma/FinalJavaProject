package bank.service.impl;

import bank.entity.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Random;
import bank.exceptions.*;
import java.util.Objects;
import java.util.ArrayList;
import java.time.LocalDate;
import com.google.gson.Gson;
import bank.service.UserService;
import bank.service.BankService;
import bank.entity.enums.StatusATM;
import bank.entity.enums.StatusOffice;
import com.google.gson.reflect.TypeToken;
import bank.service.CreditAccountService;
import bank.service.PaymentAccountService;
import bank.entity.jsonClasses.JsonPayAcc;
import bank.entity.jsonClasses.JsonCreditAcc;

public class UserServiceImpl implements UserService {
    private User user = null;
    Gson gson = new Gson();
    Type payAccArrType = new TypeToken<ArrayList<JsonPayAcc>>() {}.getType();
    Type credAccArrType = new TypeToken<ArrayList<JsonCreditAcc>>() {}.getType();

    /*Создание экземпляра пользователя на основе входных данных, в виде id, имени, фамилии, даты рождения и работы
    пользователя*/
    @Override
    public void create(Integer id, String name, String surname, LocalDate birthDay, String work) {
        this.user = new User(id, name, surname, birthDay, work);
        calcSalary();
        calcCreditRating();
    }

    /*Обновление экземпляра пользователя на основе входных данных, в виде другого экземпляра пользователя*/
    @Override
    public void update(User user) {
        this.user = user;
    }

    /*Обнуление экземпляра пользователя*/
    @Override
    public void delete() {
        this.user = null;
    }

    /*Возврат экземпляра пользователя*/
    @Override
    public User getUser() {
        return this.user;
    }

    /*Расчёт зарплаты пользователя рандомным способом*/
    private void calcSalary() {
        Random random = new Random();
        user.setMonthSalary(random.nextDouble(1, 10000));
    }

    /*Расчёт кредитного рейтинга пользователя на основе его зарплаты*/
    private void calcCreditRating() {
        int creditRating = 0;
        Integer startRat = 0;
        Integer endRat = 1000;
        while ((startRat != 10000) && (creditRating == 0)) {
            if ((user.getMonthSalary() <= endRat) && (user.getMonthSalary() >= startRat))
                creditRating = endRat / 10;
            else {
                startRat += 1000;
                endRat += 1000;
            }
        }
        user.setCreditRating(creditRating);
    }

    /*Добавление банка в список банков клиента*/
    @Override
    public void addBank(Bank bank) {
        ArrayList<Bank> banks = this.user.getBanks();
        banks.add(bank);
        this.user.setBanks(banks);
    }

    /*Удаление банка из списка банков клиента*/
    @Override
    public void delBank(Bank bank) {
        ArrayList<Bank> banks = this.user.getBanks();
        banks.remove(bank);
        this.user.setBanks(banks);
    }

    /*Добавление кредитного счёта в список кредитных счетов клиента*/
    @Override
    public void addCreditAcc(CreditAccountService creditAcc) throws CredAccUserException {
        if (!Objects.equals(creditAcc.getCreditAcc().getUser(), this.user))
            throw new CredAccUserException();
        ArrayList<CreditAccount> creditAccounts = this.user.getCreditAccounts();
        creditAccounts.add(creditAcc.getCreditAcc());
        this.user.setCreditAccounts(creditAccounts);
        creditAcc.getCreditAcc().setUser(this.user);
    }

    /*Удаление кредитного счёта из списка кредитных счетов клиента*/
    @Override
    public void delCreditAcc(CreditAccountService creditAcc) throws CredAccUserException {
        if (!Objects.equals(creditAcc.getCreditAcc().getUser(), this.user))
            throw new CredAccUserException();
        ArrayList<CreditAccount> creditAccounts = this.user.getCreditAccounts();
        creditAccounts.remove(creditAcc.getCreditAcc());
        this.user.setCreditAccounts(creditAccounts);
        creditAcc.getCreditAcc().setUser(this.user);
    }

    /*Добавление платёжного счёта в список платёжных счетов клиента*/
    @Override
    public void addPayAcc(PaymentAccountService payAcc) throws PayAccUserException {
        if (!Objects.equals(payAcc.getPayAcc().getUser(), this.user))
            throw new PayAccUserException();
        ArrayList<PaymentAccount> paymentAccounts = this.user.getPaymentAccounts();
        paymentAccounts.add(payAcc.getPayAcc());
        this.user.setPaymentAccounts(paymentAccounts);
        payAcc.getPayAcc().setUser(this.user);
    }

    /*Удаление платёжного счёта из списка платёжных счетов клиента*/
    @Override
    public void delPayAcc(PaymentAccountService payAcc) throws PayAccUserException {
        if (!Objects.equals(payAcc.getPayAcc().getUser(), this.user))
            throw new PayAccUserException();
        ArrayList<PaymentAccount> paymentAccounts = this.user.getPaymentAccounts();
        paymentAccounts.remove(payAcc.getPayAcc());
        this.user.setPaymentAccounts(paymentAccounts);
        payAcc.getPayAcc().setUser(this.user);
    }

    @Override
    public String toString() {
        StringBuilder returnStr = new StringBuilder(this.user.toString());
        returnStr.append("\n\nПлатёжные счета клиента:");
        for (int i_1 = 0; i_1 < this.user.getPaymentAccounts().size(); i_1++) {
            returnStr.append(String.format("\n\n#№%d\n", i_1));
            returnStr.append(user.getPaymentAccounts().get(i_1).toString());
        }
        returnStr.append("\n\nКредитные счета клиента:");
        for (int i_1 = 0; i_1 < this.user.getCreditAccounts().size(); i_1++) {
            returnStr.append(String.format("\n\n#№%d\n", i_1));
            returnStr.append(user.getCreditAccounts().get(i_1).toString());
        }
        return returnStr.toString();
    }

    /*Попытка получения кредита пользователем у одного из банков*/
    @Override
    public void applyForLoan(BankService bank, BankOffice workOffice, Employee workEmployee, BankATM atm,
                             Double loanSum, LocalDate startDate, Integer countMonth, PaymentAccountService payAcc,
                             CreditAccountService creditAcc) throws CreditExtension, BadUserRatingException,
            PayAccUserException, UserBankException, CredAccUserException {
        if (this.user.getCreditRating()/10 < bank.getBank().getRating()) {
            throw new BadUserRatingException(bank.getBank().getRating(),
                    this.user.getCreditRating()/10);
        }
        if (workOffice.getStatus() != StatusOffice.Work)
            throw new CreditExtension("Выбранный офис не работает");
        if (workOffice.getMoney() < loanSum)
            throw new CreditExtension("В выбранном офисе недостаточно денег");
        if (atm.getStatus() != StatusATM.Work)
            throw new CreditExtension("Банкомат, в выбранном офисе, не работает");
        if (atm.getMoney() < loanSum)
            throw new CreditExtension("В выбранном банкомате недостаточно денег");
        if (!workEmployee.getCanLend())
            throw new CreditExtension("Выбранный сотрудник не может выдавать кредиты");

        if (!bank.getBank().getClients().contains(this.user)) {
            payAcc.create(100, this.user, bank.getBank());
            this.addPayAcc(payAcc);
            bank.addUser(this);
        }
        else {
            payAcc.update(this.getPayAccByBank(bank.getBank()));
        }
        creditAcc.create(100, this.user, bank.getBank(), workEmployee, payAcc.getPayAcc(), startDate,
                countMonth, loanSum);
        this.addCreditAcc(creditAcc);
    }

    private PaymentAccount getPayAccByBank(Bank bank) {
        for (int i = 0; i < this.user.getPaymentAccounts().size(); i++) {
            if (Objects.equals(this.user.getPaymentAccounts().get(i).getBank().getId(), bank.getId())) {
                return this.user.getPaymentAccounts().get(i);
            }
        }
        return null;
    }

    @Override
    public void saveToFile(String fileName, BankService bank) throws IOException {
        String payAccStr = gson.toJson(this.makeJsonPayAcc(bank.getBank().getId()));
        String creditAccStr = gson.toJson(this.makeJsonCreditAcc(bank.getBank().getId()));
        File file = new File(fileName);
        FileWriter writer = new FileWriter(file);
        writer.write("Платёжные счета:\n" + payAccStr + "\n\nКредитные счета:\n" + creditAccStr);
        writer.close();
    }

    @Override
    public void updateFromFile(String fileName) throws IOException {
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);
        String line = reader.readLine();
        boolean first = true;
        while (line != null) {
            if (!line.isEmpty()) {
                if (line.charAt(0) == '[') {
                    if (first) {
                        first = false;
                        this.makePayAccFromJson(gson.fromJson(line, payAccArrType));
                    } else {
                        this.makeCreditAccFromJson(gson.fromJson(line, credAccArrType));
                    }
                }
            }
            line = reader.readLine();
        }
    }

    /*Смена пользователем работы, а соответственно, и заработной платы, и пересчёт его кредитного рейтинга*/
    @Override
    public void changeWork(String newWork, Double newMonthSalary) {
        user.setWork(newWork);
        user.setMonthSalary(newMonthSalary);
        int creditRating = 0;
        int startRat = 0;
        int endRat = 1000;
        while ((startRat != 10000) && (creditRating == 0)) {
            if ((newMonthSalary <= endRat) && (newMonthSalary >= startRat))
                creditRating = endRat / 10;
            else {
                startRat += 1000;
                endRat += 1000;
            }
        }
        user.setCreditRating(creditRating);
    }

    private ArrayList<JsonPayAcc> makeJsonPayAcc(Integer bankID) {
        ArrayList<JsonPayAcc> jsonPay = new ArrayList<>();
        for (PaymentAccount paymentAccount : user.getPaymentAccounts()) {
            if (Objects.equals(paymentAccount.getBank().getId(), bankID)) {
                jsonPay.add(new JsonPayAcc(paymentAccount));
            }
        }
        return jsonPay;
    }

    private void makePayAccFromJson(ArrayList<JsonPayAcc> jsonPayAcc) {
        ArrayList<PaymentAccount> payAcc = this.user.getPaymentAccounts();
        if (!jsonPayAcc.isEmpty()) {
            for (int i = 0; i < payAcc.size(); i++) {
                for (int j = 0; j < payAcc.size(); j++) {
                    if (Objects.equals(payAcc.get(i).getId(), jsonPayAcc.get(j).getId())) {
                        payAcc.get(i).updateFromJsonClass(jsonPayAcc.get(j));
                    }
                }
            }
            this.user.setPaymentAccounts(payAcc);
        }
    }

    private ArrayList<JsonCreditAcc> makeJsonCreditAcc(Integer bankID) {
        ArrayList<JsonCreditAcc> jsonCredit = new ArrayList<>();
        for (CreditAccount creditAccount : user.getCreditAccounts()) {
            if (Objects.equals(creditAccount.getBank().getId(), bankID)) {
                jsonCredit.add(new JsonCreditAcc(creditAccount));
            }
        }
        return jsonCredit;
    }

    private void makeCreditAccFromJson(ArrayList<JsonCreditAcc> jsonCreditAcc) {
        ArrayList<CreditAccount> creditAccounts = this.user.getCreditAccounts();
        if (!jsonCreditAcc.isEmpty()) {
            for (int i = 0; i < creditAccounts.size(); i++) {
                for (int j = 0; j < creditAccounts.size(); j++) {
                    if (Objects.equals(creditAccounts.get(i).getId(), jsonCreditAcc.get(j).getId())) {
                        creditAccounts.get(i).updateFromJsonClass(jsonCreditAcc.get(j));
                    }
                }
            }
            this.user.setCreditAccounts(creditAccounts);
        }
    }
}