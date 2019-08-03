package com.target.myretail.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.myretail.MyRetailApplication;
import com.target.myretail.domain.Product;
import com.target.myretail.domain.ProductPrice;
import com.target.myretail.repository.ProductRepository;
import com.target.myretail.service.ProductService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for the ProductResource REST controller.
 *
 * @see ProductResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyRetailApplication.class)
public class ProductResourceTest {

    private static final String DEFAULT_NAME = "The Big Lebowski (Blu-ray)";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PRICE = new BigDecimal(13.99);
    private static final BigDecimal UPDATED_PRICE = new BigDecimal(14.99);

    private static final String DEFAULT_CURRENCYCODE = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCYCODE = "BBBBBBBBBB";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restProductMockMvc;

    private Product product;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        final ProductResource productResource = new ProductResource(productService);
        this.restProductMockMvc = MockMvcBuilders.standaloneSetup(productResource)
                .setMessageConverters(jacksonMessageConverter).build();
        product = createProduct();
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

    @Test
    public void getProduct() throws Exception {
        //Initialize the DB
        productRepository.save(product);

        //Get the product
        restProductMockMvc.perform(get("/api/product/{id}", product.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id" ).value(product.getId().intValue()))
                .andExpect(jsonPath("$.name" ).value(DEFAULT_NAME))
                .andExpect(jsonPath("$.current_price.currency_code").value(DEFAULT_CURRENCYCODE))
                .andExpect(jsonPath("$.current_price.value").value(DEFAULT_PRICE.intValue()));
    }

    @Test
    public void saveProductPrice() throws Exception{
        // Initialize the database
        productRepository.save(product);

        int databaseSizeBeforeUpdate = productRepository.findAll().size();
        // Update the artPiece
        Product updatedProduct = productRepository.findById(product.getId()).get();
        updatedProduct.setName(UPDATED_NAME);
        ProductPrice productPrice = new ProductPrice();
        productPrice.setCurrencyCode(UPDATED_CURRENCYCODE);
        productPrice.setValue(UPDATED_PRICE);
        updatedProduct.setProductPrice(productPrice);

        ObjectMapper objectMapper = new ObjectMapper();


        restProductMockMvc.perform(put("/api/product/{}", product.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsBytes(updatedProduct)))
                .andExpect(status().isCreated());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
        Product testProduct = productRepository.findById(product.getId()).get();
        assertThat(testProduct.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testProduct.getProductPrice().getCurrencyCode()).isEqualTo(UPDATED_CURRENCYCODE);
        assertThat(testProduct.getProductPrice().getValue()).isEqualTo(UPDATED_PRICE);

    }

    @After
    public void tearDown() throws Exception {
        productRepository.delete(product);
    }
}