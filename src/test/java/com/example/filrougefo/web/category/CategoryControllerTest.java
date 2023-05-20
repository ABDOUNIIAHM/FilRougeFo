package com.example.filrougefo.web.category;

import com.example.filrougefo.entity.Category;
import com.example.filrougefo.entity.Product;
import com.example.filrougefo.service.category.CategoryService;
import com.example.filrougefo.web.Product.ProductDTO;
import com.example.filrougefo.web.Product.ProductMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
@Import(CategoryConfigurationTestController.class)
@WebMvcTest(CategoryController.class)
class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CategoryService categoryService;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ProductMapper productMapper;

    @Test
    void ShouldReturnAllCategories() throws Exception {
        //given
        Category c1 = new Category();
        Category c2 = new Category();
        c1.setId(1);
        c2.setId(2);
        c1.setName("category1");
        c2.setName("category2");
        List<Category> categories = List.of(c1,c2);
        //when
        when(categoryService.findAll()).thenReturn(categories);
        //then
        List<CategoryDto> expected = mapCategoryListToDto();
        mockMvc.perform(MockMvcRequestBuilders.get("/categories"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("product-and-category"))
                .andExpect(MockMvcResultMatchers.model().attribute("categories",hasSize(expected.size())))
                .andExpect(MockMvcResultMatchers.model().attribute("categories",contains(expected.toArray())));
    }

    @Test
    void ShouldReturnAllCategoryProducts() throws Exception {
        //given
        Product p1 = new Product();
        Product p2 = new Product();
        p1.setId(1);
        p2.setId(1);
        Category c1 = new Category();
        Category c2 = new Category();
        c1.setId(1);c1.getProducts().add(p1);c1.getProducts().add(p2);
        c2.setId(2);
        c1.setName("category1");
        c2.setName("category2");
        List<Category> categories = List.of(c1,c2);

        //when
        when(categoryService.findAll()).thenReturn(categories);
        when(categoryService.findById(ArgumentMatchers.any(int.class))).thenReturn(c1);

        //then
        List<CategoryDto> expected = mapCategoryListToDto();
        List<ProductDTO>
        mockMvc.perform(MockMvcRequestBuilders.get("/categories"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("product-and-category"))
                .andExpect(MockMvcResultMatchers.model().attribute("categories",hasSize(expected.size())))
                .andExpect(MockMvcResultMatchers.model().attribute("categories",contains(expected.toArray())));
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