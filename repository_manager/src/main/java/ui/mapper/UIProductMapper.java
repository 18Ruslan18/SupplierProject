package ui.mapper;

import domain.entity.Product;
import domain.mapper.EntityMapper;

import java.util.Vector;

public class UIProductMapper implements EntityMapper<Product, Vector<String>> {

    @Override
    public Vector<String> convert(Product item) {
        Vector<String> vector = new Vector<>();
        vector.add(item.getGoods());
        vector.add(String.valueOf(item.getCount()));
        vector.add(item.getCountPercent());
        return vector;
    }

    @Override
    public Product deconvert(Vector<String> item) {
        return new Product(
                item.get(0),
                Integer.parseInt(item.get(1))
        );
    }
}
