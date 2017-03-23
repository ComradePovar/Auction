package edu.core.java.auction.domain;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Maxim on 19.02.2017.
 */
public abstract class Person extends DomainObject {
    protected String name;
    protected double accountBalance;

    public String getName(){
        return name;
    }

    public double getAccountBalance(){
        return accountBalance;
    }

    public void addMoney(double amount){
        accountBalance += amount;
    }

    public void withdrawMoney(double amount){
        if (accountBalance < amount)
            throw new NotImplementedException();
        accountBalance -= amount;
    }

    public void setName(String value){
        name = value;
    }
}
