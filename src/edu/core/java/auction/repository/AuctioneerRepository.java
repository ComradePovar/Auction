package edu.core.java.auction.repository;

import edu.core.java.auction.vo.AuctioneerValueObject;

/**
 * Created by Max on 06.03.2017.
 */
public class AuctioneerRepository extends Repository<AuctioneerValueObject> {
    protected static Long maxId = (long)0;

    @Override
    public Long getMaxId(){
        return maxId;
    }

    @Override
    public void incMaxId(){
        maxId++;
    }
}
