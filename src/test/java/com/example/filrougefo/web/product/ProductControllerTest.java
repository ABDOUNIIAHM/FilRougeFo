package com.example.filrougefo.web.product;

import com.example.filrougefo.entity.*;
import com.example.filrougefo.security.ClientAuthDetail;
import com.example.filrougefo.security.ClientDetailServiceImpl;
import com.example.filrougefo.security.WebSecurityConfig;
import com.example.filrougefo.service.category.IntCategoryService;
import com.example.filrougefo.service.month.IntMonthService;
import com.example.filrougefo.service.order.OrderService;
import com.example.filrougefo.service.product.IntProductService;
import com.example.filrougefo.web.category.CategoryDto;
import com.example.filrougefo.web.category.CategoryMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.contains;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Import({ProductConfig.class, WebSecurityConfig.class})
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IntCategoryService categoryService;

    @MockBean
    private IntMonthService monthService;

    @MockBean
    private OrderService orderService;
    @MockBean
    private IntProductService productService;

    @MockBean
    private ClientDetailServiceImpl clientDetailService;

    @MockBean
    private ClientAuthDetail clientAuthDetail;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private MonthMapper monthMapper;
    @Autowired
    private ProductMapper productMapper;

    @WithMockUser
    @Test
    void ShouldReturnAllCategoryProductsWhenAuthenticated() throws Exception {
        //given
        Product p1 = new Product(); p1.setId(1);
        Product p2 = new Product(); p2.setId(2);

        Category c1 = new Category(); c1.setId(1); c1.getProducts().add(p1); c1.getProducts().add(p2);  c1.setName("category1");
        Category c2 = new Category(); c2.setId(2);  c2.setName("category2");

        List<Category> categories = List.of(c1,c2);

        Order order = new Order(); order.getOrderLines().add(new OrderLine());

        //when
        when(categoryService.findAll()).thenReturn(categories);
        when(categoryService.findById(ArgumentMatchers.any(int.class))).thenReturn(c1);
        when(clientAuthDetail.getClient()).thenReturn(new Client());
        when(orderService.hasPendingOrder(any(Client.class))).thenReturn(order);

        //then
        List<CategoryDto> expected1 = mapCategoryListToDto();
        List<ProductDTO> expected2 = mapCategoryProductsToDto(1);

        mockMvc.perform(MockMvcRequestBuilders.get("/products/categories/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("product/product-list"))
                .andExpect(MockMvcResultMatchers.model().attribute("categoryList",contains(expected1.toArray())))
                .andExpect(MockMvcResultMatchers.model().attribute("productList",contains(expected2.toArray())));
    }

    @Test
    void ShouldReturnAllCategoryProductsWhenNotAuthenticated() throws Exception {
        //given
        Product p1 = new Product(); p1.setId(1);
        Product p2 = new Product(); p2.setId(2);

        Category c1 = new Category(); c1.setId(1); c1.getProducts().add(p1); c1.getProducts().add(p2);  c1.setName("category1");
        Category c2 = new Category(); c2.setId(2);  c2.setName("category2");

        List<Category> categories = List.of(c1,c2);

        Order order = new Order(); order.getOrderLines().add(new OrderLine());

        //when
        when(categoryService.findAll()).thenReturn(categories);
        when(categoryService.findById(ArgumentMatchers.any(int.class))).thenReturn(c1);
        when(clientAuthDetail.getClient()).thenReturn(new Client());
        when(orderService.hasPendingOrder(any(Client.class))).thenReturn(order);

        //then
        List<CategoryDto> expected1 = mapCategoryListToDto();
        List<ProductDTO> expected2 = mapCategoryProductsToDto(1);

        mockMvc.perform(MockMvcRequestBuilders.get("/products/categories/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("product/product-list"))
                .andExpect(MockMvcResultMatchers.model().attribute("categoryList",contains(expected1.toArray())))
                .andExpect(MockMvcResultMatchers.model().attribute("productList",contains(expected2.toArray())));
    }

    @Test
    void ShouldReturnAllProducts() throws Exception {
        //given
        Product p1 = new Product(); p1.setId(1);
        Product p2 = new Product(); p2.setId(2);
        List<Product> products = List.of(p1,p2);

        Category c1 = new Category(); c1.setId(1); c1.getProducts().add(p1); c1.getProducts().add(p2);  c1.setName("category1");
        Category c2 = new Category(); c2.setId(2);  c2.setName("category2");
        List<Category> categories = List.of(c1,c2);

        Month month = new Month();
        List<Month> monthList = List.of(month);
        Order order = new Order(); order.getOrderLines().add(new OrderLine());

        //when
        when(categoryService.findAll()).thenReturn(categories);
        when(productService.findAll()).thenReturn(products);
        when(monthService.findAll()).thenReturn(monthList);
        when(categoryService.findById(ArgumentMatchers.any(int.class))).thenReturn(c1);
        when(clientAuthDetail.getClient()).thenReturn(new Client());
        when(orderService.hasPendingOrder(any(Client.class))).thenReturn(order);

        //then
        List<CategoryDto> expected1 = mapCategoryListToDto();
        List<ProductDTO> expected2 = mapCategoryProductsToDto(1);
        List<MonthDTO> expected3 = monthList
                .stream()
                .map(m -> monthMapper.toDTO(m))
                .toList();

        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("product/product-list"))
                .andExpect(MockMvcResultMatchers.model().attribute("categoryList",contains(expected1.toArray())))
                .andExpect(MockMvcResultMatchers.model().attribute("productList",contains(expected2.toArray())))
                .andExpect(MockMvcResultMatchers.model().attribute("monthList",contains(expected3.toArray())));
    }
    @Test
    void ShouldReturnProductDetails() throws Exception {
        //given
        Product p1 = new Product(); p1.setId(1);p1.setName("");p1.setCategory(new Category());p1.setVat(BigDecimal.ONE);

        Month month = new Month();
        List<Month> monthList = List.of(month);
        Order order = new Order(); order.getOrderLines().add(new OrderLine());

        //when
        when(productService.findById(any(int.class))).thenReturn(p1);
        when(monthService.findAll()).thenReturn(monthList);
        when(clientAuthDetail.getClient()).thenReturn(new Client());
        when(orderService.hasPendingOrder(any(Client.class))).thenReturn(order);

        //then
        ProductDTO expected = productMapper.toDTO(p1);
        List<MonthDTO> expected3 = monthList
                .stream()
                .map(m -> monthMapper.toDTO(m))
                .toList();

        mockMvc.perform(MockMvcRequestBuilders.get("/products/details/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("product/product-detail"))
                .andExpect(MockMvcResultMatchers.model().attribute("product",expected))
                .andExpect(MockMvcResultMatchers.model().attribute("monthList",contains(expected3.toArray())));
    }

    @Test
    void ShouldReturnProductOfGivenMonth() throws Exception {
        //given
        Product p1 = new Product(); p1.setId(1);p1.setName("");p1.setCategory(new Category());p1.setVat(BigDecimal.ONE);
        List<Product> products = List.of(p1);

        Month month = new Month();
        List<Month> monthList = List.of(month);
        Order order = new Order(); order.getOrderLines().add(new OrderLine());

        Category c1 = new Category(); c1.setId(1); c1.getProducts().add(p1); c1.setName("category1");
        Category c2 = new Category(); c2.setId(2);  c2.setName("category2");
        List<Category> categories = List.of(c1,c2);


        //when
        when(categoryService.findAll()).thenReturn(categories);
        when(productService.findAllProductPerMonth(any(String.class))).thenReturn(products);
        when(monthService.findAll()).thenReturn(monthList);
        when(clientAuthDetail.getClient()).thenReturn(new Client());
        when(orderService.hasPendingOrder(any(Client.class))).thenReturn(order);

        //then
        List<MonthDTO> expected3 = monthList
                .stream()
                .map(m -> monthMapper.toDTO(m))
                .toList();

        List<CategoryDto> expected1 = mapCategoryListToDto();

        List<ProductDTO> expected2 = products.stream().map(p->productMapper.toDTO(p)).toList();

        mockMvc.perform(MockMvcRequestBuilders.get("/products/month/March"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("product/product-list"))
                .andExpect(MockMvcResultMatchers.model().attribute("categoryList",contains(expected1.toArray())))
                .andExpect(MockMvcResultMatchers.model().attribute("productList",contains(expected2.toArray())))
                .andExpect(MockMvcResultMatchers.model().attribute("monthList",contains(expected3.toArray())));
    }


    private List<CategoryDto> mapCategoryListToDto(){

        List<Category> categoryList = categoryService.findAll();
        return categoryList
                .stream()
                .map(c -> categoryMapper.toDTO(c))
                .collect(Collectors.toList());
    }
    private List<ProductDTO> mapCategoryProductsToDto(int id){

        return categoryService
                .findById(id)
                .getProducts()
                .stream()
                .map(p -> productMapper.toDTO(p))
                .collect(Collectors.toList());
    }

}