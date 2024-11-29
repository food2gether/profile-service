package resorces;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Map;

@Path("/api/v1/profiles")
public class ProfileResorce {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProfiles() {
        String dummyResponse = """
                { "success": true, "data": [
                    {
                      "id": 1731095302112,
                      "name": "max_mustermann",
                      "displayname": "Max Mustermann",
                      "contact": [
                        {
                          "displayname": "E-Mail",
                          "value": "max.mustermann@example.com"
                        },
                        {
                          "displayname": "Phone",
                          "value": "+49 1568 483234"
                        }
                      ]
                    },
                    {
                      "id": 1099871596193,
                      "name": "phro",
                      "displayname": "Dr. Philipp Rohde",
                      "contact": [
                        {
                          "displayname": "E-Mail",
                          "value": "rhode@fh-aachen.com"
                        },
                        {
                          "displayname": "Discord",
                          "value": "phro#3865"
                        }
                      ]
                    }
                  ] }""";
        return Response.ok(dummyResponse).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfileById(@PathParam("id") String id) {
        String dummyResponse;
        if (id.equals("1731095302112")) {dummyResponse = """
                {
                  "success": true,
                  "data": {
                    "id": 1731095302112,
                    "name": "max_mustermann",
                    "displayname": "Max Mustermann",
                    "contact": [
                      {
                        "displayname": "E-Mail",
                        "value": "max.mustermann@example.com"
                      },
                      {
                        "displayname": "Phone",
                        "value": "+49 1568 483234"
                      }
                    ]
                  }
                }""";
            return Response.status(Response.Status.OK).entity(dummyResponse).build();
        }
        else { dummyResponse = """
                {{
                  "success": false,
                  "error": {
                    "code": 404,
                    "message_key": "account.notfound"
                  }
                }""";
        }
        return Response.status(Response.Status.NOT_FOUND).entity(dummyResponse).build();
    }

    @PUT
    @Path("/{id}/{name}/{displayname}/{contact}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrUpdateProfile(@PathParam("id") int id, @PathParam("name") String name,
                                          @PathParam("displayname") String displayname,
                                          @PathParam("contact") String contact)
    {
        if (id == 1)
        {
            String dummyResponse = """
                    {
                      "success": true,
                      "data": {
                        "id": 1731095302112
                      }
                    }""";
            return Response.status(Response.Status.OK).entity(dummyResponse).build();
        }
        if (id == 2)
        {
            String dummyResponse = """
                    {
                      "success": true,
                      "data": {
                        "id": 1731095302112
                      }
                    }""";
            return Response.status(Response.Status.CREATED).entity(dummyResponse).build();
        }
        if (id == 3)
        {
            String dummyResponse = """
                    {
                      "success": false,
                      "error": {
                        "code": 400,
                        "message_key": "request.missingarguments"
                      }
                    }""";
            return Response.status(Response.Status.BAD_REQUEST).entity(dummyResponse).build();
        }
        if (id == 4)
        {
            String dummyResponse = """
                    {
                      "success": false,
                      "error": {
                        "code": 401,
                        "message_key": "authorization.failed"
                      }
                    }""";
            return Response.status(Response.Status.UNAUTHORIZED).entity(dummyResponse).build();
        }
        if (id == 5)
        {
           String dummyResponse = """
                   {
                     "success": false,
                     "error": {
                       "code": 403,
                       "message_key": "user.exists"
                     }
                   }""";
           return Response.status(Response.Status.FORBIDDEN).entity(dummyResponse).build();
        }
        String dummyResonse = """
                {
                  "success": false,
                  "error": {
                    "code": 409,
                    "message_key": "account.exists"
                  }
                }""";
        return Response.status(Response.Status.CONFLICT).entity(dummyResonse).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProfile(@PathParam("id") String id) {
        String dummyResponse ;
        if (id.equals("1731095302112")) {
            dummyResponse = """
                    {
                      "success": true,
                      "data": null
                    }""";
            return Response.status(Response.Status.ACCEPTED).entity(dummyResponse).build();
        }
        else
        {
            dummyResponse = """
                    {
                      "success": false,
                      "error": {
                        "code": 401,
                        "message_key": "authorization.failed"
                      }
                    }""";
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity(dummyResponse).build();
    }
}
