package org.courseregistration.client.resources;

/** Created by SHITAL on 11/13/2015.**/

import org.courseregistration.client.model.Course;
import org.courseregistration.client.model.Professor;
import org.courseregistration.client.responses.ProfessorResponse;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface ProfessorResource {

    //-- 1. See logged in professor profile
    @GET
    @Produces("application/json")
    @Path("/professors/{id}")
    Response getProfessor(@PathParam("id") int id);

    //-- Display all professors list
    @GET
    @Produces("application/json")
    @Path("/professors")
    Response getAllProfessors();

    // -- 2. Update logged in professor details
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("professor/{id}")
    @RolesAllowed({"PROFESSOR","ADMIN"})
    public Response updateProfessor(Professor current);

    // -- 3. Delete logged in professor profile
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("professor/{id}")
    @RolesAllowed({"PROFESSOR","ADMIN"})
    public Response deleteProfessor(@PathParam("id") Long id);

   // --  4. Add new course
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/courses/")
    @RolesAllowed({"PROFESSOR","ADMIN"})
    public Response addCourse(Course course);

  /**  //  5. List of all sections
    @GET
    @Produces("application/json")
    @Path("/sections")
    Response getAllSections();**/

   /** //  6. Search for a student
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/students/{id}")
    public Response getStudentDetails(@PathParam("id") int id);   **/

   /** //  7. Search for a Course
    @GET
    @Produces("application/json")
    @Path("/courses/{id}")
    public Response getCourseDetails();    **/

    //  create single professor
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/professors/")
    @RolesAllowed({ "PROFESSOR", "ADMIN" })
    public Response setProfessor(Professor professor);

    //  create multiple professors
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/professors")
    public Response setMultipleProfessors();

    //  delete multiple professors
    @DELETE
    @Produces("application/json")
    @Path("professor/{ids}")
    public Response deleteMultipleProfessors();

}

