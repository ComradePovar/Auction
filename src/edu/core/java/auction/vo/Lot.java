package edu.core.java.auction.vo;


import java.util.Date;

/**
 * Created by Maxim on 19.02.2017.
 */
public class Lot extends ValueObject {
    public Long currentBidId;
    public Long auctioneerId;
    public double startPrice;
    public Date startDate;
    public Date endDate;
    public Long productId;

    public Lot(double startPrice, Date startDate, Date endDate, Long productId, Long auctioneerId){
        this.startDate = startDate;
        this.endDate = endDate;
        this.startPrice = startPrice;
        this.auctioneerId = auctioneerId;
        this.productId = productId;
        this.id = ++maxId;
    }
}
