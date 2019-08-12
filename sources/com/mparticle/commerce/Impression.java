package com.mparticle.commerce;

import java.util.LinkedList;
import java.util.List;

public class Impression {
    private String mListName = null;
    private List<Product> mProducts;

    public Impression(String listName, Product product) {
        this.mListName = listName;
        addProduct(product);
    }

    public String getListName() {
        return this.mListName;
    }

    public List<Product> getProducts() {
        return this.mProducts;
    }

    public Impression addProduct(Product product) {
        if (this.mProducts == null) {
            this.mProducts = new LinkedList();
        }
        if (product != null) {
            this.mProducts.add(product);
        }
        return this;
    }

    public Impression(Impression impression) {
        this.mListName = impression.mListName;
        if (impression.mProducts != null) {
            this.mProducts = impression.mProducts;
        }
    }
}
