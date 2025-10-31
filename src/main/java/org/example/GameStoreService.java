package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class GameStoreService implements StoreService {

    private static final Logger logger = LoggerFactory.getLogger(GameStoreService.class);

    private List<Product> allProducts;
    private List<Order> allOrders;
    private Map<String, List<Order>> customerHistory;
    private Set<String> productCategories;

    public GameStoreService() {
        this(new ArrayList<>(), new ArrayList<>(), new HashMap<>(), new HashSet<>());
    }

    public GameStoreService(List<Product> allProducts, List<Order> allOrders,
                            Map<String, List<Order>> customerHistory, Set<String> productCategories) {
        this.allProducts = allProducts;
        this.allOrders = allOrders;
        this.customerHistory = customerHistory;
        this.productCategories = productCategories;
    }

    @Override
    public void addProdukt(Product product) {
        allProducts.add(product);
        productCategories.add(product.getKategori());
        logger.info("Produkt tillagd: {}", product.getNamn());
    }

    public void addOrder(Order order) throws EmptyOrderException {
        if (order.getProducts().isEmpty()) {
            throw new EmptyOrderException("Ordern får inte vara tom!");
        }

        allOrders.add(order);
        customerHistory.computeIfAbsent(order.getCustomerName(), k -> new ArrayList<>()).add(order);
        logger.info("Order tillagd: {}", order);
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(allProducts);
    }

    @Override
    public List<Product> getExpensiveProducts(double minPris) {
        logger.info("Filtrerar produkter över {} kr", minPris);
        return allProducts.stream()
                .filter(p -> p.getPris() > minPris)
                .sorted(Comparator.comparingDouble(Product::getPris).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> filterByCategory(String category) {
        logger.info("Filtrerar produkter i kategori '{}'", category);
        return allProducts.stream()
                .filter(p -> p.getKategori().equalsIgnoreCase(category))
                .sorted(Comparator.comparing(Product::getNamn))
                .collect(Collectors.toList());
    }

    @Override
    public Set<String> getAllCategories() {
        return productCategories;
    }

    public double getTotaltSlösat(String name) throws ProductNotFoundException {
        List<Order> orders = customerHistory.get(name);
        if (orders == null || orders.isEmpty()) {
            throw new ProductNotFoundException("Ingen order hittades för kund: " + name);
        }

        return orders.stream()
                .flatMap(order -> order.getProducts().stream())
                .mapToDouble(Product::getPris)
                .sum();
    }

    public List<String> getToppTre() {
        return allOrders.stream()
                .flatMap(order -> order.getProducts().stream())
                .collect(Collectors.groupingBy(Product::getNamn, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public List<Product> getProduktKategori(String category) {
        return allProducts.stream()
                .filter(p -> p.getKategori().equalsIgnoreCase(category))
                .sorted(Comparator.comparingDouble(Product::getPris))
                .collect(Collectors.toList());
    }
}
