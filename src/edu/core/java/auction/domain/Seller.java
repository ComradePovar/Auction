package edu.core.java.auction.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Maxim on 19.02.2017.
 */
public class Seller extends Person {
    protected double comissionPercentage;
    private HashSet<Product> products = new HashSet<Product>();

    public Seller(Long id){
        this.id = id;
    }
    public Seller(Long id, String name, double accountBalance, double comissionPercentage){
        this.id = id;
        this.name = name;
        this.accountBalance = accountBalance;
        this.comissionPercentage = comissionPercentage;
    }

    public void setComissionPercentage(double value) {
        this.comissionPercentage = value;
    }

    public double getComissionPercentage() {
        return comissionPercentage;
    }

    public Set<Product> getProducts() {
        return products;
    }
}
