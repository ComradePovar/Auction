package edu.core.java.auction.translator;

import edu.core.java.auction.AuctionService;
import edu.core.java.auction.domain.Buyer;
import edu.core.java.auction.domain.Lot;
import edu.core.java.auction.loader.BidLoader;
import edu.core.java.auction.loader.ProductLoader;
import edu.core.java.auction.vo.BuyerValueObject;
import edu.core.java.auction.vo.LotValueObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Maxim on 02.04.2017.
 */
public class LotTranslator implements Translator<LotValueObject, Lot> {
    protected Logger logger = LoggerFactory.getLogger(ProductTranslator.class);

    @Override
    public Lot convertToDomainObject(LotValueObject value) {
        if (value == null){
            logger.warn("Value object is null. Translation is not possible.");
            return null;
        }

        logger.info("Conversion was successful.");
        ProductLoader productLoader = AuctionService.getInstance().getProductLoader();
        BidLoader bidLoader = AuctionService.getInstance().getBidLoader();
        return new Lot(value.id, value.startPrice, value.endDate,
                       productLoader.getEntity(value.productId), bidLoader.getEntity(value.currentBidId));
    }

    @Override
    public LotValueObject convertToValueObject(Lot domain) {
        if (domain == null){
            logger.warn("Domain object is null. Translation is not possible.");
            return null;
        }

        logger.info("Conversion was successful.");
        return new LotValueObject(domain.getId(), domain.getStartPrice(), domain.getEndDate(),
                                  domain.getProduct().getId(), domain.getCurrentBid().getId());
    }
}
