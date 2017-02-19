package edu.core.java.auction.domain.concrete;

import edu.core.java.auction.domain.interfaces.DomainObject;
import edu.core.java.auction.domain.interfaces.Product;

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

    public Lot(double startPrice, Date startDate, Date endDate, Product product){
        this.startDate = startDate;
        this.endDate = endDate;
        this.startPrice = startPrice;
    }

    public double getStartPrice(){
        return startPrice;
    }

    public void setCurrentBid(Bid currentBid) {
        this.currentBid = currentBid;
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
}
