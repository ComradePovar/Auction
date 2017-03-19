package edu.core.java.auction.domain.concrete;

import edu.core.java.auction.domain.interfaces.Auctioneer;
import edu.core.java.auction.domain.interfaces.DomainObject;
import edu.core.java.auction.domain.interfaces.Product;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Date;
import java.util.Timer;

/**
 * Created by Maxim on 19.02.2017.
 */
public class Lot extends DomainObject {
    private Bid currentBid;
    private Auctioneer auctioneer;
    private double startPrice;
    private Date startDate;
    private Date endDate;
    private Product product;

    public Lot(double startPrice, Date startDate, Date endDate, Product product, Auctioneer auctioneer){
        this.startDate = startDate;
        this.endDate = endDate;
        this.startPrice = startPrice;
        this.auctioneer = auctioneer;
        this.product = product;
    }

    public double getStartPrice(){
        return startPrice;
    }

    public void setCurrentBid(Bid bid) {
        if (currentBid == null) {
            if (startPrice <= bid.getBidAmount())
                this.currentBid = bid;
        } else {
            if (bid.getBidAmount() > currentBid.getBidAmount())
                this.currentBid = bid;
            else
                throw new NotImplementedException();
        }
    }

    public Bid getCurrentBid() {
        return currentBid;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Product getProduct() {
        return product;
    }

    public Date getStartDate(){
        return startDate;
    }

    public Auctioneer getAuctioneer(){
        return auctioneer;
    }
}
