package edu.core.java.auction.vo;

import edu.core.java.auction.domain.interfaces.Buyer;

/**
 * Created by Maxim on 19.02.2017.
 */
public class Bid extends ValueObject {
    public Long buyerId;
    public double amount;

    public Bid(Long buyerId, double bidAmount){
        this.buyerId = buyerId;
        amount = bidAmount;
        this.id = ++maxId;
    }
}
