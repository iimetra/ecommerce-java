package com.iimetra.order.ecommerce.mapper;

import com.iimetra.order.ecommerce.dto.AddressDto;
import com.iimetra.order.ecommerce.dto.OrderDto;
import com.iimetra.order.ecommerce.dto.OrderItemDto;
import com.iimetra.order.ecommerce.model.Address;
import com.iimetra.order.ecommerce.model.Order;
import com.iimetra.order.ecommerce.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderDetailsMapper {

    @Mapping(source = "payment.amount", target = "amount")
    @Mapping(source = "payment.paymentMode", target = "paymentMode")
    OrderDto toOrderDto(Order order);

    Order toOrderEntity(OrderDto orderDto);

    @Mapping(source = "product.productId", target = "productId")
    OrderItemDto toOrderItemDto(OrderItem orderItem);

    OrderItem toOrderItemEntity(OrderItemDto orderItemDto);

    Address toAddressEntity(AddressDto addressDto);

    AddressDto toAddressDto(Address address);
}
