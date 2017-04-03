package edu.core.java.auction.domain;

import java.util.Date;

/**
 * Created by Maxim on 19.02.2017.
 */
public class Lot extends DomainObject {
    private Bid currentBid;
    private double startPrice;
    private Date endDate;
    private Product product;

    public Lot(Long id, double startPrice, Date endDate, Product product, Bid bid){
        this.id = id;
        this.endDate = endDate;
        this.startPrice = startPrice;
        this.product = product;
        this.currentBid = bid;
    }

    public double getStartPrice(){
        return startPrice;
    }

    public Bid getCurrentBid() {
        return currentBid;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date date){
        this.endDate = date;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product){
        this.product = product;
    }
}
