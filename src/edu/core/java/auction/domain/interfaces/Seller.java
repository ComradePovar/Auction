package edu.core.java.auction.domain.interfaces;

import java.util.ArrayList;

/**
 * Created by Maxim on 19.02.2017.
 */
public interface Seller {
    void setComissionPercentage(double value);
    double getComissionPercentage();
    ArrayList<Product> getProducts();
    void addProduct(Product product);
    void removeProduct(Long productId);
}
