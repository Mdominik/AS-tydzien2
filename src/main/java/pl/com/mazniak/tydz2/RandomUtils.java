package pl.com.mazniak.tydz2;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

public class RandomUtils {

    //how many products to generate
    static final int noOfRandomProducts = 5;

    //price in 0,01 PLN
    private static final int priceRangeBegin = 5000;
    private static final int priceRangeEnd = 30000;

    public static BigDecimal randomPrice() {

        // random price in range 50.00-300.00 PLN
        int random_price = new Random().nextInt((priceRangeEnd - priceRangeBegin) -1 ) + priceRangeBegin;
        return new BigDecimal(BigInteger.valueOf(random_price), 2);
    }

}
