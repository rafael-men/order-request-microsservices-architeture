package org.rafael.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.rafael.models.CustomerModel;

import java.util.List;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<CustomerModel> {}
