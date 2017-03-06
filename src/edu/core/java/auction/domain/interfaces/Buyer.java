package edu.core.java.auction.domain.interfaces;
import edu.core.java.auction.domain.concrete.Bid;
import edu.core.java.auction.domain.concrete.Lot;

import java.util.Set;

/**
 * Created by Maxim on 19.02.2017.
 */
public interface Buyer {
    void createBid(Lot lot, double bidAmount);
    void removeBid(Long bidId);
    Set<Bid> getBids();
}
