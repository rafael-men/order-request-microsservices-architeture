package  org.rafael.controllers;

import jakarta.inject.Inject; // Import for @Inject
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.rafael.dto.ProductDto;
import org.rafael.services.ProductServices;
import org.hibernate.service.spi.ServiceException;
import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {

    @Inject
    ProductServices services;



    @GET
    public List<ProductDto> getAllProduct() {
        return services.getAllProducts();
    }

    @POST
    @Path("/new")
    public Response createProduct(ProductDto product) {
        try {
            // It's good practice to return the created resource or its ID
            ProductDto createdProduct = services.createProduct(product);
            return Response.status(Response.Status.CREATED).entity(createdProduct).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        } catch (ServiceException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to create product: " + e.getMessage())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An unexpected error occurred during product creation.")
                    .build();
        }
    }

    @PUT
    @Path("/update/{id}")
    public Response updateProduct(@PathParam("id") Long id, ProductDto product) {
        try {
            ProductDto updatedProduct = services.updateProduct(id, product);
            if (updatedProduct == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Product with ID " + id + " not found.")
                        .build();
            }
            return Response.ok(updatedProduct).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        } catch (ServiceException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to update product: " + e.getMessage())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An unexpected error occurred during product update.")
                    .build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteProduct(@PathParam("id") Long id) {
        try {
            services.deleteProduct(id);
            return Response.noContent().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        } catch (ServiceException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to delete product: " + e.getMessage())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An unexpected error occurred during product deletion.")
                    .build();
        }
    }
}