package com.example.Resources;

import com.example.Entities.User;
import com.example.Services.UserServices;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResources {
    @Inject
    UserServices UserServices;

//    @GET
//    public List<User> getAll() {
//        return UserServices.getAllUsers();
//    }

    @GET
    public String test() {
        System.out.println("jiijiii sda ");
        return "pizda";
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") UUID id) {
        Optional<User> user = UserServices.getUserById(id);
        if(user.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(user).build();
    }

    @POST
    public Response create(User user) {
        User created = UserServices.createUser(user);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") UUID id, User updatedUser) {
        Optional<User> updated = UserServices.updateUser(id , updatedUser);
        if (updated.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") UUID id) {
        long deleted = UserServices.deleteUser(id);
        if (deleted == 0) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}

