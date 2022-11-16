package com.iimetra.order.ecommerce.util;

import com.iimetra.order.ecommerce.dto.AddressDto;
import com.iimetra.order.ecommerce.dto.OrderDto;
import com.iimetra.order.ecommerce.dto.OrderItemDto;
import com.iimetra.order.ecommerce.enumeration.OrderStatus;
import com.iimetra.order.ecommerce.enumeration.PaymentMode;
import com.iimetra.order.ecommerce.enumeration.ShippingMode;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderUtils {

    public static OrderDto createTestOrder() {
        return new OrderDto(
            "1",
            6.0,
            10.0,
            2.0,
            2.0,
            "test",
            ShippingMode.DELIVERY.name(),
            10.0,
            PaymentMode.CREDIT.name(),
            createAddress(),
            createAddress(),
            List.of(
                new OrderItemDto("101", "10"),
                new OrderItemDto("102", "10")
            ),
            OrderStatus.RECEIVED.name()
        );
    }

    private static AddressDto createAddress() {
        return new AddressDto(
            "3755 M lane",
            "Apt 311",
            "Aurora",
            "IL",
            "60504",
            "test.gmail.com",
            "1234567890"
        );
    }
}
