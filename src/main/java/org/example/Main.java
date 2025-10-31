package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        GameStoreService store = new GameStoreService();

        // Produkterna läggs till i butiken
        store.addProdukt(new Product(1, "PlayStation 5", "Konsol", 5999));
        store.addProdukt(new Product(2, "DualSense Controller", "Tillbehör", 799));
        store.addProdukt(new Product(3, "Ghost of Yōtei", "Spel", 699));
        store.addProdukt(new Product(4, "Xbox Series X", "Konsol", 5799));
        store.addProdukt(new Product(5, "Xbox Series S", "Konsol", 3799));
        store.addProdukt(new Product(6, "Halo Infinite", "Spel", 599));
        store.addProdukt(new Product(7, "Nintendo Switch 2", "Konsol", 4999));
        store.addProdukt(new Product(8, "Donkey Kong Bananza", "Spel", 549));

        // Här skapas beställningarna
        try {
            store.addOrder(new Order(1, "Alice", Arrays.asList(
                    store.getAllProducts().get(0),
                    store.getAllProducts().get(2),
                    store.getAllProducts().get(1)
            )));

            store.addOrder(new Order(2, "Bob", Arrays.asList(
                    store.getAllProducts().get(3),
                    store.getAllProducts().get(5)
            )));

            store.addOrder(new Order(3, "Clara", Arrays.asList(
                    store.getAllProducts().get(7),
                    store.getAllProducts().get(6)
            )));
        } catch (EmptyOrderException e) {
            log.error(e.getMessage());
        }

        System.out.println("\nAlla spel (kategori: Spel, sorterade efter pris):");
        store.getProduktKategori("Spel").forEach(System.out::println);

        try {
            System.out.println("\nTotalt spenderat av Alice: " + store.getTotaltSlösat("Alice") + " kr");
            System.out.println("Totalt spenderat av Bob: " + store.getTotaltSlösat("Bob") + " kr");
            System.out.println("Totalt spenderat av Clara: " + store.getTotaltSlösat("Clara") + " kr");
        } catch (ProductNotFoundException e) {
            log.error(e.getMessage());
        }

        System.out.println("\nTopp 3 mest köpta produkter:");
        store.getToppTre().forEach(System.out::println);

        System.out.println("\nAlla unika produktkategorier i butiken:");
        store.getAllCategories().forEach(System.out::println);
    }
}
