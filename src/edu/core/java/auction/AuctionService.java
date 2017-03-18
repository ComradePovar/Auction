package edu.core.java.auction;

import edu.core.java.auction.domain.concrete.*;
import edu.core.java.auction.domain.interfaces.Person;
import edu.core.java.auction.loader.concrete.SimpleAuctioneerLoader;
import edu.core.java.auction.loader.interfaces.Loader;
import edu.core.java.auction.repository.concrete.*;
import edu.core.java.auction.repository.interfaces.Repository;
import edu.core.java.auction.translator.concrete.*;
import edu.core.java.auction.translator.interfaces.Translator;
import edu.core.java.auction.vo.Lot;
import edu.core.java.auction.vo.SimpleAuctioneer;
import edu.core.java.auction.vo.SimpleBuyer;
import edu.core.java.auction.vo.SimpleProduct;
import edu.core.java.auction.vo.SimpleSeller;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Maxim on 18.03.2017.
 */
public class AuctionService {
    private static AuctionService instance = new AuctionService();

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


    // Operations with SimpleAuctioneerRepository
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
        simpleAuctioneerRepository.update(simpleAuctioneerValue);
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
    public edu.core.java.auction.domain.concrete.SimpleBuyer createSimpleBuyer(){
        SimpleBuyer simpleBuyerValue = new SimpleBuyer();
        edu.core.java.auction.domain.concrete.SimpleBuyer simpleBuyerDomain =
                new edu.core.java.auction.domain.concrete.SimpleBuyer(simpleBuyerValue.getId());
        simpleBuyerRepository.add(simpleBuyerValue);

        return simpleBuyerDomain;
    }

    public edu.core.java.auction.domain.concrete.SimpleBuyer createSimpleBuyer(
            String name, String phone, double accountBalance){
        SimpleBuyer simpleBuyerValue = new SimpleBuyer(name, phone, accountBalance);
        edu.core.java.auction.domain.concrete.SimpleBuyer simpleBuyerDomain =
                new edu.core.java.auction.domain.concrete.SimpleBuyer(simpleBuyerValue.getId(), name, phone, accountBalance);
        simpleBuyerRepository.add(simpleBuyerValue);

        return simpleBuyerDomain;
    }

    public void updateSimpleBuyer(edu.core.java.auction.domain.concrete.SimpleBuyer simpleBuyer){
        SimpleBuyer simpleBuyerValue = simpleBuyerTranslator.convertToValueObject(simpleBuyer);
        simpleBuyerRepository.update(simpleBuyerValue);
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
    public edu.core.java.auction.domain.concrete.SimpleSeller createSimpleSeller(){
        SimpleSeller simpleSellerValue = new SimpleSeller();
        edu.core.java.auction.domain.concrete.SimpleSeller simpleSellerDomain =
                new edu.core.java.auction.domain.concrete.SimpleSeller(simpleSellerValue.getId());
        simpleSellerRepository.add(simpleSellerValue);

        return simpleSellerDomain;
    }

    public edu.core.java.auction.domain.concrete.SimpleSeller createSimpleSeller(
            String name, String phone, double accountBalance){
        SimpleSeller simpleSellerValue = new SimpleSeller(name, phone, accountBalance);
        edu.core.java.auction.domain.concrete.SimpleSeller simpleSellerDomain =
                new edu.core.java.auction.domain.concrete.SimpleSeller(simpleSellerValue.getId(), name, phone, accountBalance);
        simpleSellerRepository.add(simpleSellerValue);

        return simpleSellerDomain;
    }

    public void updateSimpleSeller(edu.core.java.auction.domain.concrete.SimpleSeller simpleSeller){
        SimpleSeller simpleSellerValue = simpleSellerTranslator.convertToValueObject(simpleSeller);
        simpleSellerRepository.update(simpleSellerValue);
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
    public edu.core.java.auction.domain.concrete.SimpleProduct createSimpleProduct(){
        SimpleProduct simpleProductValue = new SimpleProduct();
        edu.core.java.auction.domain.concrete.SimpleProduct simpleProductDomain =
                new edu.core.java.auction.domain.concrete.SimpleProduct(simpleProductValue.getId());
        simpleProductRepository.add(simpleProductValue);

        return simpleProductDomain;
    }

    public edu.core.java.auction.domain.concrete.SimpleProduct createSimpleProduct(
            String title, String description, int count, Person owner, double sellerPrice){
        SimpleProduct simpleProductValue = new SimpleProduct(title, description, count, owner.getId(), sellerPrice);
        edu.core.java.auction.domain.concrete.SimpleProduct simpleProductDomain =
                new edu.core.java.auction.domain.concrete.SimpleProduct(
                        simpleProductValue.getId(), title, description, count, owner, sellerPrice);
        simpleProductRepository.add(simpleProductValue);

        return simpleProductDomain;
    }

    public void updateSimpleProduct(edu.core.java.auction.domain.concrete.SimpleProduct simpleProduct){
        SimpleProduct simpleProductValue = simpleProductTranslator.convertToValueObject(simpleProduct);
        simpleProductRepository.update(simpleProductValue);
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
}
