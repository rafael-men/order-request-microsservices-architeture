package org.rafael.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    private Long customerId;
    private String customerName;
    private Long productId;
    private BigDecimal orderValue;

    public OrderModel() {
    }

    public OrderModel(Long orderId, Long customerId, String customerName, Long productId, BigDecimal orderValue) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.productId = productId;
        this.orderValue = orderValue;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(BigDecimal orderValue) {
        this.orderValue = orderValue;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        OrderModel that = (OrderModel) object;
        return Objects.equals(orderId, that.orderId) && Objects.equals(customerId, that.customerId) && Objects.equals(customerName, that.customerName) && Objects.equals(productId, that.productId) && Objects.equals(orderValue, that.orderValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, customerId, customerName, productId, orderValue);
    }
}
