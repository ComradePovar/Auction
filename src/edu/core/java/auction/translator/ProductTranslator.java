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

    public ProductTranslator() {}
    public ProductTranslator(SellerTranslator sellerTranslator, SellerRepository sellerRepository){
        this.sellerRepository = sellerRepository;
        this.sellerTranslator = sellerTranslator;
    }

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
        if (value.getOwner() != null)
            productValueObject.ownerId = value.getOwner().getId();
        else
            productValueObject.ownerId = (long)0;
        productValueObject.sellerPrice = value.getSellerPrice();
        return productValueObject;
    }

    public void setSellerTranslator(SellerTranslator sellerTranslator) {
        this.sellerTranslator = sellerTranslator;
    }

    public void setSellerRepository(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }
}
