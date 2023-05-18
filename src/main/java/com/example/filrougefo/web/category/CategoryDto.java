package com.example.filrougefo.web.category;

import com.example.filrougefo.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class CategoryDto {
    private int id;
    private String name;
    private List<Product> productList;
}
