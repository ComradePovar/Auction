package edu.core.java.auction.repository;

import edu.core.java.auction.vo.ProductValueObject;

/**
 * Created by Max on 06.03.2017.
 */
public class ProductRepository extends Repository<ProductValueObject> {
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
