package edu.core.java.auction.translator;

import edu.core.java.auction.domain.Bid;
import edu.core.java.auction.domain.Buyer;
import edu.core.java.auction.repository.BidRepository;
import edu.core.java.auction.vo.BidValueObject;
import edu.core.java.auction.vo.BuyerValueObject;

/**
 * Created by Max on 09.03.2017.
 */
public class BuyerTranslator
        implements Translator<BuyerValueObject, Buyer> {
    private BidRepository bidRepository;
    private BidTranslator bidTranslator;

    public BuyerTranslator(){}
    public BuyerTranslator(BidRepository bidRepository, BidTranslator bidTranslator){
        this.bidRepository = bidRepository;
        this.bidTranslator = bidTranslator;
    }
    @Override
    public Buyer convertToDomainObject(BuyerValueObject value) {
        Buyer buyer = new Buyer(value.id, value.name, value.accountBalance);

        for (BidValueObject bidValueObject : bidRepository.getAll()){
            if (bidValueObject.buyerId == value.id){
                buyer.getBids().add(new Bid(bidValueObject.id, buyer, bidValueObject.amount));
            }
        }

        return buyer;
    }

    @Override
    public BuyerValueObject convertToValueObject(Buyer value) {
        BuyerValueObject buyerValueObject = new BuyerValueObject();
        buyerValueObject.id = value.getId();
        buyerValueObject.name = value.getName();
        buyerValueObject.accountBalance = value.getAccountBalance();
        return buyerValueObject;
    }

    public void setBidTranslator(BidTranslator bidTranslator){
        this.bidTranslator = bidTranslator;
    }

    public void setBidRepository(BidRepository bidRepository){
        this.bidRepository = bidRepository;
    }
}
