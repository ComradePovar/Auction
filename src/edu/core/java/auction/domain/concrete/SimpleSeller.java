package edu.core.java.auction.domain.concrete;

import edu.core.java.auction.domain.interfaces.Client;
import edu.core.java.auction.domain.interfaces.DomainObject;
import edu.core.java.auction.domain.interfaces.Product;
import edu.core.java.auction.domain.interfaces.Seller;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

/**
 * Created by Maxim on 19.02.2017.
 */
public class SimpleSeller extends DomainObject
                          implements Client, Seller {
    private String name;
    private String phone;
    private double accountBalance;
    private double comissionPercentage;
    private ArrayList<Product> products;


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPhone() {
        return phone;
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
    public void setPhone(String value) {
        this.phone = value;
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
    public ArrayList<Product> getProducts() {
        return products;
    }

    @Override
    public void addProduct(Product product) {
        throw new NotImplementedException();
    }

    @Override
    public void removeProduct(Long productId) {
        throw new NotImplementedException();
    }
}
