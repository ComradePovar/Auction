package edu.core.java.auction.domain.concrete;

import edu.core.java.auction.domain.interfaces.Auctioneer;
import edu.core.java.auction.domain.interfaces.DomainObject;
import edu.core.java.auction.domain.interfaces.Product;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

/**
 * Created by Maxim on 19.02.2017.
 */
public class SimpleAuctioneer extends DomainObject
                              implements Auctioneer {
    private ArrayList<Lot> lots;

    @Override
    public ArrayList<Lot> getLots() {
        return lots;
    }

    @Override
    public void createLot(Product product) {
        throw new NotImplementedException();
    }

    @Override
    public void removeLot(Long lotId) {
        throw new NotImplementedException();
    }
}
