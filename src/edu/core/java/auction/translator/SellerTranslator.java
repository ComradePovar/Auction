package edu.core.java.auction.translator;

import edu.core.java.auction.domain.Seller;
import edu.core.java.auction.repository.ProductRepository;
import edu.core.java.auction.vo.ProductValueObject;
import edu.core.java.auction.vo.SellerValueObject;

/**
 * Created by Max on 09.03.2017.
 */
public class SellerTranslator
        implements Translator<SellerValueObject, Seller> {
    private ProductTranslator productTranslator;
    private ProductRepository productRepository;

    public SellerTranslator(ProductTranslator productTranslator, ProductRepository productRepository){
        this.productRepository = productRepository;
        this.productTranslator = productTranslator;
    }

    @Override
    public Seller convertToDomainObject(SellerValueObject value) {
        Seller seller = new Seller(value.id, value.name, value.accountBalance, value.comissionPercentage);

        for (ProductValueObject productValueObject : productRepository.getAll()){
            if (productValueObject.ownerId == value.id){
                seller.getProducts().add(productTranslator.convertToDomainObject(productValueObject));
            }
        }

        return seller;
    }

    @Override
    public SellerValueObject convertToValueObject(Seller value) {
        SellerValueObject sellerValueObject = new SellerValueObject();
        sellerValueObject.id = value.getId();
        sellerValueObject.name = value.getName();
        sellerValueObject.accountBalance = value.getAccountBalance();
        sellerValueObject.comissionPercentage = value.getComissionPercentage();
        return sellerValueObject;
    }
}
