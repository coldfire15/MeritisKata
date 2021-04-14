package com.ammouri.meritis.Bank.entities;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Account implements Serializable {
    private Long account_id;
    private Long balance;
    private Long amount;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date;
    @ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    private Client account_owner;

    public Account(Long account_id, Long balance, Long amount, Date date,Client account_owner) {
        this.account_id = account_id;
        this.balance = balance;
        this.amount = amount;
        this.date = date;
        this.account_owner = account_owner;
    }

    public Account() {
    }

    public Long getAccount_id() {
        return account_id;
    }

    public Long getBalance() {
        return balance;
    }

    public Long getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Client getAccount_owner() {
        return account_owner;
    }

    public void setAccount_owner(Client account_owner) {
        this.account_owner = account_owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(account_id, account.account_id) && Objects.equals(balance, account.balance) && Objects.equals(amount, account.amount) && Objects.equals(date, account.date) && Objects.equals(account_owner, account.account_owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account_id, balance, amount, date, account_owner);
    }

    @Override
    public String toString() {
        return "Account{" +
                "account_id=" + account_id +
                ", balance=" + balance +
                ", amount=" + amount +
                ", date=" + date +
                ", account_owner=" + account_owner +
                '}';
    }
}
