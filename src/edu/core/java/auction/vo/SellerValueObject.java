package edu.core.java.auction.vo;

/**
 * Created by Maxim on 19.02.2017.
 */
public class SellerValueObject extends ValueObject {
    public String name;
    public double accountBalance;
    public double commissionPercentage;

    public SellerValueObject() {
    }

    public SellerValueObject(Long id, String name, double accountBalance, double commissionPercentage){
        this.id = id;
        this.name = name;
        this.accountBalance = accountBalance;
        this.commissionPercentage = commissionPercentage;
    }

    @Override
    public String toString(){
        return "ID: " + id + "\nName: " + name + "\nAccount balance: " + accountBalance + "\nCommission percentage: " + commissionPercentage;
    }
}
