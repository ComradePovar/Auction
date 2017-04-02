package edu.core.java.auction;

import edu.core.java.auction.domain.*;
import edu.core.java.auction.loader.*;
import edu.core.java.auction.repository.*;
import edu.core.java.auction.translator.*;
import edu.core.java.auction.vo.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Maxim on 18.03.2017.
 */
public class AuctionService {
    private static AuctionService instance = new AuctionService();
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private HashMap<Long, Timer> timers = new HashMap<>();

    private class LotHandler extends TimerTask{
        private Long lotId;

        public LotHandler(Long lotId){
            this.lotId = lotId;
        }

        @Override
        public void run() {
            LotValueObject lotValueObject = lotRepository.find(lotId);
            ProductValueObject productValueObject = productRepository.find(lotValueObject.productId);

            System.out.println("Auction has ended. Product name: " + productValueObject.title);
            if (lotValueObject.currentBidId != 0){
                BidValueObject bidValueObject = bidRepository.find(lotValueObject.currentBidId);
                BuyerValueObject winner = buyerRepository.find(bidValueObject.buyerId);

                SellerValueObject sellerValueObject = sellerRepository.find(productValueObject.ownerId);
                sellerValueObject.accountBalance += bidValueObject.amount * (100 - sellerValueObject.commissionPercentage)/100;
                sellerRepository.update(sellerValueObject);
                productRepository.delete(productValueObject.id);
                bidRepository.delete(lotValueObject.currentBidId);
                System.out.println("The winner is " + winner.name);
            } else {
                System.out.println("No winner.");
            }

            lotRepository.delete(lotId);
            timers.remove(lotId);
        }
    }

    // Repositories

    private BidRepository bidRepository = new BidRepository();
    private LotRepository lotRepository = new LotRepository();
    private BuyerRepository buyerRepository = new BuyerRepository();
    private SellerRepository sellerRepository = new SellerRepository();
    private ProductRepository productRepository = new ProductRepository();

    // Translators

    private BidTranslator bidTranslator = new BidTranslator();
    private LotTranslator lotTranslator = new LotTranslator();
    private BuyerTranslator buyerTranslator = new BuyerTranslator();
    private SellerTranslator sellerTranslator = new SellerTranslator();
    private ProductTranslator productTranslator = new ProductTranslator();

    // Loaders

    private BidLoader bidLoader = new BidLoader(bidRepository, bidTranslator);
    private LotLoader lotLoader = new LotLoader(lotRepository, lotTranslator);
    private BuyerLoader buyerLoader = new BuyerLoader(buyerRepository, buyerTranslator);
    private SellerLoader sellerLoader = new SellerLoader(sellerRepository, sellerTranslator);
    private ProductLoader productLoader = new ProductLoader(productRepository, productTranslator);

    private AuctionService(){}

    public static AuctionService getInstance(){
        if (instance == null)
            instance = new AuctionService();
        return instance;
    }

    public DateFormat getDateFormat(){
        return dateFormat;
    }

    public void setDateFormat(DateFormat newDateFormat){
        dateFormat = newDateFormat;
    }

    public BidRepository getBidRepository() {
        return bidRepository;
    }

    public LotRepository getLotRepository() {
        return lotRepository;
    }

    public BuyerRepository getBuyerRepository() {
        return buyerRepository;
    }

