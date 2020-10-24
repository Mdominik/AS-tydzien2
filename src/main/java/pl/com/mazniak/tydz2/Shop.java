package pl.com.mazniak.tydz2;

import java.math.BigDecimal;
import java.util.List;

public class Shop {

    List<Product> products;

    protected void populateProducts() {
        //add random 5 numbers
        for(int i = 0; i < RandomUtils.noOfRandomProducts; i++) {
            products.add(
                    new Product( "Product" + i, RandomUtils.randomPrice()));// random price as BigDecimal
        }
    }

    protected void printAllProducts() {
        for(Product p : products) {
            System.out.println(p);
        }
    }

    protected BigDecimal sumPriceOfProducts() {
        BigDecimal sum = new BigDecimal(0.00);
        for (Product p : products) {
            sum = sum.add(p.getPrice());
        }
        return sum;
    }

}
