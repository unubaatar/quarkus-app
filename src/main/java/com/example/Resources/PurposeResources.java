package com.example.Resources;

import com.example.Entities.User;
import com.example.Services.PurposeServices;
import com.example.Entities.Purpose;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

class PurposeRequest {
    public String title;
    public String description;
    public UUID createdUser;  // Just UUID of User
}

@Path("/purposes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PurposeResources {
    @Inject PurposeServices PurposeServices;

    @GET
    public Response getAll() {
        List<Purpose> purposes =  PurposeServices.getAll();
        if(purposes.isEmpty()) {
            return Response.noContent().build();
        }
        return Response.ok(purposes) .header("X-Total-Count", purposes.size())
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") UUID id) {
        Optional<Purpose> foundPurpose = PurposeServices.getById(id);
        if(foundPurpose.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "User not found",
                            "id", id.toString()))
                    .build();
        }
        return Response.ok(foundPurpose.get()).build();
    }

    @POST
    public Response create(PurposeRequest request) {
        if (request == null || request.title == null || request.description == null || request.createdUser == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("message", "title, description and createdUser are required"))
                    .build();
        }

        User user = User.findById(request.createdUser);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("message", "User not found"))
                    .build();
        }

        Purpose purpose = new Purpose();
        purpose.setTitle(request.title);
        purpose.setDescription(request.description);
        purpose.setCreatedUser(user);

        Purpose createdPurpose = PurposeServices.create(purpose);

        return Response.status(Response.Status.CREATED).entity(createdPurpose).build();
    }

    @PATCH
    @Path("/{id}")
    public Response patch(@PathParam("id") UUID id, Purpose purpose) {
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "ID is required"))
                    .build();
        }

        Optional<Purpose> updatedPurpose = PurposeServices.update(id, purpose);

        if (updatedPurpose.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "Purpose not found for id: " + id))
                    .build();
        }

        return Response.ok(updatedPurpose.get()).build();
    }


    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") UUID id, Purpose purpose) {
        if(id == null ) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "ID and update data are required"))
                    .build();
        }

        Optional<Purpose> deletedPurpose = PurposeServices.delete(id);

        if (deletedPurpose.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "Purpose not found",
                            "id", id.toString()))
                    .build();
        }

        return Response.status(Response.Status.NOT_FOUND)
                .entity(Map.of("message", "Successfully deleted",
                        "id", id.toString()))
                .build();

    }
}
