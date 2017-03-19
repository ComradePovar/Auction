package edu.core.java.auction.domain.interfaces;

/**
 * Created by Maxim on 19.02.2017.
 */
public interface Person {
    String getName();
    double getAccountBalance();
    void addMoney(double amount);
    void withdrawMoney(double amount);
    void setName(String value);
    Long getId();
}
