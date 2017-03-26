package edu.core.java.auction.domain;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Date;

/**
 * Created by Maxim on 19.02.2017.
 */
public class Lot extends DomainObject {
    private Bid currentBid;
    private double startPrice;
    private Date startDate;
    private Date endDate;
    private Product product;

    public Lot(Long id, double startPrice, Date startDate, Date endDate, Product product, Bid bid){
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startPrice = startPrice;
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

    public void setStartDate(Date date){
        this.startDate = date;
    }

    public void setEndDate(Date date){
        this.endDate = date;
    }

    public Product getProduct() {
        return product;
    }

    public Date getStartDate(){
        return startDate;
    }

    public void setProduct(Product product){
        this.product = product;
    }
}
