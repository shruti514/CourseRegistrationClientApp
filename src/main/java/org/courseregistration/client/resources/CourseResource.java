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
import javax.ws.rs.core.Response;

import org.courseregistration.client.model.Course;
import org.courseregistration.client.responses.CourseResponse;

public interface CourseResource {

    /**
     * get courses by pages
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/courses?{pageNumber}&{pageSize}")
    CourseResponse getCoursesPaginated(@PathParam("pageNumber") int pageNumber, @PathParam("pageSize") int pageSize);

    /**
     * create new course
     * @param uriInfo
     * @param course
     * @return CourseREsponse
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    CourseResponse createCourse(@PathParam("uriInfo") @Context UriInfo uriInfo, @PathParam("course") Course course);

    /**
     * delete course
     * @param id
     * @return CourseResponse
     */
    @DELETE
    @Path("/courses/{id}")
    CourseResponse deleteById(@PathParam("id") long id);

    /**
     * save courses
     * @param section
     * @return CourseResponse
     */
    @PUT
    @Path("/courses/{section}")
    CourseResponse saveCourses(@PathParam("section") Course section);

    /**
     * retrieve course
     * @param id
     * @return Response
     */
    @GET
    @Produces("application/json")
    @Path("/courses/{id}")
    Response getCourse(@PathParam("id") int id);

    /**
     * retrieve all courses
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/courses")
    Response getAllCourses();
}
