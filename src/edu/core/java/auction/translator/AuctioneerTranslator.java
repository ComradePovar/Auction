package edu.core.java.auction.translator;

import edu.core.java.auction.domain.Auctioneer;
import edu.core.java.auction.domain.Lot;
import edu.core.java.auction.loader.Loader;
import edu.core.java.auction.repository.Repository;
import edu.core.java.auction.translator.Translator;
import edu.core.java.auction.vo.AuctioneerValueObject;
import edu.core.java.auction.vo.LotValueObject;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Maxim on 18.03.2017.
 */
public class AuctioneerTranslator implements Translator<AuctioneerValueObject, Auctioneer> {
    private Repository<LotValueObject> lotRepository;
    private Loader<Lot> lotLoader;

    public AuctioneerTranslator(){}

    public AuctioneerTranslator(Repository<LotValueObject> lotRepository, Loader<Lot> lotLoader){
        this.lotRepository = lotRepository;
        this.lotLoader = lotLoader;
    }

    @Override
    public Auctioneer convertToDomainObject(AuctioneerValueObject value) {
        Auctioneer auctioneer = new Auctioneer(value.id, value.name);

        for (LotValueObject lotValueObject : lotRepository.getAll()){
            if (lotValueObject.auctioneerId.equals(value.id)) {
                auctioneer.getLots().add(lotLoader.getEntity(lotValueObject.id));
            }
        }

        return auctioneer;
    }

    @Override
    public AuctioneerValueObject convertToValueObject(Auctioneer value) {
        AuctioneerValueObject auctioneerValueObject = new AuctioneerValueObject();
        auctioneerValueObject.id = value.getId();
        auctioneerValueObject.name = value.getName();
        return auctioneerValueObject;
    }

    public void setLotRepository(Repository<LotValueObject> lotRepository) {
        this.lotRepository = lotRepository;
    }

    public void setLotLoader(Loader<Lot> lotLoader) {
        this.lotLoader = lotLoader;
    }
}
