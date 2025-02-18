import com.lanchonete.product.core.entities.Product;
import com.lanchonete.product.core.entities.ProductCategory;
import com.lanchonete.product.core.exceptions.ProductCategoryNotFoundException;
import com.lanchonete.product.repository.ProductAdapter;
import com.lanchonete.product.repository.ProductCategoryRepository;
import com.lanchonete.product.repository.ProductRepository;
import com.lanchonete.product.repository.entities.ProductCategoryEntity;
import com.lanchonete.product.repository.entities.ProductEntity;
import com.lanchonete.product.utils.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductAdapterTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductCategoryRepository productCategoryRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductAdapter productAdapter;

    private UUID productId;
    private UUID categoryId;
    private Product product;
    private ProductEntity productEntity;
    private ProductCategoryEntity productCategoryEntity;

    @BeforeEach
    void setUp() {
        productId = UUID.randomUUID();
        categoryId = UUID.randomUUID();
        product = new Product(productId, "Bebida", "Refrigerante", null);
        productEntity = new ProductEntity();
        productEntity.setProductId(productId);
        productCategoryEntity = new ProductCategoryEntity();
        productCategoryEntity.setDescription("Bebida");
    }

    @Test
    void testRemoveProduct_WhenProductExists_ShouldRemoveSuccessfully() {
        when(productRepository.findById(productId)).thenReturn(Optional.of(productEntity));
        doNothing().when(productRepository).deleteByProductId(productId);

        productAdapter.removeProduct(productId);

        verify(productRepository, times(1)).deleteByProductId(productId);
    }

    @Test
    void testRemoveProduct_WhenProductDoesNotExist_ShouldNotRemove() {
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        productAdapter.removeProduct(productId);

        verify(productRepository, never()).deleteByProductId(productId);
    }

    @Test
    void testFindById_WhenProductExists_ShouldReturnTrue() {
        when(productRepository.findById(productId)).thenReturn(Optional.of(productEntity));

        assertTrue(productAdapter.findById(productId));
    }

    @Test
    void testFindById_WhenProductDoesNotExist_ShouldReturnFalse() {
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertFalse(productAdapter.findById(productId));
    }

    /*@Test
    void testSaveOrUpdate_WhenValidProduct_ShouldSaveSuccessfully() {
        when(productMapper.toEntity(product)).thenReturn(productEntity);
        when(productCategoryRepository.findByDescription("Bebida"))
                .thenReturn(Optional.of(productCategoryEntity));
        when(productRepository.save(productEntity)).thenReturn(productEntity);
        when(productMapper.toProduct(productEntity)).thenReturn(product);

        Product savedProduct = productAdapter.saveOrUpdate(product);

        assertNotNull(savedProduct);
        verify(productRepository, times(1)).save(productEntity);
    }

    @Test
    void testSaveOrUpdate_WhenCategoryNotFound_ShouldThrowException() {
        when(productMapper.toEntity(product)).thenReturn(productEntity);
        when(productCategoryRepository.findByDescription("Bebida"))
                .thenReturn(Optional.empty());

        assertThrows(ProductCategoryNotFoundException.class, () -> productAdapter.saveOrUpdate(product));
    }*/
}