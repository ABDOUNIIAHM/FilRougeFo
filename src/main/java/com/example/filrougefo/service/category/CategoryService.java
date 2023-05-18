package com.example.filrougefo.service.category;
import com.example.filrougefo.entity.Category;
import com.example.filrougefo.entity.Product;
import com.example.filrougefo.exception.CategoryNotFoundException;
import com.example.filrougefo.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService implements IntCategoryService{
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    @Override
    public Category findById(int id){

        Category cat = categoryRepository
                        .findById(id)
                        .orElseThrow(()-> new CategoryNotFoundException("No such category found for Id:"+id));

                return cat;
    }
    @Override
    public List<Category> findBySearchedName(String name){

        List<Category> searchedCategories = categoryRepository
                .findCategoriesByNameContainingIgnoreCase(name)
                .orElseThrow(()->new CategoryNotFoundException("No such category found for: "+name));

        return searchedCategories;
    }
    @Override
    public List<Category> findAllCategoriesExceptProductCategory(Product product) {

        List<Category> categories =

                categoryRepository
                .findAll()
                .stream()
                .filter(category -> category.getId() != product.getCategory().getId())
                .collect(Collectors.toList());

        return categories;
    }
}
