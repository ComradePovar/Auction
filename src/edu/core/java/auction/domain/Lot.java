package edu.core.java.auction.domain;

import java.util.Date;

/**
 * Created by Maxim on 19.02.2017.
 */
public class Lot extends DomainObject {
    private double currentPrice;
    private Date endDate;
    private Product product;

    public Lot(Long id, Date endDate, Product product, double currentPrice){
        this.id = id;
        this.endDate = endDate;
        this.product = product;
        this.currentPrice = currentPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
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
