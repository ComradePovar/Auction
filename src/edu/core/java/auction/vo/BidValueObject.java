package edu.core.java.auction.vo;

/**
 * Created by Maxim on 19.02.2017.
 */
public class BidValueObject extends ValueObject {
    public Long buyerId;
    public Long lotId;
    public double amount;

    public BidValueObject() {
    }

    public BidValueObject(Long id, Long lotId, Long buyerId, double amount){
        this.id = id;
        this.lotId = lotId;
        this.buyerId = buyerId;
        this.amount = amount;
    }

    @Override
    public String toString(){
        return "ID: " + id + "\nBuyer ID: " + buyerId + "\nAmount: " + amount;
    }
}
