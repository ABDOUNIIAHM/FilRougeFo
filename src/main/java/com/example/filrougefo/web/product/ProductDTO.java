package com.example.filrougefo.web.product;

import com.example.filrougefo.entity.Category;
import com.example.filrougefo.entity.Month;
import lombok.*;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor @EqualsAndHashCode
public class ProductDTO {

    private int id;
    private String name;
    private String unit;
    private BigDecimal pricePerUnit;
    private String imgBase64;
    private BigDecimal vat;
    private String description;
//    private BigDecimal stock;
    private Category category;
    private List<Month> seasonalMonths = new ArrayList<>();

}
