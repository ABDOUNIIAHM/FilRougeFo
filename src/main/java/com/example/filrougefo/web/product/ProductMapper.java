package com.example.filrougefo.web.product;

import com.example.filrougefo.entity.Product;
import org.mapstruct.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    RestTemplate restTemplate = new RestTemplate();

    @Mapping(target = "imgBase64", ignore = true)
    ProductDTO toDTO(Product Product);

    @Mapping(target = "imgUrl", ignore = true)
    @Mapping(target = "stock", ignore = true)
    @Mapping(target = "orderLines", ignore = true)
    Product fromDTO(ProductDTO ProductDTO);

    @AfterMapping
    default void fetchBase64FromUrl(Product product, @MappingTarget ProductDTO productDTO) {

        String urlAPI = "http://localhost:8080/webapi/images/product/" + product.getId();
        ResponseEntity<String> response = restTemplate.getForEntity(urlAPI, String.class);

        productDTO.setImgBase64(response.getBody());
    }
}