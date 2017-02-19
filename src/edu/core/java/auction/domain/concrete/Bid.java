package edu.core.java.auction.domain.concrete;

import edu.core.java.auction.domain.interfaces.Buyer;
import edu.core.java.auction.domain.interfaces.DomainObject;

/**
 * Created by Maxim on 19.02.2017.
 */
public class Bid extends DomainObject {
    private Buyer buyer;
    private double bidAmount;

    public Bid(Buyer buyer, double bidAmount){
        this.buyer = buyer;
        this.bidAmount = bidAmount;
    }

    public Buyer getBuyer(){
        return buyer;
    }

    public double getBidAmount(){
        return bidAmount;
    }
}
