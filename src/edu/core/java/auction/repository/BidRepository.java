package edu.core.java.auction.repository;

import edu.core.java.auction.vo.BidValueObject;

/**
 * Created by Max on 06.03.2017.
 */
public class BidRepository extends Repository<BidValueObject> {
    protected static Long maxId = 0L;

    @Override
    public Long getMaxId(){
        return maxId;
    }
    @Override
    public void incMaxId(){
        maxId++;
    }
}
