package edu.core.java.auction.translator;

import edu.core.java.auction.domain.Buyer;
import edu.core.java.auction.vo.BuyerValueObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Max on 09.03.2017.
 */
public class BuyerTranslator implements Translator<BuyerValueObject, Buyer> {
    protected Logger logger = LoggerFactory.getLogger(BuyerTranslator.class);

    @Override
    public Buyer convertToDomainObject(BuyerValueObject value) {
        if (value == null){
            logger.warn("Value object is null. Translation is not possible.");
            return null;
        }

        logger.info("Conversion was successful.");
        return new Buyer(value.id, value.name, value.accountBalance);
    }

    @Override
    public BuyerValueObject convertToValueObject(Buyer domain) {
        if (domain == null){
            logger.warn("Domain object is null. Translation is not possible.");
            return null;
        }

        logger.info("Conversion was successful.");
        return new BuyerValueObject(domain.getId(), domain.getName(), domain.getAccountBalance());
    }
}
