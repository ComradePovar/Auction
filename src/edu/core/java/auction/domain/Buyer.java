package edu.core.java.auction.domain;

/**
 * Created by Maxim on 19.02.2017.
 */
public class Buyer extends Person {

    public Buyer(Long id, String name, double accountBalance){
        this.id = id;
        this.name = name;
        this.accountBalance = accountBalance;
    }
}
