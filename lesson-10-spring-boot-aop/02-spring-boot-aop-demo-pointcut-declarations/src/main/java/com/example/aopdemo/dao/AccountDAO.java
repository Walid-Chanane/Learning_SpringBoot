package com.example.aopdemo.dao;

import java.util.List;

import com.example.aopdemo.Account;

public interface AccountDAO {
    
    void addAccount(Account theAccount, boolean trouv);

    boolean doWork();

    String getName();

    void setName(String name);

    String getServiceCode();

    void setServiceCode(String serviceCode);

    List<Account> findAccounts();

    List<Account> findAccounts(boolean tripwire);
}
