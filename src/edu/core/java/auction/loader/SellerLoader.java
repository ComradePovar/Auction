package edu.core.java.auction.loader;

import edu.core.java.auction.domain.Lot;
import edu.core.java.auction.domain.Seller;
import edu.core.java.auction.repository.LotRepository;
import edu.core.java.auction.repository.SellerRepository;
import edu.core.java.auction.translator.LotTranslator;
import edu.core.java.auction.translator.SellerTranslator;
import edu.core.java.auction.vo.LotValueObject;

import java.util.HashSet;

/**
 * Created by Maxim on 02.04.2017.
 */
public class SellerLoader extends Loader<Seller> {
    public SellerLoader(SellerRepository sellerRepository, SellerTranslator sellerTranslator){
        repository = sellerRepository;
        translator = sellerTranslator;
    }
}
