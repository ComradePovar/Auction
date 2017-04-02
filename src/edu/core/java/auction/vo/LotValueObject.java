package edu.core.java.auction.vo;


import java.util.Date;

/**
 * Created by Maxim on 19.02.2017.
 */
public class LotValueObject extends ValueObject {
    public Long currentBidId;
    public double startPrice;
    public Date endDate;
    public Long productId;

    public LotValueObject() {
    }

    public LotValueObject(Long id, double startPrice, Date endDate,
                          Long productId, Long currentBidId){
        this.id = id;
        this.currentBidId = currentBidId;
        this.startPrice = startPrice;
        this.endDate = endDate;
        this.productId = productId;
    }

    @Override
    public String toString(){
        return "ID: " + id + "\nCurrent Bid ID: " + currentBidId + "\nStart price: " + startPrice +
                "\nEnd date: " + endDate + "\nProduct ID: " + productId;
    }
}
