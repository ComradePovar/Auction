package edu.core.java.auction;

import edu.core.java.auction.domain.Bid;
import edu.core.java.auction.domain.Auctioneer;
import edu.core.java.auction.domain.Product;
import edu.core.java.auction.domain.Seller;
import edu.core.java.auction.domain.interfaces.Buyer;
import edu.core.java.auction.loader.AuctioneerLoader;
import edu.core.java.auction.loader.Loader;
import edu.core.java.auction.repository.*;
import edu.core.java.auction.translator.*;
import edu.core.java.auction.vo.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Maxim on 18.03.2017.
 */
public class AuctionService {
    private static AuctionService instance = new AuctionService();
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    private class LotHandler extends TimerTask{
        private edu.core.java.auction.domain.Lot lot;

        public LotHandler(edu.core.java.auction.domain.Lot lot){
            this.lot = lot;
        }

        @Override
        public void run() {
            Buyer winner = lot.getCurrentBid().getBuyer();
            Seller seller = lot.getProduct().getOwner();
            System.out.println("Auction has ended. LotValueObject name:" + lot.getProduct().getTitle());
            System.out.println("The winner is " + simpleBuyerRepository.find(winner.getId()).name);

            simpleProductRepository.delete(lot.getProduct().getId());
            seller.getProducts().remove(lot.getProduct());
            bidRepository.delete(lot.getCurrentBid().getId());

            double sellerWinnings = seller.getAccountBalance() + lot.getCurrentBid().getBidAmount() * (1 - seller.getComissionPercentage());
            seller.setAccountBalance(sellerWinnings);
            SellerValueObject sellerValue = simpleSellerRepository.find(seller.getId());
            sellerValue.accountBalance = sellerWinnings;
            simpleSellerRepository.update(sellerValue.getId(), sellerValue);
            lot.getAuctioneer().getLots().remove(lot);
            lotRepository.delete(lot.getId());
        }
    }

    // Repositories

    private Repository<BidValueObject> bidRepository = new BidRepository();
    private Repository<LotValueObject> lotRepository = new LotRepository();
    private Repository<AuctioneerValueObject> simpleAuctioneerRepository = new AuctioneerRepository();
    private Repository<BuyerValueObject> simpleBuyerRepository = new BuyerRepository();
    private Repository<ProductValueObject> simpleProductRepository = new ProductRepository();
    private Repository<SellerValueObject> simpleSellerRepository = new SellerRepository();

    // Translators

    private Translator<BidValueObject, Bid> bidTranslator = new BidTranslator();
    private Translator<LotValueObject, edu.core.java.auction.domain.Lot> lotTranslator = new LotTranslator();
    private Translator<AuctioneerValueObject, Auctioneer>
            simpleAuctioneerTranslator = new AuctioneerTranslator();
    private Translator<BuyerValueObject, edu.core.java.auction.domain.Buyer>
            simpleBuyerTranslator = new BuyerTranslator();
    private Translator<ProductValueObject, Product>
            simpleProductTranslator = new ProductTranslator();
    private Translator<SellerValueObject, Seller>
            simpleSellerTranslator = new SellerTranslator();

    // Loaders

    private Loader<Auctioneer> simpleAuctioneerLoader =
            new AuctioneerLoader(simpleAuctioneerRepository, lotRepository, lotTranslator);

    private AuctionService(){
    }

    public static AuctionService getInstance(){
        return instance;
    }


    public DateFormat getDateFormat(){
        return dateFormat;
    }

    public void setDateFormat(DateFormat newDateFormat){
        dateFormat = newDateFormat;
    }

    // Operations with AuctioneerRepository

    public Auctioneer getSimpleAuctioneerById(Long id){
        return simpleAuctioneerTranslator.convertToDomainObject(simpleAuctioneerRepository.find(id));
    }

