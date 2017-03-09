package edu.core.java.auction.translator.concrete;

import edu.core.java.auction.translator.interfaces.Translator;
import edu.core.java.auction.vo.SimpleBuyer;

/**
 * Created by Max on 09.03.2017.
 */
public class SimpleBuyerTranslator
        implements Translator<SimpleBuyer, edu.core.java.auction.domain.concrete.SimpleBuyer> {
    @Override
    public edu.core.java.auction.domain.concrete.SimpleBuyer convertToDomainObject(SimpleBuyer value) {
        return null;
    }

    @Override
    public SimpleBuyer convertToValueObject(edu.core.java.auction.domain.concrete.SimpleBuyer value) {
        return null;
    }
}
