package edu.core.java.auction.domain.interfaces;

/**
 * Created by Maxim on 19.02.2017.
 */
public interface Product {
    String getTitle();
    String getDescription();
    int getCount();
    Person getOwner();
    void setOwner(Person value);
    double getSellerPrice();
    void setSellerPrice(double value);
    void setCount(int value);
    void setDescription(String value);
    void setTitle(String value);
}
