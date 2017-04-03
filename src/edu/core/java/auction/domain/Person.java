package edu.core.java.auction.domain;

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
    public void setAccountBalance(double value){this.accountBalance = value;}
    public void setName(String value){
        name = value;
    }
}
