package org.rafael.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.rafael.client.CustomerClient;
import org.rafael.client.ProductClient;
import org.rafael.dto.CustomerDto;
import org.rafael.dto.OrderDto;
import org.rafael.models.OrderModel;
import org.rafael.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class OrderService {

    private final OrderRepository repository;
    private final CustomerClient client;
    private final ProductClient product;

    public OrderService(OrderRepository repository, @RestClient CustomerClient client, @RestClient ProductClient product) {
        this.repository = repository;
        this.client = client;
        this.product = product;
    }

    public void createOrder(OrderDto dto) {
        CustomerDto order = client.getCustomerById(dto.getCustomerId());

        if (order.getName().equals(dto.getCustomerName()) &&
                product.getProductById(dto.getProductId()) != null) {
            repository.persist(mapDtoToEntity(dto));
        } else {
            throw new NotFoundException("Cliente ou produto inválido");
        }
    }

    public List<OrderModel> getAllOrders() {
        return repository.listAll();
    }

    public void updateOrder(Long id, OrderDto dto) {
        Optional<OrderModel> existingOrderOpt = repository.findByIdOptional(id);

        if (existingOrderOpt.isEmpty()) {
            throw new NotFoundException("Pedido não encontrado com ID: " + id);
        }

        OrderModel existingOrder = existingOrderOpt.get();

        existingOrder.setCustomerId(dto.getCustomerId());
        existingOrder.setCustomerName(dto.getCustomerName());
        existingOrder.setProductId(dto.getProductId());
        existingOrder.setOrderValue(dto.getOrderValue());

        repository.persist(existingOrder);
    }

    private OrderModel mapDtoToEntity(OrderDto dto) {
        OrderModel entity = new OrderModel();
        entity.setCustomerId(dto.getCustomerId());
        entity.setCustomerName(dto.getCustomerName());
        entity.setProductId(dto.getProductId());
        entity.setOrderValue(dto.getOrderValue());
        return entity;
    }
}
