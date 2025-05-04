package com.example.aopdemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.aopdemo.Account;

@Repository // for component scanning apperentlly
public class AccountDAOImpl implements AccountDAO{

    private String name;

    private String serviceCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    @Override
    public void addAccount(Account theAccount, boolean trouv) {
        System.out.println(getClass() + " : DOING MY DB WORK : ADDING AN ACCOUNT");
    }

    @Override
    public boolean doWork() {

        System.out.println(getClass() + " : doWork()");
        return true;
    }

    @Override
    public List<Account> findAccounts() {
       return findAccounts(false);   //if tripwire = true it will throw an exception
       //but since we this one for afterreturing, we dont need an exception, so tripwire=false to use the normal method
    }

    @Override
    public List<Account> findAccounts(boolean tripwire) {
        //for academic purposess ... let's simulate an exception
        if(tripwire){
            throw new RuntimeException("GO SLEEP!!");
        }
            
            List<Account> myAccounts = new ArrayList<>();

            //create sample accounts         sample = echantillon
            Account tempAccount = new Account("lupin", "gold");
            Account tempAccount2 = new Account("mike", "diamond");
            Account tempAccount3 = new Account("madhu", "platinum");

            //add them to our accounts list
            myAccounts.add(tempAccount);
            myAccounts.add(tempAccount2);
            myAccounts.add(tempAccount3);
        
        return myAccounts;
    }
}