    public Auctioneer createSimpleAuctioneer(){
        AuctioneerValueObject auctioneerValueObjectValue = new AuctioneerValueObject();
        Auctioneer auctioneerDomain =
                new Auctioneer(auctioneerValueObjectValue.getId());
        simpleAuctioneerRepository.add(auctioneerValueObjectValue);

        return auctioneerDomain;
    }

    public Auctioneer createSimpleAuctioneer(String name){
        AuctioneerValueObject auctioneerValueObjectValue = new AuctioneerValueObject(name);
        Auctioneer auctioneerDomain =
                new Auctioneer(auctioneerValueObjectValue.getId(), name);
        simpleAuctioneerRepository.add(auctioneerValueObjectValue);

        return auctioneerDomain;
    }

    public void updateSimpleAuctioneer(Auctioneer auctioneer){
        AuctioneerValueObject auctioneerValueObjectValue = simpleAuctioneerTranslator.convertToValueObject(auctioneer);
        simpleAuctioneerRepository.update(auctioneerValueObjectValue.getId(), auctioneerValueObjectValue);
    }

    public Auctioneer findSimpleAuctioneerById(Long id){
        AuctioneerValueObject auctioneerValueObjectValue = simpleAuctioneerRepository.find(id);
        return simpleAuctioneerTranslator.convertToDomainObject(auctioneerValueObjectValue);
    }

    public void deleteSimpleAuctioneerById(Long id){
        simpleAuctioneerRepository.delete(id);
    }

    public HashSet<Auctioneer> getAllSimpleAuctioneers(){
        return simpleAuctioneerLoader.getAllEntities();
    }

    // Operations with BuyerRepository

    public edu.core.java.auction.domain.Buyer getSimpleBuyerById(Long id){
        return simpleBuyerTranslator.convertToDomainObject(simpleBuyerRepository.find(id));
    }

    public edu.core.java.auction.domain.Buyer createSimpleBuyer(){
        BuyerValueObject buyerValueObjectValue = new BuyerValueObject();
        edu.core.java.auction.domain.Buyer buyerDomain =
                new edu.core.java.auction.domain.Buyer(buyerValueObjectValue.getId());
        simpleBuyerRepository.add(buyerValueObjectValue);

        return buyerDomain;
    }

    public edu.core.java.auction.domain.Buyer createSimpleBuyer(
            String name, double accountBalance){
        BuyerValueObject buyerValueObjectValue = new BuyerValueObject(name, accountBalance);
        edu.core.java.auction.domain.Buyer buyerDomain =
                new edu.core.java.auction.domain.Buyer(buyerValueObjectValue.getId(), name, accountBalance);
        simpleBuyerRepository.add(buyerValueObjectValue);

        return buyerDomain;
    }

    public void updateSimpleBuyer(edu.core.java.auction.domain.Buyer buyer){
        BuyerValueObject buyerValueObjectValue = simpleBuyerTranslator.convertToValueObject(buyer);
        simpleBuyerRepository.update(buyerValueObjectValue.getId(), buyerValueObjectValue);
    }

    public edu.core.java.auction.domain.Buyer findSimpleBuyerById(Long id){
        BuyerValueObject buyerValueObjectValue = simpleBuyerRepository.find(id);
        return simpleBuyerTranslator.convertToDomainObject(buyerValueObjectValue);
    }

    public void deleteSimpleBuyerById(Long id){
        simpleBuyerRepository.delete(id);
    }

    public HashSet<edu.core.java.auction.domain.Buyer> getAllSimpleBuyers(){
        Collection<BuyerValueObject> buyerValueObjects = simpleBuyerRepository.getAll();
        HashSet<edu.core.java.auction.domain.Buyer> buyersDomain =
                new HashSet<edu.core.java.auction.domain.Buyer>();

        for (BuyerValueObject buyerValueObjectValue : buyerValueObjects){
            buyersDomain.add(simpleBuyerTranslator.convertToDomainObject(buyerValueObjectValue));
        }

        return buyersDomain;
    }

    // Operations with SellerRepository

