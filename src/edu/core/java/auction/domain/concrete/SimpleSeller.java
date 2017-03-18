package edu.core.java.auction.domain.concrete;

import edu.core.java.auction.domain.interfaces.Person;
import edu.core.java.auction.domain.interfaces.DomainObject;
import edu.core.java.auction.domain.interfaces.Product;
import edu.core.java.auction.domain.interfaces.Seller;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Set;

/**
 * Created by Maxim on 19.02.2017.
 */
public class SimpleSeller extends DomainObject
                          implements Person, Seller {
    private String name;
    private String phone;
    private double accountBalance;
    private double comissionPercentage;
    private Set<Product> products;

    public SimpleSeller(Long id){
        this.id = id;
    }
    public SimpleSeller(Long id, String name, String phone, double accountBalance){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.accountBalance = accountBalance;
    }

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
    public Set<Product> getProducts() {
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

    @Override
    public Long getId(){
        return id;
    }
}
