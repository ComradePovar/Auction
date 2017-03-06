package edu.core.java.auction.domain.concrete;

import edu.core.java.auction.domain.interfaces.Buyer;
import edu.core.java.auction.domain.interfaces.Person;
import edu.core.java.auction.domain.interfaces.DomainObject;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Maxim on 19.02.2017.
 */
public class SimpleBuyer extends DomainObject
                         implements Person, Buyer {

    private String name;
    private String phone;
    private double accountBalance;
    private Set<Bid> bids;

    public SimpleBuyer(String name){
        bids = new HashSet<Bid>();
    }
    @Override
    public void createBid(Lot lot, double bidAmount) {
        throw new NotImplementedException();
    }

    @Override
    public void removeBid(Long bidId) {
        throw new NotImplementedException();
    }

    @Override
    public Set<Bid> getBids() {
        return bids;
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
}
