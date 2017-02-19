package edu.core.java.auction.vo;

import java.util.ArrayList;

/**
 * Created by Maxim on 19.02.2017.
 */
public class SimpleBuyer extends ValueObject{
    public String name;
    public String phone;
    public double accountBalance;
    public ArrayList<Long> bids;
}
