package edu.core.java.auction.loader;

import edu.core.java.auction.domain.Bid;
import edu.core.java.auction.repository.BidRepository;
import edu.core.java.auction.translator.BidTranslator;

/**
 * Created by Maxim on 02.04.2017.
 */
public class BidLoader extends Loader<Bid> {
    public BidLoader(BidRepository bidRepository, BidTranslator bidTranslator){
        repository = bidRepository;
        translator = bidTranslator;
    }
}
