package edu.core.java.auction.domain.interfaces;

import edu.core.java.auction.domain.concrete.Lot;

import java.util.HashSet;

/**
 * Created by Maxim on 19.02.2017.
 */
public interface Auctioneer {
    String getName();
    void setName(String name);
    HashSet<Lot> getLots();
    Long getId();
}
