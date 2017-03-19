package edu.core.java.auction.domain.concrete;

import edu.core.java.auction.domain.interfaces.Auctioneer;
import edu.core.java.auction.domain.interfaces.DomainObject;
import edu.core.java.auction.domain.interfaces.Product;
import sun.java2d.pipe.SpanShapeRenderer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Maxim on 19.02.2017.
 */
public class SimpleAuctioneer extends DomainObject
                              implements Auctioneer {
    private HashSet<Lot> lots = new HashSet<Lot>();
    private String name;

    public SimpleAuctioneer(Long id){
        this.id = id;
    }
    public SimpleAuctioneer(Long id, String name){
        this.id = id;
        this.name = name;
    }
    @Override
    public void setName(String name){
        this.name = name;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public HashSet<Lot> getLots() {
        return lots;
    }
}
