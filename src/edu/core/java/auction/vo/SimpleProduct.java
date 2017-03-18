package edu.core.java.auction.vo;

/**
 * Created by Maxim on 19.02.2017.
 */
public class SimpleProduct extends ValueObject {
    public String title;
    public String description;
    public int count;
    public Long ownerId;
    public double sellerPrice;

    public SimpleProduct(){
        this.id = ++maxId;
    }
    public SimpleProduct(String title, String description, int count, Long ownerId, double sellerPrice){
        this.title = title;
        this.description = description;
        this.count = count;
        this.ownerId = ownerId;
        this.sellerPrice = sellerPrice;
        this.id = ++maxId;
    }
}
