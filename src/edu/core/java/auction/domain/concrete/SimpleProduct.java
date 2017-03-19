package edu.core.java.auction.domain.concrete;

import edu.core.java.auction.domain.interfaces.Person;
import edu.core.java.auction.domain.interfaces.DomainObject;
import edu.core.java.auction.domain.interfaces.Product;
import edu.core.java.auction.domain.interfaces.Seller;

/**
 * Created by Maxim on 19.02.2017.
 */
public class SimpleProduct extends DomainObject
                           implements Product {
    private String title;
    private String description;
    private Seller owner;
    private double sellerPrice;

    public SimpleProduct(Long id){
        this.id = id;
    }

    public SimpleProduct(Long id, String title, String description, Seller owner, double sellerPrice){
        this.id = id;
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.sellerPrice = sellerPrice;
    }
    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }


    @Override
    public Seller getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Seller value) {
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
    public void setDescription(String value) {
        this.description = value;
    }

    @Override
    public void setTitle(String value) {
        this.title = value;
    }
}
