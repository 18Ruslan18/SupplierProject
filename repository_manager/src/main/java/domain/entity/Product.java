package domain.entity;

import java.util.Objects;

public class Product {

    private String goods;
    private int count;

    public Product(String goods, int count) {
        this.goods = goods;
        this.count = count;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public int getCount() {
        return count;
    }

    public String getCountPercent() {
        double per = count;
        return String.format("%.3f",per*100/135);
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return count == product.count &&
                Objects.equals(goods, product.goods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goods, count);
    }

    @Override
    public String toString() {
        return "Product{" +
                "goods='" + goods + '\'' +
                ", count=" + count + '\'' +
                ", count=" + count + '\'' +
                '}';
    }
}
