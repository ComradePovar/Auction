package edu.core.java.auction.loader;

import edu.core.java.auction.domain.Buyer;
import edu.core.java.auction.repository.BuyerRepository;
import edu.core.java.auction.translator.BuyerTranslator;

/**
 * Created by Maxim on 02.04.2017.
 */
public class BuyerLoader extends Loader<Buyer> {
    public BuyerLoader(BuyerRepository buyerRepository, BuyerTranslator buyerTranslator){
        repository = buyerRepository;
        translator = buyerTranslator;
    }
}
