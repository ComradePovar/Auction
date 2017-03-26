package edu.core.java.auction.loader;

import edu.core.java.auction.domain.Lot;
import edu.core.java.auction.repository.BidRepository;
import edu.core.java.auction.repository.LotRepository;
import edu.core.java.auction.repository.ProductRepository;
import edu.core.java.auction.translator.BidTranslator;
import edu.core.java.auction.translator.ProductTranslator;
import edu.core.java.auction.translator.Translator;
import edu.core.java.auction.vo.LotValueObject;

import java.util.HashSet;

/**
 * Created by Max on 09.03.2017.
 */
public class LotLoader extends Loader<Lot> {
    private BidTranslator bidTranslator;
    private LotRepository lotRepository;
    private ProductTranslator productTranslator;
    private BidRepository bidRepository;
    private ProductRepository productRepository;

    public LotLoader(){}
    public LotLoader(BidTranslator bidTranslator, BidRepository bidRepository,
                     ProductRepository productRepository, ProductTranslator productTranslator, LotRepository lotRepository){
        this.bidTranslator = bidTranslator;
        this.bidRepository = bidRepository;
        this.productRepository = productRepository;
        this.productTranslator = productTranslator;
        this.lotRepository = lotRepository;
    }

    public void setBidTranslator(BidTranslator bidTranslator) {
        this.bidTranslator = bidTranslator;
    }

    public void setProductTranslator(ProductTranslator productTranslator) {
        this.productTranslator = productTranslator;
    }

    public void setBidRepository(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void setLotRepository(LotRepository lotRepository){
        this.lotRepository = lotRepository;
    }
    @Override
    public Lot getEntity(Long id) {
        LotValueObject lotValueObject = lotRepository.find(id);
        return new Lot(lotValueObject.id, lotValueObject.startPrice, lotValueObject.startDate, lotValueObject.endDate,
                          productTranslator.convertToDomainObject(productRepository.find(lotValueObject.productId)),
                          bidTranslator.convertToDomainObject(bidRepository.find(lotValueObject.currentBidId)));
    }

    @Override
    public HashSet<Lot> getAllEntities() {
        HashSet<Lot> lots = new HashSet<>();

        for (LotValueObject lotValueObject : lotRepository.getAll()){
            lots.add(getEntity(lotValueObject.id));
        }

        return lots;
    }
}
