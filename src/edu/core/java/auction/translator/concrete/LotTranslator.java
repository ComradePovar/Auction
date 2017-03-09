package edu.core.java.auction.translator.concrete;

import edu.core.java.auction.translator.interfaces.Translator;
import edu.core.java.auction.vo.Lot;

/**
 * Created by Max on 09.03.2017.
 */
public class LotTranslator implements Translator<Lot, edu.core.java.auction.domain.concrete.Lot> {
    @Override
    public edu.core.java.auction.domain.concrete.Lot convertToDomainObject(Lot value) {
        return null;
    }

    @Override
    public Lot convertToValueObject(edu.core.java.auction.domain.concrete.Lot value) {
        return null;
    }
}
