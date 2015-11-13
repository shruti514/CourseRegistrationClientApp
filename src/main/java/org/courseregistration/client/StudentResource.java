package org.courseregistration.client;

import org.courseregistration.client.student.StudentResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


public interface StudentResource {

    @GET
    @Produces("application/json")
    @Path("/students/{id}")
    StudentResponse getStudent(@PathParam("id") int id);
}
