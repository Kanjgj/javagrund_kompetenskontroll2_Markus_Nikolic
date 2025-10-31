package org.example;

import java.util.List;
import java.util.Set;

public interface StoreService {
    void addProdukt(Product p);
    List<Product> getAllProducts();
    List<Product> getExpensiveProducts(double minPris);
    List<Product> filterByCategory(String kategori);
    Set<String> getAllCategories();
}
