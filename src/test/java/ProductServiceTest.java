import com.ihsan.itgecommerce.entity.Product;
import com.ihsan.itgecommerce.entity.enums.ProductState;
import com.ihsan.itgecommerce.repository.IProductRepository;
import com.ihsan.itgecommerce.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private IProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveProduct() {
        Product product = new Product();
        product.setTitle("Test Product");

        when(productRepository.save(any(Product.class))).thenReturn(null);

        assertTrue(productService.saveProduct(product));

        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void testFindAllProducts() {
        Product product1 = new Product();
        product1.setTitle("Product 1");
        product1.setProductState(ProductState.AVAILABLE);

        Product product2 = new Product();
        product2.setTitle("Product 2");
        product2.setProductState(ProductState.AVAILABLE);

        when(productRepository.findAllByProductState(ProductState.AVAILABLE)).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.findAllProducts();

        assertEquals(2, products.size());
        assertEquals("Product 1", products.get(0).getTitle());
        assertEquals("Product 2", products.get(1).getTitle());

        verify(productRepository, times(1)).findAllByProductState(ProductState.AVAILABLE);
    }
}
