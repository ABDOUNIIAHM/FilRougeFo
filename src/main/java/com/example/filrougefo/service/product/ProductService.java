package com.example.filrougefo.service.product;

import com.example.filrougefo.entity.Month;
import com.example.filrougefo.entity.Order;
import com.example.filrougefo.entity.OrderLine;
import com.example.filrougefo.entity.Product;
import com.example.filrougefo.exception.MonthNotFoundException;
import com.example.filrougefo.exception.ProductNotFoundException;
import com.example.filrougefo.repository.ProductRepository;
import com.example.filrougefo.service.month.MonthService;
import com.example.filrougefo.service.orderline.IntOrderLineService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService implements IntProductService {
    private final ProductRepository productRepository;
    private final MonthService monthService;
    private IntOrderLineService orderLineService;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(int id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException("No such product with id=" + id + " was found !"));
    }

    @Override
    public List<Product> searchProductByNamePattern(String name) {
        return productRepository
                .findAllByNameContainingIgnoreCase(name)
                .orElseThrow(() -> new ProductNotFoundException("No such product with name=" + name + " was found !"));
    }
    @Override
    public List<Product> findAllProductPerMonth(int id) {
        Month month = monthService
                .findById(id)
                .orElseThrow(() -> new MonthNotFoundException("No such Month found for id=" + id));

        return productRepository.findAllBySeasonalMonthsContaining(month);

    }

    @Override
    public List<Product> searchByKeyword(String keyword) {
        return productRepository.findByPartialNameOrDescriptionIgnoreCase(keyword);
    }
    @Override
    public List<Product> searchByKeywordForCategory(String keyword) {
        return productRepository.findProductsByPartialCategoryNameIgnoreCase(keyword);
    }

    @Override
    public List<Product> searchByKeywordForMonths(String keyword) {
        return productRepository.findProductsByPartialMonthName(keyword);
    }

    public List<Product> searchProductsGlobal(String keyword) {

        List<Product> productList = new ArrayList<>();

        List<Product> productListProduit = searchByKeyword(keyword);
        List<Product> productListCategory = searchByKeywordForCategory(keyword);
        List<Product> productListMonth = searchByKeywordForMonths(keyword);

        productList.addAll(productListProduit);
        productList.addAll(productListCategory);
        productList.addAll(productListMonth);

        // Removes duplicates
       return productList.stream().distinct().toList();
    }

    @Override
    public List<Product> checkIfAvailableStock(Order order) {

        List<OrderLine> orderLines = orderLineService.findAllOrderLinesByOrderId(order.getId());

        List<Product> outOfStock = orderLines
                .stream()
                .filter(ol -> ol.getQuantity().doubleValue() > ol.getProduct().getStock().doubleValue())
                .map(ol-> ol.getProduct())
                .toList();

        return outOfStock;
    }

    @Override
    public void updateProductStock(Order order) {

        List<OrderLine> orderLines = orderLineService.findAllOrderLinesByOrderId(order.getId());

        orderLines
                .stream()
                .map(ol-> {
                    Product p = ol.getProduct();
                    double stock = ol.getProduct().getStock().doubleValue();
                    double quantity = ol.getQuantity().doubleValue();
                    double newStock = stock - quantity;
                    p.setStock(BigDecimal.valueOf(newStock));
                    return productRepository.save(p);
                }).toList();
    }

    public void save(Product product) {
        productRepository.save(product);
    }
}
