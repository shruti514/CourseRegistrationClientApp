package org.courseregistration.client.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.courseregistration.client.responses.SectionResponse;

public interface SectionResource {

	@GET
	@Produces("application/json")
	@Path("/sections/{id}")
	SectionResponse getSection(@PathParam("id") int id);
}
