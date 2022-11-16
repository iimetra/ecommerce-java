package com.iimetra.order.ecommerce.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemPk implements Serializable {

    private static long serialVersionUID = 5074947294298476737L;

    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(name = "order_id", nullable = false)
    private String orderId;

}
