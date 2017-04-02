package edu.core.java.auction.loader;

import edu.core.java.auction.domain.Lot;
import edu.core.java.auction.repository.BidRepository;
import edu.core.java.auction.repository.LotRepository;
import edu.core.java.auction.repository.ProductRepository;
import edu.core.java.auction.translator.BidTranslator;
import edu.core.java.auction.translator.LotTranslator;
import edu.core.java.auction.translator.ProductTranslator;
import edu.core.java.auction.translator.Translator;
import edu.core.java.auction.vo.LotValueObject;
import edu.core.java.auction.vo.ValueObject;

import java.util.HashSet;

/**
 * Created by Max on 09.03.2017.
 */
public class LotLoader extends Loader<Lot> {
    public LotLoader(LotRepository lotRepository, LotTranslator lotTranslator){
        repository = lotRepository;
        translator = lotTranslator;
    }
}
