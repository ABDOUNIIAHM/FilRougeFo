package com.example.filrougefo.web.order;

import com.example.filrougefo.entity.Order;
import com.example.filrougefo.web.product.ProductDTO;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @EqualsAndHashCode @ToString

public class OrderLineDto {
    private long id;
    private Order order;
    private ProductDTO product;
    private BigDecimal quantity;
    private BigDecimal discount;

    public BigDecimal computeTotal() {

        if (this.discount == null) {
            this.setDiscount(BigDecimal.valueOf(0));
        }
        // price * (1 + vat) * (1 - discount) * qty
        BigDecimal price = this.product.getPricePerUnit().multiply(this.getProduct().getVat().add(BigDecimal.valueOf(1)));
        return price.multiply((BigDecimal.valueOf(1).subtract(discount))).multiply(this.quantity);

    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }
}
