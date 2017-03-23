package edu.core.java.auction.vo;


import java.util.Date;

/**
 * Created by Maxim on 19.02.2017.
 */
public class LotValueObject extends ValueObject {
    public Long currentBidId;
    public Long auctioneerId;
    public double startPrice;
    public Date startDate;
    public Date endDate;
    public Long productId;
}
