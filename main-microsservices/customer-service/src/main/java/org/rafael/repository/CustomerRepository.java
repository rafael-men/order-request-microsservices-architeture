package org.rafael.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.rafael.dto.CustomerDto;
import org.rafael.models.CustomerModel;

import java.util.List;

@ApplicationScoped
public interface CustomerRepository extends PanacheRepository<CustomerModel> {}
