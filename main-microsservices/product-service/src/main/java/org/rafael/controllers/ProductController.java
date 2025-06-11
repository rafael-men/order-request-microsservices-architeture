package org.rafael.controllers;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.rafael.dto.ProductDto;
import org.rafael.services.ProductServices;

import java.util.List;

@Path("/products")
public class ProductController {

    private final ProductServices services;

    public ProductController(ProductServices services) {
        this.services = services;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductDto> getAllProduct() {
        return services.getAllProducts();
    }

    @POST
    @Path("/new")
    public Response createProduct(ProductDto product) {
        try {
            services.createProduct(product);
            return Response.ok().build();
        }
        catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/update/{id}")
    public Response updateProduct(@PathParam("id") Long id,ProductDto product) {
        try {
            services.updateProduct(id,product);
            return Response.ok().build();
        }
        catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteProduct(@PathParam("id") Long id) {
        services.deleteProduct(id);
        return Response.ok().build();
    }
}

