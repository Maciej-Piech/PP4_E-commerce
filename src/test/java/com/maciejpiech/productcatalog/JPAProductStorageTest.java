package com.maciejpiech.productcatalog;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JPAProductStorageTest {

    @Autowired
    ProductDataCRUD productDataCRUD;

    @Test
    void storeAndLoadProduct() {
        ProductData data = new ProductData("001", "Black Tea");

        productDataCRUD.save(data);

        ProductData loaded = productDataCRUD
                .findById(data.getId()).get();

        assertEquals(data.getId(), loaded.getId());
    }
}
