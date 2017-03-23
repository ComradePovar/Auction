package edu.core.java.auction.domain;

/**
 * Created by Maxim on 19.02.2017.
 */
public class Product extends DomainObject {
    private String title;
    private String description;
    private Seller owner;
    private double sellerPrice;

    public Product(Long id){
        this.id = id;
    }

    public Product(Long id, String title, String description, Seller owner, double sellerPrice){
        this.id = id;
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.sellerPrice = sellerPrice;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Seller getOwner() {
        return owner;
    }

    public void setOwner(Seller value) {
        this.owner = value;
    }

    public double getSellerPrice() {
        return sellerPrice;
    }

    public void setSellerPrice(double value) {
        this.sellerPrice = value;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public void setTitle(String value) {
        this.title = value;
    }
}
