package edu.core.java.auction.domain.concrete;

import edu.core.java.auction.domain.interfaces.Person;
import edu.core.java.auction.domain.interfaces.DomainObject;
import edu.core.java.auction.domain.interfaces.Product;

/**
 * Created by Maxim on 19.02.2017.
 */
public class SimpleProduct extends DomainObject
                           implements Product {
    private String title;
    private String description;
    private int count;
    private Person owner;
    private double sellerPrice;

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Person getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Person value) {
        this.owner = value;
    }

    @Override
    public double getSellerPrice() {
        return sellerPrice;
    }

    @Override
    public void setSellerPrice(double value) {
        this.sellerPrice = value;
    }

    @Override
    public void setCount(int value) {
        this.count = value;
    }

    @Override
    public void setDescription(String value) {
        this.description = value;
    }

    @Override
    public void setTitle(String value) {
        this.title = value;
    }
}
