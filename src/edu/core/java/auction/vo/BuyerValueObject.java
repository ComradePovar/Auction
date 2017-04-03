package edu.core.java.auction.vo;


/**
 * Created by Maxim on 19.02.2017.
 */
public class BuyerValueObject extends ValueObject{
    public String name;
    public double accountBalance;

    public BuyerValueObject() {
    }

    public BuyerValueObject(Long id, String name, double accountBalance){
        this.id = id;
        this.name = name;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString(){
        return "ID: " + id + "\nName: " + name + "\nAccount balance: " + accountBalance ;
    }
}
