package org.courseregistration.client.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.courseregistration.client.model.Course;
import org.courseregistration.client.responses.CourseResponse;

public interface CourseResource {

    @GET
    @Produces("application/json")
    @Path("/courses?{pageNumber}&{pageSize}")
    CourseResponse getCoursesPaginated(@PathParam("pageNumber") int pageNumber, @PathParam("pageSize") int pageSize);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    CourseResponse createCourse(@PathParam("uriInfo") @Context UriInfo uriInfo, @PathParam("course") Course course);

    @DELETE
    @Path("/courses/{id}")
    CourseResponse deleteById(@PathParam("id") long id);

    @PUT
    @Path("/courses/{section}")
    CourseResponse saveCourses(@PathParam("section") Course section);

    @GET
    @Produces("application/json")
    @Path("/courses/{id}")
    Course getCourse(@PathParam("id") int id);

    @GET
    @Produces("application/json")
    @Path("/courses")
    CourseResponse getAllCourses();
}
