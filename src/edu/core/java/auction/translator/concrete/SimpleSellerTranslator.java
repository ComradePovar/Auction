package edu.core.java.auction.translator.concrete;

import edu.core.java.auction.translator.interfaces.Translator;
import edu.core.java.auction.vo.SimpleSeller;

/**
 * Created by Max on 09.03.2017.
 */
public class SimpleSellerTranslator
        implements Translator<SimpleSeller, edu.core.java.auction.domain.concrete.SimpleSeller> {
    @Override
    public edu.core.java.auction.domain.concrete.SimpleSeller convertToDomainObject(SimpleSeller value) {
        return null;
    }

    @Override
    public SimpleSeller convertToValueObject(edu.core.java.auction.domain.concrete.SimpleSeller value) {
        return null;
    }
}
