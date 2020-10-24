package pl.com.mazniak.tydz2;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "page-info")
@Profile("Start")
public class ShopStart extends Shop {

    @EventListener(ApplicationReadyEvent.class)
    public void start() {

        products = new LinkedList<>();
        populateProducts();
        System.out.println("List of products: ");
        printAllProducts();
        System.out.println("Summed price for products: " + sumPriceOfProducts() + " PLN");
    }


}
