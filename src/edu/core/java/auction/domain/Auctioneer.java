package edu.core.java.auction.domain;

import java.util.HashSet;

/**
 * Created by Maxim on 19.02.2017.
 */
public class Auctioneer extends DomainObject {
    private HashSet<Lot> lots = new HashSet<Lot>();
    private String name;

    public Auctioneer(Long id){
        this.id = id;
    }
    public Auctioneer(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public HashSet<Lot> getLots() {
        return lots;
    }
}
