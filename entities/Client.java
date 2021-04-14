package com.ammouri.meritis.Bank.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Client implements Serializable {
    private String history;
    private Long client_id;
    private String name;
    private String last_name;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthday;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "accountOwner")
    private List<Account> client_accounts;

    public Client(Long client_id, String name, String last_name, Date birthday, List<Account> client_accounts) {
        this.client_id = client_id;
        this.name = name;
        this.last_name = last_name;
        this.birthday = birthday;
        this.client_accounts = client_accounts;
    }

    public Client(Long client_id, String name, String last_name, Date birthday) {
        this.client_id = client_id;
        this.name = name;
        this.last_name = last_name;
        this.birthday = birthday;

    }


    public Client() {
    }

    public Long getClient_id() {
        return client_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<Account> getClient_accounts() {
        return client_accounts;
    }

    public void setClient_accounts(List<Account> client_accounts) {
        this.client_accounts = client_accounts;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }
}
