package edu.core.java.auction.domain.interfaces;

import edu.core.java.auction.domain.concrete.Lot;

import java.util.Set;

/**
 * Created by Maxim on 19.02.2017.
 */
public interface Auctioneer {
    Set<Lot> getLots();
    void createLot(Product product);
    void removeLot(Long lotId);
}
