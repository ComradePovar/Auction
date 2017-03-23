package edu.core.java.auction.translator;

import edu.core.java.auction.domain.Product;
import edu.core.java.auction.repository.SellerRepository;
import edu.core.java.auction.vo.ProductValueObject;

/**
 * Created by Max on 09.03.2017.
 */
public class ProductTranslator
        implements Translator<ProductValueObject, Product> {
    private SellerTranslator sellerTranslator;
    private SellerRepository sellerRepository;

    @Override
    public Product convertToDomainObject(ProductValueObject value) {
        Product product = new Product(value.id, value.title, value.description,
                sellerTranslator.convertToDomainObject(sellerRepository.find(value.ownerId)),
                value.sellerPrice);
        return product;
    }

    @Override
    public ProductValueObject convertToValueObject(Product value) {
        ProductValueObject productValueObject = new ProductValueObject();
        productValueObject.id = value.getId();
        productValueObject.title = value.getTitle();
        productValueObject.description = value.getDescription();
        productValueObject.ownerId = value.getOwner().getId();
        productValueObject.sellerPrice = value.getSellerPrice();
        return productValueObject;
    }
}
