package edu.core.java.auction.domain;

/**
 * Created by Maxim on 19.02.2017.
 */
public class Product extends DomainObject {
    private String title;
    private String description;
    private Seller owner;

    public Product(Long id, String title, String description, Seller owner){
        this.id = id;
        this.title = title;
        this.description = description;
        this.owner = owner;
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

    public void setDescription(String value) {
        this.description = value;
    }

    public void setTitle(String value) {
        this.title = value;
    }
}
