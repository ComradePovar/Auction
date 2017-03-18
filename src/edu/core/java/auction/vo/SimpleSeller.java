package edu.core.java.auction.vo;

import edu.core.java.auction.domain.interfaces.Person;

import java.util.ArrayList;

/**
 * Created by Maxim on 19.02.2017.
 */
public class SimpleSeller extends ValueObject {
    public String name;
    public String phone;
    public double accountBalance;
    public double comissionPercentage;
    public ArrayList<Long> products;

    public SimpleSeller(){
        this.id = ++maxId;
    }
    public SimpleSeller(String name, String phone, double accountBalance){
        this.name = name;
        this.phone = phone;
        this.accountBalance = accountBalance;
        this.id = ++maxId;
    }
}
