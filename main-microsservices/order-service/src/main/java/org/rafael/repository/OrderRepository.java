package org.rafael.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.rafael.models.OrderModel;

@ApplicationScoped
public class OrderRepository implements PanacheRepository<OrderModel> {}
