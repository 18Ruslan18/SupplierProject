package domain.interactor.product;

import domain.entity.Product;
import domain.interactor.BaseUseCase;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FindProducts extends BaseUseCase<List<Product>, String> {

    private final GetProducts getProducts;

    public FindProducts(GetProducts getProducts) {
        super(Logger.getLogger(FindProducts.class.getName()));
        this.getProducts = getProducts;
    }

    @Override
    public List<Product> run(String params) {
        List<Product> result = new ArrayList<>();
        getProducts.run().forEach(product -> {
            if (product.getGoods().contains(params)) {
                result.add(product);
            }
        });
        return result;
    }
}
