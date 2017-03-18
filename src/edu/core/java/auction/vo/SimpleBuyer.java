package edu.core.java.auction.vo;

import edu.core.java.auction.domain.interfaces.Person;

import java.util.ArrayList;

/**
 * Created by Maxim on 19.02.2017.
 */
public class SimpleBuyer extends ValueObject{
    public String name;
    public String phone;
    public double accountBalance;
    public ArrayList<Long> bids;

    public SimpleBuyer(){
        this.id = ++maxId;
    }

    public SimpleBuyer(String name, String phone, double accountBalance){
        this.name = name;
        this.phone = phone;
        this.accountBalance = accountBalance;
        this.id = ++maxId;
    }
}
