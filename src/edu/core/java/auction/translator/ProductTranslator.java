package edu.core.java.auction.translator;

import edu.core.java.auction.AuctionService;
import edu.core.java.auction.domain.Product;
import edu.core.java.auction.loader.SellerLoader;
import edu.core.java.auction.vo.ProductValueObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Max on 09.03.2017.
 */
public class ProductTranslator implements Translator<ProductValueObject, Product> {
    protected Logger logger = LoggerFactory.getLogger(ProductTranslator.class);

    @Override
    public Product convertToDomainObject(ProductValueObject value) {
        if (value == null){
            logger.warn("Value object is null. Translation is not possible.");
            return null;
        }

        logger.info("Conversion was successful.");
        SellerLoader sellerLoader = AuctionService.getInstance().getSellerLoader();
        return new Product(value.id, value.title, value.description, sellerLoader.getEntity(value.ownerId));
    }

    @Override
    public ProductValueObject convertToValueObject(Product domain) {
        if (domain == null){
            logger.warn("Domain object is null. Translation is not possible.");
            return null;
        }

        logger.info("Conversion was successful.");
        return new ProductValueObject(domain.getId(), domain.getTitle(),
                                      domain.getDescription(), domain.getOwner().getId());
    }
}
