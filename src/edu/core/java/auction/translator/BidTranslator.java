package edu.core.java.auction.translator;

import edu.core.java.auction.AuctionService;
import edu.core.java.auction.domain.Bid;
import edu.core.java.auction.loader.BuyerLoader;
import edu.core.java.auction.loader.LotLoader;
import edu.core.java.auction.vo.BidValueObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Max on 09.03.2017.
 */
public class BidTranslator implements Translator<BidValueObject, Bid> {
    protected Logger logger = LoggerFactory.getLogger(BidTranslator.class);

    @Override
    public Bid convertToDomainObject(BidValueObject value) {
        if (value == null) {
            logger.warn("Value object is null. Translation is not possible.");
            return null;
        }

        logger.info("Conversion was successful.");
        BuyerLoader buyerLoader = AuctionService.getInstance().getBuyerLoader();
        LotLoader lotLoader = AuctionService.getInstance().getLotLoader();
        return new Bid(value.id, lotLoader.getEntity(value.lotId), buyerLoader.getEntity(value.buyerId), value.amount);
    }

    @Override
    public BidValueObject convertToValueObject(Bid domain) {
        if (domain == null){
            logger.warn("Value object is null. Translation is not possible.");
            return null;
        }

        logger.info("Conversion was successful.");
        return new BidValueObject(domain.getId(), domain.getLot().getId(), domain.getBuyer().getId(), domain.getBidAmount());
    }
}
