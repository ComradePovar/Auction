package edu.core.java.auction.repository.interfaces;

import edu.core.java.auction.vo.Bid;
import edu.core.java.auction.vo.ValueObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by Max on 06.03.2017.
 */
public abstract class Repository<V extends ValueObject> {
    private HashMap<Long, V> repository = new HashMap<Long, V>();

    public void add(V object){
        repository.put(object.getId(), object);
    }
    public void update(Long id, V object){
        delete(id);
        add(object);
    }
    public V find(Long id){
        return repository.get(id);
    }
    public void delete(Long id){
        repository.remove(id);
    }
    public Collection<V> getAll(){
        return repository.values();
    }
}
