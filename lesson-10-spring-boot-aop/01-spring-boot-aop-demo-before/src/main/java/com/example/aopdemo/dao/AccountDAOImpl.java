package com.example.aopdemo.dao;

import org.springframework.stereotype.Repository;

import com.example.aopdemo.Account;

@Repository // for component scanning apperentlly
public class AccountDAOImpl implements AccountDAO{

    @Override
    public void addAccount(Account theAccount, boolean trouv) {
        System.out.println(getClass() + " : DOING MY DB WORK : ADDING AN ACCOUNT");
    }

    @Override
    public boolean doWork() {

        System.out.println(getClass() + " : doWork()");
        return true;
    }
    
}
