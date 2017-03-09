package edu.core.java.auction.translator.concrete;

import edu.core.java.auction.translator.interfaces.Translator;
import edu.core.java.auction.vo.SimpleAuctioneer;

/**
 * Created by Max on 09.03.2017.
 */
public class SimpleAuctioneerTranslator
        implements Translator<SimpleAuctioneer, edu.core.java.auction.domain.concrete.SimpleAuctioneer> {
    @Override
    public edu.core.java.auction.domain.concrete.SimpleAuctioneer convertToDomainObject(SimpleAuctioneer value) {
        return null;
    }

    @Override
    public SimpleAuctioneer convertToValueObject(edu.core.java.auction.domain.concrete.SimpleAuctioneer value) {
        return null;
    }
}
