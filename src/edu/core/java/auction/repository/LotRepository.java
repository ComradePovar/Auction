package edu.core.java.auction.repository;

import edu.core.java.auction.vo.LotValueObject;

/**
 * Created by Max on 06.03.2017.
 */
public class LotRepository extends Repository<LotValueObject> {
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
