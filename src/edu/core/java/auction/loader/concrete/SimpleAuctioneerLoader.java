package edu.core.java.auction.loader.concrete;

import edu.core.java.auction.domain.concrete.SimpleAuctioneer;
import edu.core.java.auction.domain.interfaces.Auctioneer;
import edu.core.java.auction.loader.interfaces.Loader;
import edu.core.java.auction.repository.interfaces.Repository;
import edu.core.java.auction.translator.interfaces.Translator;
import edu.core.java.auction.vo.Lot;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Maxim on 18.03.2017.
 */
public class SimpleAuctioneerLoader extends Loader<SimpleAuctioneer> {
    private Repository<edu.core.java.auction.vo.SimpleAuctioneer> simpleAuctioneerRepository;
    private Repository<Lot> lotRepository;
    private Translator<Lot, edu.core.java.auction.domain.concrete.Lot> lotTranslator;

    public SimpleAuctioneerLoader(Repository<edu.core.java.auction.vo.SimpleAuctioneer> simpleAuctioneerRepository,
                                  Repository<Lot> lotRepository,
                                  Translator<Lot, edu.core.java.auction.domain.concrete.Lot> lotTranslator){
        this.simpleAuctioneerRepository = simpleAuctioneerRepository;
        this.lotRepository = lotRepository;
        this.lotTranslator = lotTranslator;
    }

    @Override
    public SimpleAuctioneer getEntity(Long id) {
        edu.core.java.auction.vo.SimpleAuctioneer simpleAuctioneerValue = simpleAuctioneerRepository.find(id);
        SimpleAuctioneer simpleAuctioneerDomain = new SimpleAuctioneer(
                simpleAuctioneerValue.getId(), simpleAuctioneerValue.name);

        for (Lot lotValue : lotRepository.getAll()){
            if (lotValue.auctioneerId.equals(id))
                simpleAuctioneerDomain.getLots().add(lotTranslator.convertToDomainObject(lotValue));
        }
        return simpleAuctioneerDomain;
    }

    @Override
    public HashSet<SimpleAuctioneer> getAllEntities() {
        Collection<edu.core.java.auction.vo.SimpleAuctioneer> simpleAuctioneersValue = simpleAuctioneerRepository.getAll();
        HashSet<SimpleAuctioneer> simpleAuctioneersDomain = new HashSet<SimpleAuctioneer>();

        for (edu.core.java.auction.vo.SimpleAuctioneer simpleAuctioneerValue : simpleAuctioneersValue){
            simpleAuctioneersDomain.add(getEntity(simpleAuctioneerValue.getId()));
        }

        return simpleAuctioneersDomain;
    }
}
