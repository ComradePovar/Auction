package edu.core.java.auction.domain;

/**
 * Created by Maxim on 19.02.2017.
 */
public class Bid extends DomainObject {
    private Buyer buyer;
    private double bidAmount;

    public Bid(Long id, Buyer buyer, double bidAmount){
        this.id = id;
        this.buyer = buyer;
        this.bidAmount = bidAmount;
    }

    public Buyer getBuyer(){
        return buyer;
    }

    public void setBuyer(Buyer buyer){
        this.buyer = buyer;
    }

    public double getBidAmount(){
        return bidAmount;
    }

    public void setBidAmount(double bidAmount){
        this.bidAmount = bidAmount;
    }
}
