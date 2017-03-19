package edu.core.java.auction.vo;

import edu.core.java.auction.domain.interfaces.Person;

import java.util.ArrayList;

/**
 * Created by Maxim on 19.02.2017.
 */
public class SimpleSeller extends ValueObject {
    public String name;
    public double accountBalance;
    public double comissionPercentage;

    public SimpleSeller(){
        this.id = ++maxId;
    }
    public SimpleSeller(String name, double accountBalance, double comissionPercentage){
        this.name = name;
        this.accountBalance = accountBalance;
        this.comissionPercentage = comissionPercentage;
        this.id = ++maxId;
    }
}
