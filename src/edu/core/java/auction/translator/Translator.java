package edu.core.java.auction.translator;

import edu.core.java.auction.domain.DomainObject;
import edu.core.java.auction.vo.ValueObject;

/**
 * Created by Max on 09.03.2017.
 */
public interface Translator<V extends ValueObject, D extends DomainObject> {
    D convertToDomainObject(V value);
    V convertToValueObject(D value);
}
