package edu.core.java.auction.loader;

import edu.core.java.auction.domain.DomainObject;
import edu.core.java.auction.repository.Repository;
import edu.core.java.auction.translator.Translator;
import edu.core.java.auction.vo.ValueObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;

/**
 * Created by Max on 09.03.2017.
 */
//TODO
//Загрузчик не должен знать о трансляторах
public abstract class Loader<D extends DomainObject> {
    protected Repository repository;
    protected Translator translator;
    protected Logger logger = LoggerFactory.getLogger(Loader.class);

    public D getEntity(Long id) {
        ValueObject valueObject = repository.find(id);
        D domainObject = null;
        if (valueObject != null){
            domainObject = (D) translator.convertToDomainObject(valueObject);
            logger.info("Domain object with id = " + id + " was loaded.");
        } else {
            logger.warn("Domain object with id = " + id + " wasn't loaded.");
        }
        return domainObject;
    }

    public HashSet<D> getAllEntities() {
        HashSet<D> lots = new HashSet<>();

        for (Object object : repository.getAll()){
            ValueObject valueObject = (ValueObject) object;
            lots.add(getEntity(valueObject.id));
        }

        logger.info("Domain objects were loaded.");
        return lots;
    }
}
