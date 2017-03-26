package edu.core.java.auction.translator;

import edu.core.java.auction.domain.Bid;
import edu.core.java.auction.domain.Buyer;
import edu.core.java.auction.repository.BuyerRepository;
import edu.core.java.auction.vo.BidValueObject;

/**
 * Created by Max on 09.03.2017.
 */
public class BidTranslator implements Translator<BidValueObject, Bid> {
    private BuyerTranslator buyerTranslator;
    private BuyerRepository buyerRepository;

    public BidTranslator(){}
    public BidTranslator(BuyerTranslator buyerTranslator, BuyerRepository buyerRepository){
        this.buyerTranslator = buyerTranslator;
        this.buyerRepository = buyerRepository;
    }

    @Override
    public Bid convertToDomainObject(BidValueObject value) {
        if (value == null)
            return null;
        Buyer buyer = buyerTranslator.convertToDomainObject(buyerRepository.find(value.buyerId));
        return new Bid(value.id, buyer, value.amount);
    }

    @Override
    public BidValueObject convertToValueObject(Bid value) {
        BidValueObject bid = new BidValueObject();
        bid.id = value.getId();
        bid.amount = value.getBidAmount();
        bid.buyerId = value.getBuyer().getId();
        return bid;
    }

    public void setBuyerTranslator(BuyerTranslator buyerTranslator){
        this.buyerTranslator = buyerTranslator;
    }

    public void setBuyerRepository(BuyerRepository buyerRepository){
        this.buyerRepository = buyerRepository;
    }
}
