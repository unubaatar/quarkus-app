package com.example.Resources;

import com.example.Services.CoupleServices;
import com.example.Entities.Couple;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Path("/couples")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CoupleResources {

    @Inject CoupleServices CoupleServices;

    @GET
    public List<Couple> getAll() {
        return CoupleServices.getAll();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") UUID id) {
        Optional<Couple> couple = CoupleServices.getById(id);
        if(couple.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(couple).build();
    }

    @POST
    public Response create(Couple couple) {
        Couple createdCouple = CoupleServices.create(couple);
        return Response.status(Response.Status.CREATED).entity(createdCouple).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") UUID id) {
        long deletedCouple = CoupleServices.delete(id);

        if (deletedCouple > 0) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
