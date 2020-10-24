package pl.com.mazniak.tydz2;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.sound.midi.SysexMessage;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.LinkedList;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "page-info")
@Profile("Pro")
public class ShopPro extends Shop{
    public int getVatRate() {
        return vatRate;
    }

    public void setVatRate(int vatRate) {
        this.vatRate = vatRate;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    private int vatRate;  // tax in %
    private int discount; // discount in %

    @EventListener(ApplicationReadyEvent.class)
    public void start() {
        products = new LinkedList<>();
        populateProducts();
        System.out.println("List of products without discounts nor VAT: ");
        printAllProducts();
        applyVATAndDiscount();
        System.out.println();
        System.out.println("List of products with discount of " + discount + "% and calculated VAT of " + vatRate  + "%: ");
        printAllProducts();

        System.out.println("Summed price for products: " + sumPriceOfProducts() + " PLN");
    }

    // adds VAT and subtracts discount
    private void applyVATAndDiscount() {
        for (Product p : products) {
            p.setPrice(p.getPrice().multiply(
                    new BigDecimal(BigInteger.valueOf(vatRate-discount+100),2))
                    .setScale(2, BigDecimal.ROUND_HALF_UP));
        }
    }

}
