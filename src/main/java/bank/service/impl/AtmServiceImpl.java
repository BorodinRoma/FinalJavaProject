package bank.service.impl;

import bank.entity.Bank;
import java.util.Objects;
import bank.entity.BankATM;
import bank.entity.Employee;
import bank.entity.BankOffice;
import bank.service.AtmService;
import bank.entity.enums.StatusATM;

public class AtmServiceImpl implements AtmService {
    private BankATM bankATM = null;

    /*Создание экземпляра банкомата на основе входных данных, в виде id, имени, статуса, информации о работе на
     выдачу денег, информации о работе на внесение денег, стоимостью обслуживания, банке, офисе и сотруднике
     банкомата*/
    @Override
    public void create(Integer id, String name, StatusATM status, Boolean workPayMoney, Boolean workDepositMoney,
                       Double maintenanceCost, Bank bank, BankOffice bankOffice, Employee employee) {
        bank.setCountATM(bank.getCountATM() + 1);
        bankOffice.setCountATM(bankOffice.getCountATM() + 1);
        this.bankATM = new BankATM(id, name, status, workPayMoney, workDepositMoney, maintenanceCost, bank,
                bankOffice, employee);
    }

    /*Обновление экземпляра банкомата на основе входных данных, в виде другого экземпляра банкомата*/
    @Override
    public void update(BankATM bankATM) {
        this.bankATM = bankATM;
    }

    /*Обнуление экземпляра банкомата*/
    @Override
    public void delete() {
        this.bankATM = null;
    }

    /*Возврат экземпляра банкомата*/
    @Override
    public BankATM getBankATM() {
        return this.bankATM;
    }

    /*Добавление суммы денег в банкомат, а, соответственно, добавление суммы денег в офис банка
     * и в банк, которому принадлежит данный банкомат, с учётом того, работает ли банкомат. Если он
     * работает, то деньги вносятся и возвращается true, иначе false*/
    @Override
    public Boolean addMoney(Double sumMoney) {
        if (!Objects.equals(this.bankATM.getStatus(), StatusATM.Work)) {
            return Boolean.FALSE;
        }
        this.bankATM.setMoney(this.bankATM.getMoney() + sumMoney);
        this.bankATM.getBankOffice().setMoney(this.bankATM.getBankOffice().getMoney() + sumMoney);
        this.bankATM.getBank().setMoney(this.bankATM.getBank().getMoney() + sumMoney);
        return Boolean.TRUE;
    }

    /*Вычитание суммы денег из банкомата, и, соответственно, вычитание суммы денег из офиса банка и банка,
     * которому принадлежит данный банкомат, с проверкой того, достаточно ли денег в банкомате, чтобы их вычесть.
     * Если не достаточно, то возвращается false, иначе true. И с учётом того, работает ли банкомат и есть ли в
     * нём деньги. Если он работает и в нём есть деньги, то деньги вычитаются и возвращается true, иначе false*/
    @Override
    public Boolean subtractMoney(Double sumMoney) {
        if ((Objects.equals(this.bankATM.getStatus(), StatusATM.NotWork)) || (Objects.equals(this.bankATM.getStatus(),
                StatusATM.NoMoney)) || (this.bankATM.getMoney() < sumMoney))
            return Boolean.FALSE;
        if (Objects.equals(this.bankATM.getMoney(), sumMoney))
            this.bankATM.setStatus(StatusATM.NoMoney);
        this.bankATM.setMoney(this.bankATM.getMoney() - sumMoney);
        this.bankATM.getBankOffice().setMoney(this.bankATM.getBankOffice().getMoney() - sumMoney);
        this.bankATM.getBank().setMoney(this.bankATM.getBank().getMoney() - sumMoney);
        return Boolean.TRUE;
    }
}
