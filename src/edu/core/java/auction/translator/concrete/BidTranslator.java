package edu.core.java.auction.translator.concrete;

import edu.core.java.auction.translator.interfaces.Translator;
import edu.core.java.auction.vo.Bid;

/**
 * Created by Max on 09.03.2017.
 */
public class BidTranslator implements Translator<Bid,edu.core.java.auction.domain.concrete.Bid> {
    @Override
    public edu.core.java.auction.domain.concrete.Bid convertToDomainObject(Bid value) {
        return null;
    }

    @Override
    public Bid convertToValueObject(edu.core.java.auction.domain.concrete.Bid value) {
        return null;
    }
}
