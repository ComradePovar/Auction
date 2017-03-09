package edu.core.java.auction.translator.concrete;

import edu.core.java.auction.translator.interfaces.Translator;
import edu.core.java.auction.vo.SimpleProduct;

/**
 * Created by Max on 09.03.2017.
 */
public class SimpleProductTranslator
        implements Translator<SimpleProduct, edu.core.java.auction.domain.concrete.SimpleProduct> {
    @Override
    public edu.core.java.auction.domain.concrete.SimpleProduct convertToDomainObject(SimpleProduct value) {
        return null;
    }

    @Override
    public SimpleProduct convertToValueObject(edu.core.java.auction.domain.concrete.SimpleProduct value) {
        return null;
    }
}
