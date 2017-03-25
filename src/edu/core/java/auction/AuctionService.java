package edu.core.java.auction;

import edu.core.java.auction.domain.*;
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
            System.out.println("The winner is " + buyerRepository.find(winner.getId()).name);

            seller.addMoney(lot.getCurrentBid().getBidAmount() * (1 - seller.getComissionPercentage()));
            sellerRepository.update(seller.getId(), sellerTranslator.convertToValueObject(seller));
            deleteProductById(lot.getProduct().getId());
        }
    }

    // Repositories

    private Repository<BidValueObject> bidRepository = new BidRepository();
    private Repository<LotValueObject> lotRepository = new LotRepository();
    private Repository<AuctioneerValueObject> auctioneerRepository = new AuctioneerRepository();
    private Repository<BuyerValueObject> buyerRepository = new BuyerRepository();
    private Repository<ProductValueObject> productRepository = new ProductRepository();
    private Repository<SellerValueObject> sellerRepository = new SellerRepository();

    // Translators

    private Translator<BidValueObject, Bid> bidTranslator = new BidTranslator();
    private Translator<LotValueObject, Lot> lotTranslator = new LotTranslator();
    private Translator<BuyerValueObject, Buyer> buyerTranslator = new BuyerTranslator();
    private Translator<ProductValueObject, Product> productTranslator = new ProductTranslator();
    private Translator<SellerValueObject, Seller> sellerTranslator = new SellerTranslator();

    // Loaders

    private Loader<Auctioneer> auctioneerLoader = new AuctioneerLoader(auctioneerRepository, lotRepository, lotTranslator);

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

    public Auctioneer getAuctioneerById(Long id){
        return auctioneerLoader.getEntity(id);
    }

    public Auctioneer createAuctioneer(){
        AuctioneerValueObject auctioneerValueObjectValue = new AuctioneerValueObject();
        auctioneerValueObjectValue.id = auctioneerRepository.getMaxId() + 1;
        auctioneerRepository.incMaxId();
        auctioneerRepository.add(auctioneerValueObjectValue);

        return new Auctioneer(auctioneerValueObjectValue.id);
    }

    public Auctioneer createAuctioneer(String name){
        AuctioneerValueObject auctioneerValueObjectValue = new AuctioneerValueObject();
        auctioneerValueObjectValue.id = auctioneerRepository.getMaxId() + 1;
        auctioneerRepository.incMaxId();
        auctioneerValueObjectValue.name = name;
        auctioneerRepository.add(auctioneerValueObjectValue);

        return new Auctioneer(auctioneerValueObjectValue.id, name);
    }

    public void updateAuctioneer(Auctioneer auctioneer){
        AuctioneerValueObject auctioneerValueObjectValue = new AuctioneerValueObject();
        auctioneerValueObjectValue.id = auctioneer.getId();
        auctioneerValueObjectValue.name = auctioneer.getName();

        auctioneerRepository.update(auctioneerValueObjectValue.id, auctioneerValueObjectValue);
    }

    public Auctioneer findAuctioneerById(Long id){
        return auctioneerLoader.getEntity(id);
    }

    public void deleteAuctioneerById(Long id){
        ArrayList<Long> lotIDs = new ArrayList<>();
        for (LotValueObject lotValueObject : lotRepository.getAll()) {
            if (lotValueObject.auctioneerId.equals(id)){
                lotIDs.add(lotValueObject.id);
            }
        };

        for (Long lotId : lotIDs){
            deleteLotById(lotId);
        }

        auctioneerRepository.delete(id);
    }

    public HashSet<Auctioneer> getAllAuctioneers(){
        return auctioneerLoader.getAllEntities();
    }

    // Operations with BuyerRepository

    public Buyer getBuyerById(Long id){
        return buyerTranslator.convertToDomainObject(buyerRepository.find(id));
    }

    public Buyer createBuyer(){
        BuyerValueObject buyerValueObjectValue = new BuyerValueObject();
        buyerValueObjectValue.id = buyerRepository.getMaxId();
        buyerRepository.incMaxId();
        buyerRepository.add(buyerValueObjectValue);

        return new Buyer(buyerValueObjectValue.id);
    }

    public Buyer createBuyer(String name, double accountBalance){
        BuyerValueObject buyerValueObjectValue = new BuyerValueObject();
        buyerValueObjectValue.id = buyerRepository.getMaxId();
        buyerRepository.incMaxId();
        buyerValueObjectValue.name = name;
        buyerValueObjectValue.accountBalance = accountBalance;
        buyerRepository.add(buyerValueObjectValue);

        return new Buyer(buyerValueObjectValue.id, name, accountBalance);
    }

    public void updateBuyer(Buyer buyer){
        BuyerValueObject buyerValueObjectValue = buyerTranslator.convertToValueObject(buyer);
        buyerRepository.update(buyerValueObjectValue.id, buyerValueObjectValue);
    }

    public Buyer findBuyerById(Long id){
        BuyerValueObject buyerValueObjectValue = buyerRepository.find(id);
        return buyerTranslator.convertToDomainObject(buyerValueObjectValue);
    }

    public void deleteBuyerById(Long id){
        ArrayList<Long> bidIDs = new ArrayList<>();
        for (BidValueObject bidValueObject : bidRepository.getAll()) {
            if (bidValueObject.buyerId.equals(id)){
                bidIDs.add(bidValueObject.id);
            }
        };

        for (Long bidId : bidIDs){
            deleteBidById(bidId);
        }

        buyerRepository.delete(id);
    }

    public HashSet<Buyer> getAllBuyers(){
        Collection<BuyerValueObject> buyerValueObjects = buyerRepository.getAll();
        HashSet<Buyer> buyers = new HashSet<Buyer>();

        for (BuyerValueObject buyerValueObjectValue : buyerValueObjects){
            buyers.add(buyerTranslator.convertToDomainObject(buyerValueObjectValue));
        }

        return buyers;
    }

    // Operations with SellerRepository

    public Seller getSellerById(Long id){
        return sellerTranslator.convertToDomainObject(sellerRepository.find(id));
    }

    public Seller createSeller(){
        SellerValueObject sellerValueObjectValue = new SellerValueObject();
        sellerValueObjectValue.id = sellerRepository.getMaxId() + 1;
        sellerRepository.incMaxId();
        sellerRepository.add(sellerValueObjectValue);

        return new Seller(sellerValueObjectValue.id);
    }

    public Seller createSeller(String name, double accountBalance, double comissionPercentage){
        SellerValueObject sellerValueObjectValue = new SellerValueObject();
        sellerValueObjectValue.id = sellerRepository.getMaxId() + 1;
        sellerRepository.incMaxId();
        sellerValueObjectValue.name = name;
        sellerValueObjectValue.accountBalance = accountBalance;
        sellerValueObjectValue.comissionPercentage = comissionPercentage;
        sellerRepository.add(sellerValueObjectValue);

        return new Seller(sellerValueObjectValue.id, name, accountBalance, comissionPercentage);
    }

    public void updateSeller(Seller seller){
        SellerValueObject sellerValueObjectValue = sellerTranslator.convertToValueObject(seller);
        sellerRepository.update(sellerValueObjectValue.id, sellerValueObjectValue);
    }

    public Seller findSellerById(Long id){
        SellerValueObject sellerValueObjectValue = sellerRepository.find(id);
        return sellerTranslator.convertToDomainObject(sellerValueObjectValue);
    }

    public void deleteSellerById(Long id){
        ArrayList<Product> products = new ArrayList<>();
        for (ProductValueObject productValueObject : productRepository.getAll()) {
            if (productValueObject.ownerId.equals(id)){
                products.add(productTranslator.convertToDomainObject(productValueObject));
            }
        };

        for (Product product: products){
            product.setOwner(null);
            updateProduct(product);
        }

        sellerRepository.delete(id);
    }

    public HashSet<Seller> getAllSellers(){
        Collection<SellerValueObject> sellerValueObjects = sellerRepository.getAll();
        HashSet<Seller> sellers = new HashSet<Seller>();

        for (SellerValueObject sellerValueObjectValue : sellerValueObjects){
            sellers.add(sellerTranslator.convertToDomainObject(sellerValueObjectValue));
        }

        return sellers;
    }

    // Operations with ProductRepository

    public Product getProductById(Long id){
        return productTranslator.convertToDomainObject(productRepository.find(id));
    }

    public Product createProduct(){
        ProductValueObject productValueObjectValue = new ProductValueObject();
        productValueObjectValue.id = productRepository.getMaxId();
        productRepository.incMaxId();
        productRepository.add(productValueObjectValue);

        return new Product(productValueObjectValue.id);
    }

    public Product createProduct(String title, String description, Long ownerId, double sellerPrice){
        ProductValueObject productValueObjectValue = new ProductValueObject();
        productValueObjectValue.id = productRepository.getMaxId() + 1;
        productRepository.incMaxId();
        productValueObjectValue.title = title;
        productValueObjectValue.description = description;
        productValueObjectValue.ownerId = ownerId;
        productValueObjectValue.sellerPrice = sellerPrice;
        productRepository.add(productValueObjectValue);

        Seller owner = sellerTranslator.convertToDomainObject(sellerRepository.find(ownerId));
        Product product = new Product(productValueObjectValue.id, title, description, owner, sellerPrice);
        owner.getProducts().add(product);
        productRepository.add(productValueObjectValue);
        return product;
    }

    public void updateProduct(Product product){
        ProductValueObject productValueObjectValue = productTranslator.convertToValueObject(product);
        productRepository.update(product.getId(), productValueObjectValue);
    }

    public Product findProductById(Long id){
        ProductValueObject productValueObjectValue = productRepository.find(id);
        return productTranslator.convertToDomainObject(productValueObjectValue);
    }

    public void deleteProductById(Long id){
        Long lotId = (long)0;

        for (LotValueObject lotValueObject : lotRepository.getAll()){
            if (lotValueObject.productId.equals(id)){
                lotId = lotValueObject.id;
                break;
            }
        }

        deleteLotById(lotId);
        productRepository.delete(id);
    }

    public HashSet<Product> getAllProducts(){
        Collection<ProductValueObject> productValueObjects = productRepository.getAll();
        HashSet<Product> productsDomain =
                new HashSet<Product>();

        for (ProductValueObject productValueObjectValue : productValueObjects){
            productsDomain.add(productTranslator.convertToDomainObject(productValueObjectValue));
        }

        return productsDomain;
    }

    // Operations with BidRepository

    public Bid getBidById(Long id){
        return bidTranslator.convertToDomainObject(bidRepository.find(id));
    }

    public Bid createBid(Long buyerId, Long lotId, double bidAmount) {
        BidValueObject bidValueObjectValue = new BidValueObject();
        bidValueObjectValue.id = bidRepository.getMaxId() + 1;
        bidRepository.incMaxId();
        bidValueObjectValue.buyerId = buyerId;
        bidValueObjectValue.amount = bidAmount;

        Buyer buyer = buyerTranslator.convertToDomainObject(buyerRepository.find(buyerId));
        Lot lot = lotTranslator.convertToDomainObject(lotRepository.find(lotId));
        Bid bid = new Bid(bidValueObjectValue.id, buyer, bidAmount);

        if (buyer.getAccountBalance() >= bidAmount) {
            Bid oldBid = lot.getCurrentBid();
            if (lot.getCurrentBid() != null) {
                deleteBidById(oldBid.getId());
            }

            lot.setCurrentBid(bid);
            buyer.getBids().add(bid);
            buyer.withdrawMoney(bidAmount);
            buyerRepository.update(buyer.getId(), buyerTranslator.convertToValueObject(buyer));
            bidRepository.add(bidValueObjectValue);
        }

        return bid;
    }

    public void deleteBidById(Long id) {
        BidValueObject bidValueObject = bidRepository.find(id);
        Buyer buyer = buyerTranslator.convertToDomainObject(buyerRepository.find(bidValueObject.buyerId));
        buyer.getBids().removeIf(buyerBid -> buyerBid.getId().equals(bidValueObject.id));
        buyer.addMoney(bidValueObject.amount);
        buyerRepository.update(buyer.getId(), buyerTranslator.convertToValueObject(buyer));
        bidRepository.delete(id);
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
    public Lot createLot(double startPrice, Date endDate, Long productId, Long auctioneerId){
        Date startDate = new Date();
        LotValueObject lotValueObjectValue = new LotValueObject();
        lotValueObjectValue.id = lotRepository.getMaxId() + 1;
        lotRepository.incMaxId();
        lotValueObjectValue.startPrice = startPrice;
        lotValueObjectValue.startDate = startDate;
        lotValueObjectValue.endDate = endDate;
        lotValueObjectValue.productId = productId;
        lotValueObjectValue.auctioneerId = auctioneerId;
        lotRepository.add(lotValueObjectValue);

        Product product = productTranslator.convertToDomainObject(productRepository.find(productId));
        Auctioneer auctioneer = auctioneerLoader.getEntity(auctioneerId);
        Lot lotDomain = new Lot(startPrice, startDate, endDate, product, auctioneer, null);
        Timer timer = new Timer();
        timer.schedule(new LotHandler(lotDomain), (new Date()).getTime() + endDate.getTime() - startDate.getTime());
        System.out.println("Auction has started. Name: " + lotDomain.getProduct().getTitle());

        auctioneer.getLots().add(lotDomain);
        return lotDomain;
    }

    public Lot getLotById(Long id){
        return lotTranslator.convertToDomainObject(lotRepository.find(id));
    }

    public void deleteLotById(Long id){
        Lot lot = lotTranslator.convertToDomainObject(lotRepository.find(id));
        lot.getAuctioneer().getLots().removeIf(aucLot -> aucLot.getId().equals(id));
        if (lot.getCurrentBid() != null)
            deleteBidById(lot.getCurrentBid().getId());
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
