package org.courseregistration.client.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

public interface SectionResource {

	@GET
	@Produces("application/json")
	@Path("/sections/{id}")
	Response getSection(@PathParam("id") int id);

	@GET
	@Produces("application/json")
	@Path("/sections")
	Response getAllSections();
}
