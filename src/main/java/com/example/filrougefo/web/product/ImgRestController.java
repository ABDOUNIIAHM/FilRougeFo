package com.example.filrougefo.web.product;

import com.example.filrougefo.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.client.RestTemplate;


@AllArgsConstructor
public class ImgRestController {
    private RestTemplate restTemplate;

    public String fetchProductImgBase64(Product product) {
        String apiUrl = "http://localhost:8080/webapi/images/product/" + product.getId();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);
        return responseEntity.getBody();
    }
}
