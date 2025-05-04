package com.example.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository // for component scanning apperentlly
public class MembershipDAOImpl implements MembershipDAO{

    @Override
    public boolean addSillyMember() {
        System.out.println(getClass() + " : DOING MY DB WORK : ADDING A MEMBERSHIP ACCOUNT");

        return true;
    }

    @Override
    public void goToSleep() {
        System.out.println(getClass() + ": im going to sleep right now");
    }
    
}
