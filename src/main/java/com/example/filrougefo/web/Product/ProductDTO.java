package com.example.filrougefo.web.Product;

import com.example.filrougefo.entity.Category;
import com.example.filrougefo.entity.Months;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private int id;
    private String name;
    private String unit;
    private BigDecimal pricePerUnit;
    private String imgUrl;
    private BigDecimal vat;
    private String description;
//    private BigDecimal stock;
    private Category category;
    private List<Months> seasonalMonths = new ArrayList<>();

}
