package com.maciejpiech.productcatalog;

import org.springframework.data.repository.CrudRepository;

public interface ProductDataCRUD
        extends CrudRepository<ProductData, String> {
}
