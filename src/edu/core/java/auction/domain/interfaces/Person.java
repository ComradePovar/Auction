package edu.core.java.auction.domain.interfaces;

/**
 * Created by Maxim on 19.02.2017.
 */
public interface Person {
    String getName();
    String getPhone();
    double getAccountBalance();
    void addMoney(double amount);
    void withdrawMoney(double amount);
    void setName(String value);
    void setPhone(String value);
}
