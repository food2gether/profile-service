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
        String dummyResponse = "{ \"success\": true, \"data\": [\n" +
                "    {\n" +
                "      \"id\": 1731095302112,\n" +
                "      \"name\": \"max_mustermann\",\n" +
                "      \"displayname\": \"Max Mustermann\",\n" +
                "      \"contact\": [\n" +
                "        {\n" +
                "          \"displayname\": \"E-Mail\",\n" +
                "          \"value\": \"max.mustermann@example.com\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"displayname\": \"Phone\",\n" +
                "          \"value\": \"+49 1568 483234\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 1099871596193,\n" +
                "      \"name\": \"phro\",\n" +
                "      \"displayname\": \"Dr. Philipp Rohde\",\n" +
                "      \"contact\": [\n" +
                "        {\n" +
                "          \"displayname\": \"E-Mail\",\n" +
                "          \"value\": \"rhode@fh-aachen.com\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"displayname\": \"Discord\",\n" +
                "          \"value\": \"phro#3865\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ] }";
        return Response.ok(dummyResponse).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfileById(@PathParam("id") String id) {
        String dummyResponse;
        if (id.equals("1731095302112")) {dummyResponse = "{\n" +
                "  \"success\": true,\n" +
                "  \"data\": {\n" +
                "    \"id\": 1731095302112,\n" +
                "    \"name\": \"max_mustermann\",\n" +
                "    \"displayname\": \"Max Mustermann\",\n" +
                "    \"contact\": [\n" +
                "      {\n" +
                "        \"displayname\": \"E-Mail\",\n" +
                "        \"value\": \"max.mustermann@example.com\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"displayname\": \"Phone\",\n" +
                "        \"value\": \"+49 1568 483234\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
            return Response.status(Response.Status.OK).entity(dummyResponse).build();
        }
        else { dummyResponse = "{{\n" +
                "  \"success\": false,\n" +
                "  \"error\": {\n" +
                "    \"code\": 404,\n" +
                "    \"message_key\": \"account.notfound\"\n" +
                "  }\n" +
                "}";
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
            String dummyResponse = "{\n" +
                    "  \"success\": true,\n" +
                    "  \"data\": {\n" +
                    "    \"id\": 1731095302112\n" +
                    "  }\n" +
                    "}";
            return Response.status(Response.Status.OK).entity(dummyResponse).build();
        }
        if (id == 2)
        {
            String dummyResponse = "{\n" +
                    "  \"success\": true,\n" +
                    "  \"data\": {\n" +
                    "    \"id\": 1731095302112\n" +
                    "  }\n" +
                    "}";
            return Response.status(Response.Status.CREATED).entity(dummyResponse).build();
        }
        if (id == 3)
        {
            String dummyResponse = "{\n" +
                    "  \"success\": false,\n" +
                    "  \"error\": {\n" +
                    "    \"code\": 400,\n" +
                    "    \"message_key\": \"request.missingarguments\"\n" +
                    "  }\n" +
                    "}";
            return Response.status(Response.Status.BAD_REQUEST).entity(dummyResponse).build();
        }
        if (id == 4)
        {
            String dummyResponse = "{\n" +
                    "  \"success\": false,\n" +
                    "  \"error\": {\n" +
                    "    \"code\": 401,\n" +
                    "    \"message_key\": \"authorization.failed\"\n" +
                    "  }\n" +
                    "}";
            return Response.status(Response.Status.UNAUTHORIZED).entity(dummyResponse).build();
        }
        if (id == 5)
        {
           String dummyResponse = "{\n" +
                   "  \"success\": false,\n" +
                   "  \"error\": {\n" +
                   "    \"code\": 403,\n" +
                   "    \"message_key\": \"user.exists\"\n" +
                   "  }\n" +
                   "}";
           return Response.status(Response.Status.FORBIDDEN).entity(dummyResponse).build();
        }
        String dummyResonse = "{\n" +
                "  \"success\": false,\n" +
                "  \"error\": {\n" +
                "    \"code\": 409,\n" +
                "    \"message_key\": \"account.exists\"\n" +
                "  }\n" +
                "}";
        return Response.status(Response.Status.CONFLICT).entity(dummyResonse).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProfile(@PathParam("id") String id) {
        String dummyResponse ;
        if (id.equals("1731095302112")) {
            dummyResponse = "{\n" +
                "  \"success\": true,\n" +
                "  \"data\": null\n" +
                "}";
            return Response.status(Response.Status.ACCEPTED).entity(dummyResponse).build();
        }
        else
        {
            dummyResponse = "{\n" +
                    "  \"success\": false,\n" +
                    "  \"error\": {\n" +
                    "    \"code\": 401,\n" +
                    "    \"message_key\": \"authorization.failed\"\n" +
                    "  }\n" +
                    "}";
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity(dummyResponse).build();
    }
}
