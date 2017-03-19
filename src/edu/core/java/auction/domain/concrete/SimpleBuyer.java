package edu.core.java.auction.domain.concrete;

import edu.core.java.auction.domain.interfaces.Buyer;
import edu.core.java.auction.domain.interfaces.Person;
import edu.core.java.auction.domain.interfaces.DomainObject;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashSet;

/**
 * Created by Maxim on 19.02.2017.
 */
public class SimpleBuyer extends DomainObject
                         implements Person, Buyer {

    private String name;
    private double accountBalance;
    private HashSet<Bid> bids = new HashSet<Bid>();

    public SimpleBuyer(Long id){
        this.id = id;
    }
    public SimpleBuyer(Long id, String name, double accountBalance){
        this.id = id;
        this.name = name;
        this.accountBalance = accountBalance;
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
    public HashSet<Bid> getBids() {
        return bids;
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
    public void setAccountBalance(double amount) {
        accountBalance = amount;
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
    public Long getId(){
        return id;
    }
}
