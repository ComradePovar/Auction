package edu.core.java.auction.vo;

import java.util.ArrayList;

/**
 * Created by Maxim on 19.02.2017.
 */
public class SimpleAuctioneer extends ValueObject {
    public String name;

    public SimpleAuctioneer(){
        this.id = ++maxId;
    }
    public SimpleAuctioneer(String name){
        this.name = name;
        this.id = ++maxId;
    }
}
