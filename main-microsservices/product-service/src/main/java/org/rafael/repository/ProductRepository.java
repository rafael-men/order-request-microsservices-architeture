package org.rafael.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.rafael.models.ProductModel;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<ProductModel> {}
