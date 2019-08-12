package com.mparticle.commerce;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ProductBag {
    String bagName;
    List<Product> products = new LinkedList();

    private ProductBag() {
    }

    ProductBag(String bagName2) {
        this.bagName = bagName2;
    }

    public String getName() {
        return this.bagName;
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(this.products);
    }
}
