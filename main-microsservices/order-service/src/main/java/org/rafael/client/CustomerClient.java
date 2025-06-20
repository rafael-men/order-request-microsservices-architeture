package org.rafael.client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.rafael.dto.CustomerDto;

@Path("/customers")
@RegisterRestClient(baseUri = "http://localhost:8081/customers")
@ApplicationScoped
public interface CustomerClient {

    @GET
    @Path("/{id}")
    CustomerDto getCustomerById(@PathParam("id") Long id);
}
