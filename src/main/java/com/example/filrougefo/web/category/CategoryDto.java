package com.example.filrougefo.web.category;

import com.example.filrougefo.entity.Product;
import lombok.*;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @EqualsAndHashCode
public class CategoryDto {
    private int id;
    private String name;
    private List<Product> productList;
}
