package org.courseregistration.client.resources;

/** Created by SHITAL on 11/13/2015.**/

import org.courseregistration.client.responses.ProfessorResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

public interface ProfessorResource {
    //  1. See logged in professor profile
    @GET
    @Produces("application/json")
    @Path("/professors/{id}")
    Response getProfessor(@PathParam("id") int id);

    //  2. Update logged in professor details
    @PUT
    @Produces("text/plain")
    @Consumes("application/json")
    @Path("professor/{id}")
    ProfessorResponse updateProfessor(@PathParam("id") int id);

    //  3. Delete logged in professor profile
    @DELETE
    @Produces("application/json")
    @Path("professor/{id}")
    ProfessorResponse deleteProfessor(@PathParam("id") int id);

    //   4. Add new course
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("{id}/courses/{course_id}")
    ProfessorResponse addNewCourse(@PathParam("id") int id, @PathParam("course_id") int course_id);

    //  5. List of all sections
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/sections")
    ProfessorResponse getAllSections();

    //  6. Search for a student
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/students/{id}")
    ProfessorResponse getStudentDetails(@PathParam("id") int id);

    //  7. Search for a Course
    @GET
    @Produces("application/json")
    @Path("/courses/{id}")
    ProfessorResponse getCourseDetails();

    //  create single professor
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/professors/{id}")
    ProfessorResponse setProfessor(@PathParam("id") int id);

    //  create multiple professors
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/professors")
    ProfessorResponse setMultipleProfessors();

    //  delete multiple professors
    @DELETE
    @Produces("application/json")
    @Path("professor/{ids}")
    ProfessorResponse deleteMultipleProfessors();

    // Display all professors list
    @GET
    @Produces("application/json")
    @Path("/professors")
    ProfessorResponse getAllProfessors();


}

