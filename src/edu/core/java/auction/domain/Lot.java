package edu.core.java.auction.domain;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Date;

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

    public Lot(double startPrice, Date startDate, Date endDate, Product product, Auctioneer auctioneer, Bid bid){
        this.startDate = startDate;
        this.endDate = endDate;
        this.startPrice = startPrice;
        this.auctioneer = auctioneer;
        this.product = product;
        this.currentBid = bid;
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
