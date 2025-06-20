package org.rafael.client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import org.rafael.dto.ProductDto;

@Path("/products")
@RegisterRestClient(baseUri = "http://localhost:8080/products")
@ApplicationScoped
public interface ProductClient {

    @GET
    @Path("/{id}")
    ProductDto getProductById(@PathParam("id") Long id);
}
