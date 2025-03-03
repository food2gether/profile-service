package org.github.food2gether.profileservice.resources;

import com.github.food2gether.shared.model.ContactInformation;
import com.github.food2gether.shared.response.APIResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.github.food2gether.profileservice.service.ContactInformationService;
import java.util.List;

@Path("/api/v1/profiles/{profile_id}/contact-information")
public class ContactInformationResource {

  @Inject
  ContactInformationService service;

  @PUT
  public Response updateOrCreateContactInformation(@PathParam("profile_id") Long profileId, List<ContactInformation.DTO> body) {
    if (body == null) {
      throw new WebApplicationException("Missing request body", Response.Status.BAD_REQUEST);
    }

    List<ContactInformation> informations = this.service.createOrUpdate(profileId, body);

    return APIResponse.response(
        Response.Status.OK,
        informations.stream()
            .map(ContactInformation.DTO::fromContactInformation)
            .toList()
    );
  }

  @GET
  public Response getContactInformation(@PathParam("profile_id") Long profileId) {
    List<ContactInformation> informations = this.service.getAll(profileId);

    return APIResponse.response(
        Response.Status.OK,
        informations.stream()
            .map(ContactInformation.DTO::fromContactInformation)
            .toList()
    );
  }

}
