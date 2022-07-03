package com.maciejpiech.productcatalog;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductCatalogTest {

    @Test
    void itAllowToListOnlyPublishedProducts() {
        ProductCatalog catalog = thereIsProductCatalog();
        List<ProductData> products = catalog.allPublishedProducts();
        assertEquals(0, products.size());
    }

    @Test
    void itAllowsToAddProductDraft() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("001", "Black Tea");

        List<ProductData> products = catalog.allPublishedProducts();
        assertEquals(0, products.size());
    }

    @Test
    void itDenyToPublishProductWithoutPrice() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("001", "Black Tea");

        assertThrows(CantPublishProductException.class, () -> {
            catalog.publish(productId);
        });
    }

    @Test
    void itAllowsToAssignPrice() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("001", "Black Tea");
        catalog.assignPrice(productId, BigDecimal.valueOf(10.10));

        ProductData loaded = catalog.getDetails(productId);
        assertEquals(BigDecimal.valueOf(10.10), loaded.getPrice());
    }

    @Test
    void itDenyToPublishProductWithoutImage() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("001", "Black Tea");
        catalog.assignPrice(productId, BigDecimal.valueOf(10.10));

        assertThrows(CantPublishProductException.class, () -> {
            catalog.publish(productId);
        });
    }

    @Test
    void itAllowsToListPublishedProduct() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("001", "Black Tea");
        catalog.assignPrice(productId, BigDecimal.valueOf(10.10));
        catalog.assignImage(productId, "https://as2.ftcdn.net/v2/jpg/03/21/99/89/1000_F_321998935_OEmaxE5Opa7cESmIClPtsFYeawPtzzvE.jpg");


        catalog.publish(productId);

        List<ProductData> products = catalog.allPublishedProducts();
        assertEquals(1, products.size());
    }


    private ProductCatalog thereIsProductCatalog() {
        return new ProductCatalog(new MapProductStorage());
    }
}
