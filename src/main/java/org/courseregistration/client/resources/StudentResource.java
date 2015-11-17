package org.courseregistration.client.resources;

import org.courseregistration.client.responses.StudentResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

public interface StudentResource {

    // 1. See logged in student profile
    @GET
    @Produces("application/json")
    @Path("/students/{id}")
    Response getStudent(@PathParam("id") int id);

    // 2. Update logged in student details
    @PUT
    @Produces("text/plain")
    @Consumes("application/json")
    @Path("/students/{id}")
    StudentResponse updateStudent(@PathParam("id") int id);

    // 3. Delete logged in student profile
    @DELETE
    @Produces("application/json")
    @Path("/students/{id}")
    StudentResponse deleteStudent(@PathParam("id") int id);

    // 4. Search for a course
    @GET
    @Produces("application/json")
    @Path("/courses/{id}")
    StudentResponse getCourseDetails();

    // 5. Search for a Professor
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/professors/{id}")
    StudentResponse getProfessorDetails(@PathParam("id") int id);

    // 6. List all sections
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/sections")
    StudentResponse getAllSections();


    // 7. Enroll for a section
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("{id}/sections/{section_id}")
    StudentResponse enrollSection(@PathParam("id") int id, @PathParam("section_id") int section_id);


    // 8. Drop a section
    @DELETE
    @Produces("application/json")
    @Path("{id}/sections/{section_id}")
    StudentResponse deleteSection(@PathParam("id") int id, @PathParam("section_id") int section_id);

}
