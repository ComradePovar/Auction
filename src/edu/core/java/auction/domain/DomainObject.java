package edu.core.java.auction.domain;

/**
 * Created by Maxim on 19.02.2017.
 */
public abstract class DomainObject {
    protected Long id;

    public Long getId(){
        return id;
    }

    protected void setId(Long id){
        this.id = id;
    }
}
