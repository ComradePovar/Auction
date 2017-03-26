package edu.core.java.auction.translator;

import edu.core.java.auction.domain.Product;
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

    public SellerTranslator(){ }

    public SellerTranslator(ProductTranslator productTranslator, ProductRepository productRepository){
        this.productRepository = productRepository;
        this.productTranslator = productTranslator;
    }

    @Override
    public Seller convertToDomainObject(SellerValueObject value) {
        Seller seller = new Seller(value.id, value.name, value.accountBalance, value.comissionPercentage);

        for (ProductValueObject productValueObject : productRepository.getAll()){
            if (productValueObject.ownerId.equals(value.id)){
                Product product = new Product(productValueObject.id, productValueObject.title, productValueObject.description,
                        seller);
                seller.getProducts().add(product);
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

    public void setProductTranslator(ProductTranslator productTranslator) {
        this.productTranslator = productTranslator;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
