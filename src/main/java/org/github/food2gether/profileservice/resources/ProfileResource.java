
package org.github.food2gether.profileservice.resources;

import com.github.food2gether.shared.Constant;
import com.github.food2gether.shared.model.Profile;
import com.github.food2gether.shared.response.APIResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.github.food2gether.profileservice.service.ProfileService;

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
  public Response getProfileById(@PathParam("id") Long id) {
    return APIResponse.response(Response.Status.OK,
        Profile.DTO.fromProfile(this.profileService.getProfile(id)));
  }

  @GET
  @Path("/me")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getProfileMe(@HeaderParam(Constant.USER_MAIL_HEADER) String primaryEmail) {
    if (primaryEmail == null) {
      throw new WebApplicationException("Required Header is missing: " + Constant.USER_MAIL_HEADER, Response.Status.BAD_REQUEST);
    }

    Profile profile = this.profileService.getProfileByEmail(primaryEmail);
    
    return APIResponse.response(
        Response.Status.OK,
        Profile.DTO.fromProfile(profile)
    );
  }

  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createOrUpdateProfile(@HeaderParam(Constant.USER_MAIL_HEADER) String primaryEmail,
      Profile.DTO body) {
    if (body == null) {
      throw new WebApplicationException("Missing request body", Response.Status.BAD_REQUEST);
    }

    if (primaryEmail != null) {
      body.setPrimaryEmail(primaryEmail);
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
