package pl.com.mazniak.tydz2;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "page-info")
@Profile("Plus")
public class ShopPlus extends Shop{

    public int getVatRate() {
        return vatRate;
    }

    public void setVatRate(int vatRate) {
        this.vatRate = vatRate;
    }

    private int vatRate; // tax in %

    @EventListener(ApplicationReadyEvent.class)
    public void start() {
        products = new LinkedList<>();
        populateProducts();
        System.out.println("List of products without discount nor VAT: ");
        printAllProducts();
        applyVAT();
        System.out.println();
        System.out.println("List of products with calculated VAT of " + vatRate + "%:");
        printAllProducts();
        System.out.println("Summed price for products: " + sumPriceOfProducts() + " PLN");
    }

    // multiplies the price by (100+vatRate)/100
    private void applyVAT() {
        for (Product p : products) {
            p.setPrice(p.getPrice().multiply(new BigDecimal(BigInteger.valueOf(vatRate+100),2))
            .setScale(2, BigDecimal.ROUND_HALF_UP));
        }
    }


}
