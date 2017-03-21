package edu.core.java.auction;

import edu.core.java.auction.domain.concrete.*;
import edu.core.java.auction.domain.concrete.Bid;
import edu.core.java.auction.domain.interfaces.*;
import edu.core.java.auction.loader.concrete.SimpleAuctioneerLoader;
import edu.core.java.auction.loader.interfaces.Loader;
import edu.core.java.auction.repository.concrete.*;
import edu.core.java.auction.repository.interfaces.Repository;
import edu.core.java.auction.translator.concrete.*;
import edu.core.java.auction.translator.interfaces.Translator;
import edu.core.java.auction.vo.*;
import edu.core.java.auction.vo.Lot;
import edu.core.java.auction.vo.SimpleAuctioneer;
import edu.core.java.auction.vo.SimpleBuyer;
import edu.core.java.auction.vo.SimpleProduct;
import edu.core.java.auction.vo.SimpleSeller;

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
        private edu.core.java.auction.domain.concrete.Lot lot;

        public LotHandler(edu.core.java.auction.domain.concrete.Lot lot){
            this.lot = lot;
        }

        @Override
        public void run() {
            Buyer winner = lot.getCurrentBid().getBuyer();
            Seller seller = lot.getProduct().getOwner();
            System.out.println("Auction has ended. Lot name:" + lot.getProduct().getTitle());
            System.out.println("The winner is " + simpleBuyerRepository.find(winner.getId()).name);

            simpleProductRepository.delete(lot.getProduct().getId());
            seller.getProducts().remove(lot.getProduct());
            bidRepository.delete(lot.getCurrentBid().getId());

            double sellerWinnings = seller.getAccountBalance() + lot.getCurrentBid().getBidAmount() * (1 - seller.getComissionPercentage());
            seller.setAccountBalance(sellerWinnings);
            SimpleSeller sellerValue = simpleSellerRepository.find(seller.getId());
            sellerValue.accountBalance = sellerWinnings;
            simpleSellerRepository.update(sellerValue.getId(), sellerValue);
            lot.getAuctioneer().getLots().remove(lot);
            lotRepository.delete(lot.getId());
        }
    }

    // Repositories

    private Repository<edu.core.java.auction.vo.Bid> bidRepository = new BidRepository();
    private Repository<Lot> lotRepository = new LotRepository();
    private Repository<SimpleAuctioneer> simpleAuctioneerRepository = new SimpleAuctioneerRepository();
    private Repository<SimpleBuyer> simpleBuyerRepository = new SimpleBuyerRepository();
    private Repository<SimpleProduct> simpleProductRepository = new SimpleProductRepository();
    private Repository<SimpleSeller> simpleSellerRepository = new SimpleSellerRepository();

    // Translators

    private Translator<edu.core.java.auction.vo.Bid, Bid> bidTranslator = new BidTranslator();
    private Translator<Lot, edu.core.java.auction.domain.concrete.Lot> lotTranslator = new LotTranslator();
    private Translator<SimpleAuctioneer, edu.core.java.auction.domain.concrete.SimpleAuctioneer>
            simpleAuctioneerTranslator = new SimpleAuctioneerTranslator();
    private Translator<SimpleBuyer, edu.core.java.auction.domain.concrete.SimpleBuyer>
            simpleBuyerTranslator = new SimpleBuyerTranslator();
    private Translator<SimpleProduct, edu.core.java.auction.domain.concrete.SimpleProduct>
            simpleProductTranslator = new SimpleProductTranslator();
    private Translator<SimpleSeller, edu.core.java.auction.domain.concrete.SimpleSeller>
            simpleSellerTranslator = new SimpleSellerTranslator();

    // Loaders

    private Loader<edu.core.java.auction.domain.concrete.SimpleAuctioneer> simpleAuctioneerLoader =
            new SimpleAuctioneerLoader(simpleAuctioneerRepository, lotRepository, lotTranslator);

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

    // Operations with SimpleAuctioneerRepository

    public edu.core.java.auction.domain.concrete.SimpleAuctioneer getSimpleAuctioneerById(Long id){
        return simpleAuctioneerTranslator.convertToDomainObject(simpleAuctioneerRepository.find(id));
    }

    public edu.core.java.auction.domain.concrete.SimpleAuctioneer createSimpleAuctioneer(){
        SimpleAuctioneer simpleAuctioneerValue = new SimpleAuctioneer();
        edu.core.java.auction.domain.concrete.SimpleAuctioneer simpleAuctioneerDomain =
                new edu.core.java.auction.domain.concrete.SimpleAuctioneer(simpleAuctioneerValue.getId());
        simpleAuctioneerRepository.add(simpleAuctioneerValue);

        return simpleAuctioneerDomain;
    }

    public edu.core.java.auction.domain.concrete.SimpleAuctioneer createSimpleAuctioneer(String name){
        SimpleAuctioneer simpleAuctioneerValue = new SimpleAuctioneer(name);
        edu.core.java.auction.domain.concrete.SimpleAuctioneer simpleAuctioneerDomain =
                new edu.core.java.auction.domain.concrete.SimpleAuctioneer(simpleAuctioneerValue.getId(), name);
        simpleAuctioneerRepository.add(simpleAuctioneerValue);

        return simpleAuctioneerDomain;
    }

    public void updateSimpleAuctioneer(edu.core.java.auction.domain.concrete.SimpleAuctioneer simpleAuctioneer){
        SimpleAuctioneer simpleAuctioneerValue = simpleAuctioneerTranslator.convertToValueObject(simpleAuctioneer);
        simpleAuctioneerRepository.update(simpleAuctioneerValue.getId(), simpleAuctioneerValue);
    }

    public edu.core.java.auction.domain.concrete.SimpleAuctioneer findSimpleAuctioneerById(Long id){
        SimpleAuctioneer simpleAuctioneerValue = simpleAuctioneerRepository.find(id);
        return simpleAuctioneerTranslator.convertToDomainObject(simpleAuctioneerValue);
    }

    public void deleteSimpleAuctioneerById(Long id){
        simpleAuctioneerRepository.delete(id);
    }

    public HashSet<edu.core.java.auction.domain.concrete.SimpleAuctioneer> getAllSimpleAuctioneers(){
        return simpleAuctioneerLoader.getAllEntities();
    }

    // Operations with SimpleBuyerRepository

    public edu.core.java.auction.domain.concrete.SimpleBuyer getSimpleBuyerById(Long id){
        return simpleBuyerTranslator.convertToDomainObject(simpleBuyerRepository.find(id));
    }

    public edu.core.java.auction.domain.concrete.SimpleBuyer createSimpleBuyer(){
        SimpleBuyer simpleBuyerValue = new SimpleBuyer();
        edu.core.java.auction.domain.concrete.SimpleBuyer simpleBuyerDomain =
                new edu.core.java.auction.domain.concrete.SimpleBuyer(simpleBuyerValue.getId());
        simpleBuyerRepository.add(simpleBuyerValue);

        return simpleBuyerDomain;
    }

    public edu.core.java.auction.domain.concrete.SimpleBuyer createSimpleBuyer(
            String name, double accountBalance){
        SimpleBuyer simpleBuyerValue = new SimpleBuyer(name, accountBalance);
        edu.core.java.auction.domain.concrete.SimpleBuyer simpleBuyerDomain =
                new edu.core.java.auction.domain.concrete.SimpleBuyer(simpleBuyerValue.getId(), name, accountBalance);
        simpleBuyerRepository.add(simpleBuyerValue);

        return simpleBuyerDomain;
    }

    public void updateSimpleBuyer(edu.core.java.auction.domain.concrete.SimpleBuyer simpleBuyer){
        SimpleBuyer simpleBuyerValue = simpleBuyerTranslator.convertToValueObject(simpleBuyer);
        simpleBuyerRepository.update(simpleBuyerValue.getId(), simpleBuyerValue);
    }

    public edu.core.java.auction.domain.concrete.SimpleBuyer findSimpleBuyerById(Long id){
        SimpleBuyer simpleBuyerValue = simpleBuyerRepository.find(id);
        return simpleBuyerTranslator.convertToDomainObject(simpleBuyerValue);
    }

    public void deleteSimpleBuyerById(Long id){
        simpleBuyerRepository.delete(id);
    }

    public HashSet<edu.core.java.auction.domain.concrete.SimpleBuyer> getAllSimpleBuyers(){
        Collection<SimpleBuyer> simpleBuyers = simpleBuyerRepository.getAll();
        HashSet<edu.core.java.auction.domain.concrete.SimpleBuyer> simpleBuyersDomain =
                new HashSet<edu.core.java.auction.domain.concrete.SimpleBuyer>();

        for (SimpleBuyer simpleBuyerValue : simpleBuyers){
            simpleBuyersDomain.add(simpleBuyerTranslator.convertToDomainObject(simpleBuyerValue));
        }

        return simpleBuyersDomain;
    }

    // Operations with SimpleSellerRepository

    public edu.core.java.auction.domain.concrete.SimpleSeller getSimpleSellerById(Long id){
        return simpleSellerTranslator.convertToDomainObject(simpleSellerRepository.find(id));
    }

    public edu.core.java.auction.domain.concrete.SimpleSeller createSimpleSeller(){
        SimpleSeller simpleSellerValue = new SimpleSeller();
        edu.core.java.auction.domain.concrete.SimpleSeller simpleSellerDomain =
                new edu.core.java.auction.domain.concrete.SimpleSeller(simpleSellerValue.getId());
        simpleSellerRepository.add(simpleSellerValue);

        return simpleSellerDomain;
    }

    public edu.core.java.auction.domain.concrete.SimpleSeller createSimpleSeller(
            String name, double accountBalance, double comissionPercentage){
        SimpleSeller simpleSellerValue = new SimpleSeller(name, accountBalance, comissionPercentage);
        edu.core.java.auction.domain.concrete.SimpleSeller simpleSellerDomain =
                new edu.core.java.auction.domain.concrete.SimpleSeller(simpleSellerValue.getId(), name, accountBalance, comissionPercentage);
        simpleSellerRepository.add(simpleSellerValue);

        return simpleSellerDomain;
    }

    public void updateSimpleSeller(edu.core.java.auction.domain.concrete.SimpleSeller simpleSeller){
        SimpleSeller simpleSellerValue = simpleSellerTranslator.convertToValueObject(simpleSeller);
        simpleSellerRepository.update(simpleSellerValue.getId(), simpleSellerValue);
    }

    public edu.core.java.auction.domain.concrete.SimpleSeller findSimpleSellerById(Long id){
        SimpleSeller simpleSellerValue = simpleSellerRepository.find(id);
        return simpleSellerTranslator.convertToDomainObject(simpleSellerValue);
    }

    public void deleteSimpleSellerById(Long id){
        simpleSellerRepository.delete(id);
    }

    public HashSet<edu.core.java.auction.domain.concrete.SimpleSeller> getAllSimpleSellers(){
        Collection<SimpleSeller> simpleSellers = simpleSellerRepository.getAll();
        HashSet<edu.core.java.auction.domain.concrete.SimpleSeller> simpleSellersDomain =
                new HashSet<edu.core.java.auction.domain.concrete.SimpleSeller>();

        for (SimpleSeller simpleSellerValue : simpleSellers){
            simpleSellersDomain.add(simpleSellerTranslator.convertToDomainObject(simpleSellerValue));
        }

        return simpleSellersDomain;
    }

    // Operations with SimpleProductRepository

    public edu.core.java.auction.domain.concrete.SimpleProduct getSimpleProductById(Long id){
        return simpleProductTranslator.convertToDomainObject(simpleProductRepository.find(id));
    }

    public edu.core.java.auction.domain.concrete.SimpleProduct createSimpleProduct(){
        SimpleProduct simpleProductValue = new SimpleProduct();
        edu.core.java.auction.domain.concrete.SimpleProduct simpleProductDomain =
                new edu.core.java.auction.domain.concrete.SimpleProduct(simpleProductValue.getId());
        simpleProductRepository.add(simpleProductValue);

        return simpleProductDomain;
    }

    public edu.core.java.auction.domain.concrete.SimpleProduct createSimpleProduct(
            String title, String description, Long ownerId, double sellerPrice){
        SimpleProduct simpleProductValue = new SimpleProduct(title, description, ownerId, sellerPrice);

        edu.core.java.auction.domain.concrete.SimpleSeller owner =
                simpleSellerTranslator.convertToDomainObject(simpleSellerRepository.find(ownerId));
        edu.core.java.auction.domain.concrete.SimpleProduct simpleProductDomain =
                new edu.core.java.auction.domain.concrete.SimpleProduct(
                        simpleProductValue.getId(), title, description, owner, sellerPrice);
        owner.getProducts().add(simpleProductDomain);
        simpleProductRepository.add(simpleProductValue);

        return simpleProductDomain;
    }

    public void updateSimpleProduct(edu.core.java.auction.domain.concrete.SimpleProduct simpleProduct){
        SimpleProduct simpleProductValue = simpleProductTranslator.convertToValueObject(simpleProduct);
        simpleProductRepository.update(simpleProduct.getId(), simpleProductValue);
    }

    public edu.core.java.auction.domain.concrete.SimpleProduct findSimpleProductById(Long id){
        SimpleProduct simpleProductValue = simpleProductRepository.find(id);
        return simpleProductTranslator.convertToDomainObject(simpleProductValue);
    }

    public void deleteSimpleProductById(Long id){
        simpleProductRepository.delete(id);
    }

    public HashSet<edu.core.java.auction.domain.concrete.SimpleProduct> getAllSimpleProducts(){
        Collection<SimpleProduct> simpleProducts = simpleProductRepository.getAll();
        HashSet<edu.core.java.auction.domain.concrete.SimpleProduct> simpleProductsDomain =
                new HashSet<edu.core.java.auction.domain.concrete.SimpleProduct>();

        for (SimpleProduct simpleProductValue : simpleProducts){
            simpleProductsDomain.add(simpleProductTranslator.convertToDomainObject(simpleProductValue));
        }

        return simpleProductsDomain;
    }

    // Operations with BidRepository

    public Bid getBidById(Long id){
        return bidTranslator.convertToDomainObject(bidRepository.find(id));
    }

    public edu.core.java.auction.domain.concrete.Bid createBid(Long buyerId, Long lotId, double bidAmount) {
        edu.core.java.auction.vo.Bid bidValue = new edu.core.java.auction.vo.Bid(buyerId, bidAmount);
        Buyer buyer = simpleBuyerTranslator.convertToDomainObject(simpleBuyerRepository.find(buyerId));
        edu.core.java.auction.domain.concrete.Lot lot = lotTranslator.convertToDomainObject(lotRepository.find(lotId));
        Bid bidDomain = new Bid(buyer, bidAmount);

        if (buyer.getAccountBalance() >= bidAmount) {
            Bid oldBid = lot.getCurrentBid();
            if (oldBid != null) {
                Buyer oldBuyerDomain = lot.getCurrentBid().getBuyer();
                oldBuyerDomain.setAccountBalance(oldBuyerDomain.getAccountBalance() + lot.getCurrentBid().getBidAmount());
                SimpleBuyer oldBuyer = simpleBuyerRepository.find(oldBid.getBuyer().getId());
                oldBuyer.accountBalance += oldBid.getBidAmount();
                simpleBuyerRepository.update(oldBuyer.getId(), oldBuyer);
                bidRepository.delete(oldBid.getId());
            }

            lot.setCurrentBid(bidDomain);
            buyer.setAccountBalance(buyer.getAccountBalance() - bidAmount);
            SimpleBuyer newBuyer = simpleBuyerRepository.find(buyerId);
            newBuyer.accountBalance -= bidAmount;
            simpleBuyerRepository.update(newBuyer.getId(), newBuyer);

            bidRepository.add(bidValue);
        }

        return bidDomain;
    }

    public void deleteBidById(Long id) { bidRepository.delete(id);
    }

    public HashSet<Bid> getAllBids(){
        Collection<edu.core.java.auction.vo.Bid> bids = bidRepository.getAll();
        HashSet<Bid> bidsDomain = new HashSet<Bid>();

        for (edu.core.java.auction.vo.Bid bidValue : bids){
            bidsDomain.add(bidTranslator.convertToDomainObject(bidValue));
        }

        return bidsDomain;
    }

    // Operations with LotRepository
    public edu.core.java.auction.domain.concrete.Lot createLot(
            double startPrice, Date endDate, Long productId, Long auctioneerId){
        Date startDate = new Date();
        Lot lotValue = new Lot(startPrice, startDate, endDate, productId, auctioneerId);

        Product product = simpleProductTranslator.convertToDomainObject(simpleProductRepository.find(productId));
        Auctioneer auctioneer = simpleAuctioneerTranslator.convertToDomainObject(simpleAuctioneerRepository.find(auctioneerId));
        edu.core.java.auction.domain.concrete.Lot lotDomain =
                new edu.core.java.auction.domain.concrete.Lot(startPrice, startDate, endDate, product, auctioneer);
        Timer timer = new Timer();
        timer.schedule(new LotHandler(lotDomain), endDate.getTime() - startDate.getTime());
        System.out.println("Auction has started. Name: " + lotDomain.getProduct().getTitle());

        auctioneer.getLots().add(lotDomain);
        lotRepository.add(lotValue);

        return lotDomain;
    }

    public edu.core.java.auction.domain.concrete.Lot getLotById(Long id){
        return lotTranslator.convertToDomainObject(lotRepository.find(id));
    }

    public void deleteLotById(Long id){
        lotRepository.delete(id);
    }

    public HashSet<edu.core.java.auction.domain.concrete.Lot> getAllLots(){
        Collection<Lot> lots = lotRepository.getAll();
        HashSet<edu.core.java.auction.domain.concrete.Lot> lotsDomain =
                new HashSet<edu.core.java.auction.domain.concrete.Lot>();

        for (Lot lotValue : lots){
            lotsDomain.add(lotTranslator.convertToDomainObject(lotValue));
        }

        return lotsDomain;
    }
}
