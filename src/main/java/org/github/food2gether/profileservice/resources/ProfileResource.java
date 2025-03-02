
package org.github.food2gether.profileservice.resources;

import com.github.food2gether.shared.model.Profile;
import com.github.food2gether.shared.response.APIResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.github.food2gether.profileservice.service.ProfileService;

import java.util.List;

@Path("/api/v1/profiles/")
public class ProfileResource {

    @Inject
    ProfileService profileService;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProfiles(@QueryParam("search") String searchQuery) {
        List<Profile> profiles = this.profileService.getAll(searchQuery);
        return APIResponse.response(Response.Status.OK,
                profiles.stream().map(Profile.DTO::fromProfile).toList()
        );
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfileById(@PathParam("id") String id) {
        return APIResponse.response(Response.Status.OK, Profile.DTO.fromProfile(this.profileService.getProfile(Long.parseLong(id))));
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrUpdateProfile(Profile.DTO body) {
        if (body == null) {
            throw new WebApplicationException("Missing request body", Response.Status.BAD_REQUEST);
        }

        Profile profile = this.profileService.createOrUpdateProfile(body);

        return APIResponse.response(body.getId() == null
                ? Response.Status.CREATED
                : Response.Status.OK,
                Profile.DTO.fromProfile(profile)
        );
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProfile(@PathParam("id") Long id) {
        Profile profile = this.profileService.deleteProfile(id);
        return APIResponse.response(Response.Status.OK, Profile.DTO.fromProfile(profile));
    }
}
