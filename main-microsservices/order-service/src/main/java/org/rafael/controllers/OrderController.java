package org.rafael.controllers;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.rafael.dto.OrderDto;
import org.rafael.models.OrderModel;
import org.rafael.service.OrderService;

import java.util.List;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderController {

    private final OrderService service;

    @Inject
    public OrderController(OrderService service) {
        this.service = service;
    }

    @POST
    public Response createOrder(OrderDto dto) {
        service.createOrder(dto);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @RolesAllowed({"user","admin"})
    public List<OrderModel> getAllOrders() {
        return service.getAllOrders();
    }

    @PUT
    @Path("/{id}")
    public Response updateOrder(@PathParam("id") Long id, OrderDto dto) {
        service.updateOrder(id, dto);
        return Response.ok().build();
    }
}
