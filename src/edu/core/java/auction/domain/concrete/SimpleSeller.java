package edu.core.java.auction.domain.concrete;

import edu.core.java.auction.domain.interfaces.Person;
import edu.core.java.auction.domain.interfaces.DomainObject;
import edu.core.java.auction.domain.interfaces.Product;
import edu.core.java.auction.domain.interfaces.Seller;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Maxim on 19.02.2017.
 */
public class SimpleSeller extends DomainObject
                          implements Person, Seller {
    private String name;
    private double accountBalance;
    private double comissionPercentage;
    private HashSet<Product> products = new HashSet<Product>();

    public SimpleSeller(Long id){
        this.id = id;
    }
    public SimpleSeller(Long id, String name, double accountBalance, double comissionPercentage){
        this.id = id;
        this.name = name;
        this.accountBalance = accountBalance;
        this.comissionPercentage = comissionPercentage;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getAccountBalance() {
        return accountBalance;
    }

    @Override
    public void addMoney(double amount) {
        accountBalance += amount;
    }

    @Override
    public void withdrawMoney(double amount) {
        if (accountBalance < amount){
            throw new NotImplementedException();
        }
        accountBalance -= amount;
    }

    @Override
    public void setName(String value) {
        this.name = value;
    }

    @Override
    public void setComissionPercentage(double value) {
        this.comissionPercentage = value;
    }

    @Override
    public double getComissionPercentage() {
        return comissionPercentage;
    }

    @Override
    public Set<Product> getProducts() {
        return products;
    }

    @Override
    public void setAccountBalance(double amount) {
        accountBalance = amount;
    }
}
