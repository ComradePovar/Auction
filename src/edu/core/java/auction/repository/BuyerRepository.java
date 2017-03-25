package edu.core.java.auction.repository;

import edu.core.java.auction.vo.BuyerValueObject;

/**
 * Created by Max on 06.03.2017.
 */
public class BuyerRepository extends Repository<BuyerValueObject> {
    protected static Long maxId;


    @Override
    public Long getMaxId(){
        return maxId;
    }
    @Override
    public void incMaxId(){
        maxId++;
    }
}
