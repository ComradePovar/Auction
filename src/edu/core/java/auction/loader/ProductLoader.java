package edu.core.java.auction.loader;

import edu.core.java.auction.domain.Product;
import edu.core.java.auction.repository.ProductRepository;
import edu.core.java.auction.translator.ProductTranslator;

/**
 * Created by Maxim on 02.04.2017.
 */
public class ProductLoader extends Loader<Product> {
    public ProductLoader(ProductRepository productRepository, ProductTranslator productTranslator){
        repository = productRepository;
        translator = productTranslator;
    }
}
