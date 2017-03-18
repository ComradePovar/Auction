package edu.core.java.auction.translator.concrete;

import edu.core.java.auction.domain.concrete.Bid;
import edu.core.java.auction.translator.interfaces.Translator;

/**
 * Created by Max on 09.03.2017.
 */
public class BidTranslator implements Translator<edu.core.java.auction.vo.Bid, Bid> {
    @Override
    public Bid convertToDomainObject(edu.core.java.auction.vo.Bid value) {
        return null;
    }

    @Override
    public edu.core.java.auction.vo.Bid convertToValueObject(Bid value) {
        return null;
    }
}
