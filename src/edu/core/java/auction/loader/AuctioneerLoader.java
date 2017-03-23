package edu.core.java.auction.loader;

import edu.core.java.auction.domain.Auctioneer;
import edu.core.java.auction.domain.Lot;
import edu.core.java.auction.repository.Repository;
import edu.core.java.auction.translator.Translator;
import edu.core.java.auction.vo.AuctioneerValueObject;
import edu.core.java.auction.vo.LotValueObject;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Maxim on 18.03.2017.
 */
public class AuctioneerLoader extends Loader<Auctioneer> {
    private Repository<AuctioneerValueObject> auctioneerRepository;
    private Repository<LotValueObject> lotRepository;
    private Translator<LotValueObject, Lot> lotTranslator;

    public AuctioneerLoader(Repository<AuctioneerValueObject> auctioneerRepository,
                            Repository<LotValueObject> lotRepository,
                            Translator<LotValueObject, Lot> lotTranslator){
        this.auctioneerRepository = auctioneerRepository;
        this.lotRepository = lotRepository;
        this.lotTranslator = lotTranslator;
    }

    @Override
    public Auctioneer getEntity(Long id) {
        AuctioneerValueObject auctioneerValueObject = auctioneerRepository.find(id);
        Auctioneer auctioneerDomain = new Auctioneer(
                auctioneerValueObject.id, auctioneerValueObject.name);

        for (LotValueObject lotValueObject : lotRepository.getAll()){
            if (lotValueObject.auctioneerId.equals(id))
                auctioneerDomain.getLots().add(lotTranslator.convertToDomainObject(lotValueObject));
        }
        return auctioneerDomain;
    }

    @Override
    public HashSet<Auctioneer> getAllEntities() {
        Collection<AuctioneerValueObject> auctioneersValueObject = auctioneerRepository.getAll();
        HashSet<Auctioneer> auctioneersDomain = new HashSet<Auctioneer>();

        for (AuctioneerValueObject auctioneerValueObject : auctioneersValueObject){
            auctioneersDomain.add(getEntity(auctioneerValueObject.id));
        }

        return auctioneersDomain;
    }
}
