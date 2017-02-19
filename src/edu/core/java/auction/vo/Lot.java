package edu.core.java.auction.vo;


import java.util.Date;

/**
 * Created by Maxim on 19.02.2017.
 */
public class Lot extends ValueObject {
    public Long currentBidId;
    public double startPrice;
    public Date startDate;
    public Date endDate;
    public Long productId;
}
