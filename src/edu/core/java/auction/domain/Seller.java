package edu.core.java.auction.domain;

/**
 * Created by Maxim on 19.02.2017.
 */
public class Seller extends Person {
    private double commissionPercentage;

    public Seller(Long id, String name, double accountBalance, double commissionPercentage){
        this.id = id;
        this.name = name;
        this.accountBalance = accountBalance;
        this.commissionPercentage = commissionPercentage;
    }

    public void setCommissionPercentage(double value) {
        this.commissionPercentage = value;
    }

    public double getCommissionPercentage() {
        return commissionPercentage;
    }
}
