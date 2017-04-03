package edu.core.java.auction.loader;

import edu.core.java.auction.domain.Lot;
import edu.core.java.auction.repository.LotRepository;
import edu.core.java.auction.translator.LotTranslator;

/**
 * Created by Max on 09.03.2017.
 */
public class LotLoader extends Loader<Lot> {
    public LotLoader(LotRepository lotRepository, LotTranslator lotTranslator){
        repository = lotRepository;
        translator = lotTranslator;
    }
}
