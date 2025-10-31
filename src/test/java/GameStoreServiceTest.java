import org.example.GameStoreService;
import org.example.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

@ExtendWith(MockitoExtension.class)
public class GameStoreServiceTest {

    @Mock
    List<Product> mockProductList;

    @Test
    void testAddProdukt_usesMockList() {
        GameStoreService store = new GameStoreService(mockProductList, new ArrayList<>(), new HashMap<>(), new HashSet<>());

        Product p = new Product(1, "MockGame", "Test", 100);
        store.addProdukt(p);

        verify(mockProductList).add(p);
    }

    @Test
    void testExpensiveProductsFilter() {
        GameStoreService store = new GameStoreService();
        store.addProdukt(new Product(1, "PS5", "Konsol", 5999));
        store.addProdukt(new Product(2, "Controller", "Tillbehör", 799));

        List<Product> expensive = store.getExpensiveProducts(1000);
        assertEquals(1, expensive.size());
        assertEquals("PS5", expensive.get(0).getNamn());
    }
}