    public SellerRepository getSellerRepository() {
        return sellerRepository;
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public BidTranslator getBidTranslator() {
        return bidTranslator;
    }

    public LotTranslator getLotTranslator() {
        return lotTranslator;
    }

    public BuyerTranslator getBuyerTranslator() {
        return buyerTranslator;
    }

    public SellerTranslator getSellerTranslator() {
        return sellerTranslator;
    }

    public ProductTranslator getProductTranslator() {
        return productTranslator;
    }

    public BidLoader getBidLoader() {
        return bidLoader;
    }

    public LotLoader getLotLoader() {
        return lotLoader;
    }

    public BuyerLoader getBuyerLoader() {
        return buyerLoader;
    }

    public SellerLoader getSellerLoader() {
        return sellerLoader;
    }

    public ProductLoader getProductLoader() {
        return productLoader;
    }

    // Operations with Buyer

    public BuyerValueObject getBuyerById(Long id){
        return buyerRepository.find(id);
    }

    public BuyerValueObject createBuyer(String name, double accountBalance){
        BuyerValueObject buyerValueObject = new BuyerValueObject(buyerRepository.getMaxId() + 1, name, accountBalance);
        buyerRepository.incMaxId();
        buyerRepository.add(buyerValueObject);

        return buyerValueObject;
    }

    public void updateBuyer(BuyerValueObject buyer){
        buyerRepository.update(buyer);
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

    public Collection<BuyerValueObject> getAllBuyers(){
        return buyerRepository.getAll();
    }

    // Operations with Seller

    public SellerValueObject getSellerById(Long id){
        return sellerRepository.find(id);
    }

    public SellerValueObject createSeller(String name, double accountBalance, double commissionPercentage){
        SellerValueObject sellerValueObjectValue = new SellerValueObject(sellerRepository.getMaxId() + 1, name,
                accountBalance, commissionPercentage);
        sellerRepository.incMaxId();
        sellerRepository.add(sellerValueObjectValue);

        return sellerValueObjectValue;
    }

    public void updateSeller(SellerValueObject seller){
        sellerRepository.update(seller);
    }

    public void deleteSellerById(Long id){
        ArrayList<ProductValueObject> products = new ArrayList<>();
        for (ProductValueObject productValueObject : productRepository.getAll()) {
            if (productValueObject.ownerId.equals(id)){
                products.add(productValueObject);
            }
        };

        for (ProductValueObject productValueObject : products){
            productValueObject.ownerId = (long)0;
            productRepository.update(productValueObject);
        }

        sellerRepository.delete(id);
    }

    public Collection<SellerValueObject> getAllSellers(){
        return sellerRepository.getAll();
    }

    // Operations with Product

    public ProductValueObject getProductById(Long id){
        return productRepository.find(id);
    }

    public ProductValueObject createProduct(String title, String description, Long ownerId){
        ProductValueObject productValueObject = new ProductValueObject(productRepository.getMaxId() + 1,
                title, description, ownerId);
        productRepository.incMaxId();
        productRepository.add(productValueObject);

        return productValueObject;
    }

    public void updateProduct(ProductValueObject product){
        productRepository.update(product);
    }

    public void deleteProductById(Long id){
        Long lotId = (long)0;

        for (LotValueObject lotValueObject : lotRepository.getAll()){
            if (lotValueObject.productId.equals(id)){
                lotId = lotValueObject.id;
                break;
            }
        }

        if (lotId != 0)
            deleteLotById(lotId);
        productRepository.delete(id);
    }

    public Collection<ProductValueObject> getAllProducts(){
        return productRepository.getAll();
    }

    // Operations with Bid

    public BidValueObject getBidById(Long id){
        return bidRepository.find(id);
    }

    public BidValueObject createBid(Long buyerId, Long lotId, double bidAmount) {
        BidValueObject bidValueObject = new BidValueObject(bidRepository.getMaxId() + 1, buyerId, bidAmount);
        bidRepository.incMaxId();

        BuyerValueObject buyerValueObject = buyerRepository.find(buyerId);
        LotValueObject lotValueObject = lotRepository.find(lotId);

        if (buyerValueObject.accountBalance >= bidAmount && bidAmount >= lotValueObject.startPrice) {
            if (lotValueObject.currentBidId != 0) {
                BidValueObject oldBidValueObject = bidRepository.find(lotValueObject.currentBidId);
                if (oldBidValueObject.amount < bidAmount) {
                    deleteBidById(oldBidValueObject.id);
                } else {
                    throw new NotImplementedException();
                }
            }

            lotValueObject.currentBidId = bidValueObject.id;
            buyerValueObject.accountBalance -= bidAmount;
            buyerRepository.update(buyerValueObject);
            lotRepository.update(lotValueObject);
            bidRepository.add(bidValueObject);
        } else {
            throw new NotImplementedException();
        }

        return bidValueObject;
    }

    public void deleteBidById(Long id) {
        BidValueObject bidValueObject = bidRepository.find(id);
        BuyerValueObject buyerValueObject = buyerRepository.find(bidValueObject.buyerId);
        buyerValueObject.accountBalance += bidValueObject.amount;

        for (LotValueObject lotValueObject : lotRepository.getAll()){
            if (lotValueObject.currentBidId.equals(id)){
                lotValueObject.currentBidId = (long)0;
                lotRepository.update(lotValueObject);
                break;
            }
        }

        buyerRepository.update(buyerValueObject);
        bidRepository.delete(id);
    }

    public void updateBid(BidValueObject bid){
        bidRepository.update(bid);
    }

    public Collection<BidValueObject> getAllBids(){
        return bidRepository.getAll();
    }

    // Operations with Lot

    public LotValueObject getLotById(Long id){
        return lotRepository.find(id);
    }

    public LotValueObject createLot(double startPrice, Date endDate, Long productId){
        LotValueObject lotValueObject = new LotValueObject(lotRepository.getMaxId() + 1, startPrice,
                endDate, productId, (long)0);
        lotRepository.incMaxId();
        lotRepository.add(lotValueObject);

        Product product = productTranslator.convertToDomainObject(productRepository.find(productId));
        Lot lot = new Lot(lotValueObject.id, startPrice, endDate, productLoader.getEntity(productId), null);

//        Timer timer = new Timer();
//        timer.schedule(new LotHandler(lot.getId()), endDate.getTime() - (new Date()).getTime());
//        timers.put(lot.getId(), timer);

        System.out.println("Auction has started. Name: " + lot.getProduct().getTitle());

        return lotValueObject;
    }

    public void deleteLotById(Long id){
        LotValueObject lotValueObject = lotRepository.find(id);

        if (lotValueObject.currentBidId != 0)
            deleteBidById(lotValueObject.currentBidId);

        lotRepository.delete(id);
    }

    public void updateLot(LotValueObject lot){
        lotRepository.update(lot);
    }

    public Collection<LotValueObject> getAllLots(){
        return lotRepository.getAll();
    }
}
