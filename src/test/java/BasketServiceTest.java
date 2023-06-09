

import com.ihsan.itgecommerce.entity.Basket;
import com.ihsan.itgecommerce.entity.Product;
import com.ihsan.itgecommerce.entity.UserEntity;
import com.ihsan.itgecommerce.entity.enums.ProductState;
import com.ihsan.itgecommerce.repository.IBasketRepository;
import com.ihsan.itgecommerce.repository.IProductRepository;
import com.ihsan.itgecommerce.repository.IUserRepository;
import com.ihsan.itgecommerce.service.BasketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class BasketServiceTest {

    @Mock
    private IBasketRepository basketRepository;

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IProductRepository productRepository;

    @InjectMocks
    private BasketService basketService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBasket() {
        UserEntity user = new UserEntity();
        user.setId(1L);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(basketRepository.save(any(Basket.class))).thenReturn(null);

        assertTrue(basketService.createBasket(1L));

        verify(basketRepository, times(1)).save(any(Basket.class));
    }

    @Test
    public void testAddProductToBasket() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setBasket(new Basket());

        Product product = new Product();
        product.setId(1L);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
        when(userRepository.save(any(UserEntity.class))).thenReturn(null);

        assertTrue(basketService.addProductToBasket(1L, 1L));

        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    public void testListProducts() {
        Basket basket = new Basket();
        basket.setId(1L);

        when(basketRepository.findById(anyLong())).thenReturn(Optional.of(basket));

        List<Product> products = basketService.listProducts(1L);

        assertNotNull(products);
        assertEquals(0, products.size());

        verify(basketRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testRemoveProductFromBasket() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setBasket(new Basket());

        Product product = new Product();
        product.setId(1L);

        user.getBasket().getProducts().add(product);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
        when(userRepository.save(any(UserEntity.class))).thenReturn(null);

        assertTrue(basketService.removeProductFromBasket(1L, 1L));

        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    public void testPurchaseProducts() {
        Basket basket = new Basket();
        basket.setId(1L);

        Product product1 = new Product();
        product1.setId(1L);

        Product product2 = new Product();
        product2.setId(2L);

        basket.getProducts().add(product1);
        basket.getProducts().add(product2);

        when(basketRepository.findById(anyLong())).thenReturn(Optional.of(basket));
        when(basketRepository.save(any(Basket.class))).thenReturn(null);

        assertTrue(basketService.purchaseProducts(1L));

        verify(basketRepository, times(1)).save(any(Basket.class));
        assertEquals(ProductState.SOLD, product1.getProductState());
        assertEquals(ProductState.SOLD, product2.getProductState());
        assertTrue(basket.getProducts().isEmpty());
        assertEquals(2, basket.getUser().getProducts().size());
    }

    @Test
    public void testListProductsFromUserBasket() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setBasket(new Basket());

        Product product1 = new Product();
        product1.setId(1L);

        Product product2 = new Product();
        product2.setId(2L);

        user.getBasket().getProducts().add(product1);
        user.getBasket().getProducts().add(product2);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        List<Product> products = basketService.listProductsFromUserBasket(1L);

        assertNotNull(products);
        assertEquals(2, products.size());

        verify(userRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testPurchaseProductsInUserBasket() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setBasket(new Basket());
        user.getBasket().setId(1L);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(basketRepository.findById(anyLong())).thenReturn(Optional.of(user.getBasket()));
        when(basketRepository.save(any(Basket.class))).thenReturn(null);

        assertTrue(basketService.purchaseProductsInUserBasket(1L));

        verify(basketRepository, times(1)).save(any(Basket.class));
    }
}
