package edu.core.java.auction.translator;

import edu.core.java.auction.domain.Auctioneer;
import edu.core.java.auction.domain.Lot;
import edu.core.java.auction.domain.Product;
import edu.core.java.auction.loader.AuctioneerLoader;
import edu.core.java.auction.repository.AuctioneerRepository;
import edu.core.java.auction.repository.BidRepository;
import edu.core.java.auction.repository.ProductRepository;
import edu.core.java.auction.vo.LotValueObject;

import java.util.Date;

/**
 * Created by Max on 09.03.2017.
 */
public class LotTranslator implements Translator<LotValueObject, Lot> {
    private BidTranslator bidTranslator;

    private ProductTranslator productTranslator;
    private BidRepository bidRepository;
    private ProductRepository productRepository;
    private AuctioneerLoader auctioneerLoader;

    public LotTranslator(){}
    public LotTranslator(BidTranslator bidTranslator, BidRepository bidRepository,
                         ProductRepository productRepository, ProductTranslator productTranslator,
                         AuctioneerLoader auctioneerLoader){
        this.bidTranslator = bidTranslator;
        this.bidRepository = bidRepository;
        this.productRepository = productRepository;
        this.productTranslator = productTranslator;
        this.auctioneerLoader = auctioneerLoader;
    }
    @Override
    public Lot convertToDomainObject(LotValueObject value) {
        return new Lot(value.startPrice, value.startDate, value.endDate,
                productTranslator.convertToDomainObject(productRepository.find(value.productId)),
                auctioneerLoader.getEntity(value.auctioneerId),
                bidTranslator.convertToDomainObject(bidRepository.find(value.id)));
    }

    @Override
    public LotValueObject convertToValueObject(Lot value) {
        LotValueObject lotValueObject = new LotValueObject();
        lotValueObject.id = value.getId();
        lotValueObject.auctioneerId = value.getAuctioneer().getId();
        lotValueObject.currentBidId = value.getCurrentBid().getId();
        lotValueObject.productId = value.getProduct().getId();
        lotValueObject.startDate = value.getStartDate();
        lotValueObject.endDate = value.getEndDate();
        lotValueObject.startPrice = value.getStartPrice();
        return lotValueObject;
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

    public void setAuctioneerLoader(AuctioneerLoader auctioneerLoader) {
        this.auctioneerLoader = auctioneerLoader;
    }
}
