package edu.core.java.auction.translator;

import edu.core.java.auction.domain.Seller;
import edu.core.java.auction.vo.SellerValueObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Max on 09.03.2017.
 */
public class SellerTranslator implements Translator<SellerValueObject, Seller> {
    protected Logger logger = LoggerFactory.getLogger(SellerTranslator.class);

    @Override
    public Seller convertToDomainObject(SellerValueObject value) {
        if (value == null){
            logger.warn("Value object is null. Translation is not possible.");
            return null;
        }

        logger.info("Conversion was successful.");
        return new Seller(value.id, value.name, value.accountBalance, value.commissionPercentage);
    }

    @Override
    public SellerValueObject convertToValueObject(Seller domain) {
        if (domain == null){
            logger.warn("Domain object is null. Translation is not possible.");
            return null;
        }

        logger.info("Conversion was successful.");
        return new SellerValueObject(domain.getId(), domain.getName(),
                                     domain.getAccountBalance(), domain.getCommissionPercentage());
    }
}
