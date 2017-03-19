package edu.core.java.auction.domain.interfaces;

import java.util.Set;

/**
 * Created by Maxim on 19.02.2017.
 */
public interface Seller {
    void setComissionPercentage(double value);
    double getComissionPercentage();
    double getAccountBalance();
    void setAccountBalance(double amount);
    Set<Product> getProducts();
    Long getId();
}
