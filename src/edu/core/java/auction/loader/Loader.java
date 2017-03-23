package edu.core.java.auction.loader;

import edu.core.java.auction.domain.DomainObject;

import java.util.HashSet;

/**
 * Created by Max on 09.03.2017.
 */
public abstract class Loader<D extends DomainObject> {
    public abstract D getEntity(Long id);
    public abstract HashSet<D> getAllEntities();
}