    public Seller getSimpleSellerById(Long id){
        return simpleSellerTranslator.convertToDomainObject(simpleSellerRepository.find(id));
    }

    public Seller createSimpleSeller(){
        SellerValueObject sellerValueObjectValue = new SellerValueObject();
        Seller sellerDomain =
                new Seller(sellerValueObjectValue.getId());
        simpleSellerRepository.add(sellerValueObjectValue);

        return sellerDomain;
    }

    public Seller createSimpleSeller(
            String name, double accountBalance, double comissionPercentage){
        SellerValueObject sellerValueObjectValue = new SellerValueObject(name, accountBalance, comissionPercentage);
        Seller sellerDomain =
                new Seller(sellerValueObjectValue.getId(), name, accountBalance, comissionPercentage);
        simpleSellerRepository.add(sellerValueObjectValue);

        return sellerDomain;
    }

    public void updateSimpleSeller(Seller seller){
        SellerValueObject sellerValueObjectValue = simpleSellerTranslator.convertToValueObject(seller);
        simpleSellerRepository.update(sellerValueObjectValue.getId(), sellerValueObjectValue);
    }

    public Seller findSimpleSellerById(Long id){
        SellerValueObject sellerValueObjectValue = simpleSellerRepository.find(id);
        return simpleSellerTranslator.convertToDomainObject(sellerValueObjectValue);
    }

    public void deleteSimpleSellerById(Long id){
        simpleSellerRepository.delete(id);
    }

    public HashSet<Seller> getAllSimpleSellers(){
        Collection<SellerValueObject> sellerValueObjects = simpleSellerRepository.getAll();
        HashSet<Seller> sellersDomain =
                new HashSet<Seller>();

        for (SellerValueObject sellerValueObjectValue : sellerValueObjects){
            sellersDomain.add(simpleSellerTranslator.convertToDomainObject(sellerValueObjectValue));
        }

        return sellersDomain;
    }

    // Operations with ProductRepository

    public Product getSimpleProductById(Long id){
        return simpleProductTranslator.convertToDomainObject(simpleProductRepository.find(id));
    }

    public Product createSimpleProduct(){
        ProductValueObject productValueObjectValue = new ProductValueObject();
        Product productDomain =
                new Product(productValueObjectValue.getId());
        simpleProductRepository.add(productValueObjectValue);

        return productDomain;
    }

    public Product createSimpleProduct(
            String title, String description, Long ownerId, double sellerPrice){
        ProductValueObject productValueObjectValue = new ProductValueObject(title, description, ownerId, sellerPrice);

        Seller owner =
                simpleSellerTranslator.convertToDomainObject(simpleSellerRepository.find(ownerId));
        Product productDomain =
                new Product(
                        productValueObjectValue.getId(), title, description, owner, sellerPrice);
        owner.getProducts().add(productDomain);
        simpleProductRepository.add(productValueObjectValue);

        return productDomain;
    }

    public void updateSimpleProduct(Product product){
        ProductValueObject productValueObjectValue = simpleProductTranslator.convertToValueObject(product);
        simpleProductRepository.update(product.getId(), productValueObjectValue);
    }

    public Product findSimpleProductById(Long id){
        ProductValueObject productValueObjectValue = simpleProductRepository.find(id);
        return simpleProductTranslator.convertToDomainObject(productValueObjectValue);
    }

    public void deleteSimpleProductById(Long id){
        simpleProductRepository.delete(id);
    }

    public HashSet<Product> getAllSimpleProducts(){
        Collection<ProductValueObject> productValueObjects = simpleProductRepository.getAll();
        HashSet<Product> productsDomain =
                new HashSet<Product>();

        for (ProductValueObject productValueObjectValue : productValueObjects){
            productsDomain.add(simpleProductTranslator.convertToDomainObject(productValueObjectValue));
        }

        return productsDomain;
    }

    // Operations with BidRepository

    public Bid getBidById(Long id){
        return bidTranslator.convertToDomainObject(bidRepository.find(id));
    }

