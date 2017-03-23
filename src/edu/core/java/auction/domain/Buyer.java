package edu.core.java.auction.domain;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashSet;

/**
 * Created by Maxim on 19.02.2017.
 */
public class Buyer extends Person {
    private HashSet<Bid> bids = new HashSet<Bid>();

    public Buyer(Long id){
        this.id = id;
    }
    public Buyer(Long id, String name, double accountBalance){
        this.id = id;
        this.name = name;
        this.accountBalance = accountBalance;
    }

    public void createBid(Lot lot, double bidAmount) {
        throw new NotImplementedException();
    }

    public void removeBid(Long bidId) {
        throw new NotImplementedException();
    }

    public HashSet<Bid> getBids() {
        return bids;
    }
}
