package org.courseregistration.client.resources;

/**
 * Created by SHITAL on 11/13/2015.
 **/

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.courseregistration.client.responses.ProfessorResponse;
import org.courseregistration.client.responses.StudentResponse;

public interface ProfessorResource {
    @GET
    @Produces("application/json")
    @Path("/professors/{id}")
    ProfessorResponse getProfessor(@PathParam("id") int id);
}