    public Bid createBid(Long buyerId, Long lotId, double bidAmount) {
        BidValueObject bidValueObjectValue = new BidValueObject(buyerId, bidAmount);
        Buyer buyer = simpleBuyerTranslator.convertToDomainObject(simpleBuyerRepository.find(buyerId));
        edu.core.java.auction.domain.Lot lot = lotTranslator.convertToDomainObject(lotRepository.find(lotId));
        Bid bidDomain = new Bid(buyer, bidAmount);

        if (buyer.getAccountBalance() >= bidAmount) {
            Bid oldBid = lot.getCurrentBid();
            if (oldBid != null) {
                Buyer oldBuyerDomain = lot.getCurrentBid().getBuyer();
                oldBuyerDomain.setAccountBalance(oldBuyerDomain.getAccountBalance() + lot.getCurrentBid().getBidAmount());
                BuyerValueObject oldBuyer = simpleBuyerRepository.find(oldBid.getBuyer().getId());
                oldBuyer.accountBalance += oldBid.getBidAmount();
                simpleBuyerRepository.update(oldBuyer.getId(), oldBuyer);
                bidRepository.delete(oldBid.getId());
            }

            lot.setCurrentBid(bidDomain);
            buyer.setAccountBalance(buyer.getAccountBalance() - bidAmount);
            BuyerValueObject newBuyer = simpleBuyerRepository.find(buyerId);
            newBuyer.accountBalance -= bidAmount;
            simpleBuyerRepository.update(newBuyer.getId(), newBuyer);

            bidRepository.add(bidValueObjectValue);
        }

        return bidDomain;
    }

    public void deleteBidById(Long id) { bidRepository.delete(id);
    }

    public HashSet<Bid> getAllBids(){
        Collection<BidValueObject> bidValueObjects = bidRepository.getAll();
        HashSet<Bid> bidsDomain = new HashSet<Bid>();

        for (BidValueObject bidValueObjectValue : bidValueObjects){
            bidsDomain.add(bidTranslator.convertToDomainObject(bidValueObjectValue));
        }

        return bidsDomain;
    }

    // Operations with LotRepository
    public edu.core.java.auction.domain.Lot createLot(
            double startPrice, Date endDate, Long productId, Long auctioneerId){
        Date startDate = new Date();
        LotValueObject lotValueObjectValue = new LotValueObject(startPrice, startDate, endDate, productId, auctioneerId);

        Product product = simpleProductTranslator.convertToDomainObject(simpleProductRepository.find(productId));
        Auctioneer auctioneer = simpleAuctioneerTranslator.convertToDomainObject(simpleAuctioneerRepository.find(auctioneerId));
        edu.core.java.auction.domain.Lot lotDomain =
                new edu.core.java.auction.domain.Lot(startPrice, startDate, endDate, product, auctioneer);
        Timer timer = new Timer();
        timer.schedule(new LotHandler(lotDomain), endDate.getTime() - startDate.getTime());
        System.out.println("Auction has started. Name: " + lotDomain.getProduct().getTitle());

        auctioneer.getLots().add(lotDomain);
        lotRepository.add(lotValueObjectValue);

        return lotDomain;
    }

    public edu.core.java.auction.domain.Lot getLotById(Long id){
        return lotTranslator.convertToDomainObject(lotRepository.find(id));
    }

    public void deleteLotById(Long id){
        lotRepository.delete(id);
    }

    public HashSet<edu.core.java.auction.domain.Lot> getAllLots(){
        Collection<LotValueObject> lotValueObjects = lotRepository.getAll();
        HashSet<edu.core.java.auction.domain.Lot> lotsDomain =
                new HashSet<edu.core.java.auction.domain.Lot>();

        for (LotValueObject lotValueObjectValue : lotValueObjects){
            lotsDomain.add(lotTranslator.convertToDomainObject(lotValueObjectValue));
        }

        return lotsDomain;
    }
}
