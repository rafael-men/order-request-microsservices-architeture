package org.rafael.services;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.hibernate.service.spi.ServiceException;
import org.rafael.dto.ProductDto;
import org.rafael.models.ProductModel;
import org.rafael.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;


@ApplicationScoped
public class ProductServices {

    private final ProductRepository repository;

    public ProductServices(ProductRepository repository) {
        this.repository = repository;
    }

    public List<ProductDto> getAllProducts() {
        try {
            List<ProductModel> products = (List<ProductModel>) repository.findAll();
            List<ProductDto> productDtos = products.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());

            return productDtos;

        } catch (Exception e) {
            throw new ServiceException("Erro ao buscar produtos", e);
        }
    }



    @Transactional
    public ProductDto createProduct(ProductDto productDto) {
        try {
            if (productDto == null) {
                throw new IllegalArgumentException("ProductDto não pode ser nulo");
            }

            if (productDto.getName() == null || productDto.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("Nome do produto é obrigatório");
            }

            if (productDto.getPrice() == null || productDto.getPrice().compareTo(java.math.BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Preço deve ser maior ou igual a zero");
            }
            ProductModel product = convertToModel(productDto);

            repository.persist(product);
            return convertToDto(product);

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Erro ao criar produto", e);
        }
    }

    @Transactional
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        try {

            if (id == null) {
                throw new IllegalArgumentException("ID do produto é obrigatório");
            }

            if (productDto == null) {
                throw new IllegalArgumentException("ProductDto não pode ser nulo");
            }

            if (productDto.getName() == null || productDto.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("Nome do produto é obrigatório");
            }

            if (productDto.getPrice() == null || productDto.getPrice().compareTo(java.math.BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Preço deve ser maior ou igual a zero");
            }
            ProductModel existingProduct = repository.findById(id);
            if (existingProduct == null) {
                throw new IllegalArgumentException("Produto não encontrado com ID: " + id);
            }

            existingProduct.setName(productDto.getName());
            existingProduct.setDescription(productDto.getDescription());
            existingProduct.setPrice(productDto.getPrice());
            existingProduct.setCategory(productDto.getCategory());

            return convertToDto(existingProduct);

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Erro ao atualizar produto", e);
        }
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    private ProductDto convertToDto(ProductModel product) {
        ProductDto dto = new ProductDto();
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setCategory(product.getCategory());
        return dto;
    }

        private ProductModel convertToModel(ProductDto dto) {
            ProductModel product = new ProductModel();
            product.setName(dto.getName());
            product.setDescription(dto.getDescription());
            product.setPrice(dto.getPrice());
            product.setCategory(dto.getCategory());
            return product;
        }

}
