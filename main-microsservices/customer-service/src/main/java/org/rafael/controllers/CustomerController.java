package org.rafael.controllers;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.rafael.dto.CustomerDto;
import org.rafael.services.CustomerService;

import java.util.List;

@Path("/customers")
public class CustomerController {

    @Inject
    CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CustomerDto> findAll() {
        return service.findAllCustomers();
    }

    @POST
    @Path("/new")
    @Transactional
    public Response newCustomer(CustomerDto dto) {
        try {
            service.createCustomer(dto);
            return Response.ok().build();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    public Response updateCustomer(@PathParam("id") Long id, CustomerDto dto) {
        try {
            service.updateCustomer(id,dto);
            return Response.accepted().build();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return Response.serverError().build();
        }
    }
}
