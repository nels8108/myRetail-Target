package com.target.myretail.service;

import com.target.myretail.domain.Item;
import com.target.myretail.domain.Product;
import com.target.myretail.domain.ProductDescription;
import com.target.myretail.domain.ProductInformation;
import com.target.myretail.domain.ProductInformationWrapper;
import com.target.myretail.domain.ProductPrice;
import com.target.myretail.repository.ProductRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductServiceTest {

    private static final String DEFAULT_NAME = "The Big Lebowski (Blu-ray)";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PRICE = new BigDecimal(13.99);
    private static final BigDecimal UPDATED_PRICE = new BigDecimal(14.99);

    private static final String DEFAULT_CURRENCYCODE = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCYCODE = "BBBBBBBBBB";

    @Mock
    private ProductRepository productRepository;

    @Mock
    private RestTemplate restTemplate;

    private ProductService productService;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        productService = new ProductService(productRepository, restTemplate);

    }
    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Product createProduct() {
        Product product = new Product();
        product.setName(DEFAULT_NAME);
        product.setId(13860428L);
        ProductPrice productPrice = new ProductPrice();
        productPrice.setValue(DEFAULT_PRICE);
        productPrice.setCurrencyCode(DEFAULT_CURRENCYCODE);
        product.setProductPrice(productPrice);

        return product;
    }


    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findOne() {
        //Setup fake redSky response, we could fill out more, but only really care about the title
        ProductInformation productInformation = new ProductInformation();
        ProductDescription productDescription = new ProductDescription();
        ProductInformationWrapper productInformationWrapper= new ProductInformationWrapper();
        productDescription.setTitle("The Big Lebowski (Blu-ray)");
        Item item = new Item();
        item.setProductDescription(productDescription);
        productInformation.setItem(item);
        productInformationWrapper.setProductInformation(productInformation);
        Optional<ProductInformationWrapper> fakeProductInformationWrapper = Optional.of(productInformationWrapper);
        Optional<Product> fakeProduct = Optional.of(createProduct());
        ResponseEntity<ProductInformationWrapper> responseEntity = ResponseEntity.of(fakeProductInformationWrapper);


        Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.eq(ProductInformationWrapper.class),Mockito.anyString())).thenReturn(responseEntity);
        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(fakeProduct);
        Optional<Product> productInformation1 = this.productService.findOne("13860428");
        assertThat(productInformation1.get().getName()).isEqualTo(DEFAULT_NAME);
        assertThat(productInformation1.get().getProductPrice().getValue()).isEqualTo(DEFAULT_PRICE);
        assertThat(productInformation1.get().getProductPrice().getCurrencyCode()).isEqualTo(DEFAULT_CURRENCYCODE);

    }

    @Test
    public void findOneNoResults(){
        Optional<Product> fakeProduct = Optional.empty();
        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(fakeProduct);
        Optional<Product> returnedProduct = this.productService.findOne("13860428");
        assertThat(returnedProduct.isPresent()).isEqualTo(false);
    }

    @Test
    public void save() {
    }
}