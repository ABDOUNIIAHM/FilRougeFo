package com.example.filrougefo.web.product;

import com.example.filrougefo.entity.Month;
import com.example.filrougefo.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MonthMapper {

    @Mapping(target = "countProduct", expression = "java(MonthMapper.getListSize(month.getProducts()))")
    @Mapping(target = "name", expression = "java(MonthMapper.getFrenchName(month.getName()))")
    MonthDTO toDTO(Month month);

    @Mapping(target = "products", ignore = true)
    Month fromDTO(MonthDTO monthDTO);

    // Because changing db month name storage would lead to issues in the back-office.
    static String getFrenchName(String monthName) {
        return switch (monthName) {
            case "January" -> "Janvier";
            case "February" -> "Février";
            case "March" -> "Mars";
            case "April" -> "Avril";
            case "May" -> "Mai";
            case "June" -> "Juin";
            case "July" -> "Juillet";
            case "August" -> "Août";
            case "September" -> "Septembre";
            case "October" -> "Octobre";
            case "November" -> "Novembre";
            default -> monthName;
        };
    }

    static int getListSize(List<Product> list) {
        return list != null ? list.size() : 0;
    }

}
