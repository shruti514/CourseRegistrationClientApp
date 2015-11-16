package org.courseregistration.client.resources;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.courseregistration.client.model.Section;

public interface SectionResource {

	@GET
	@Produces("application/json")
	@Path("/sections/{id}")
	Response getSection(@PathParam("id") int id);

	@GET
	@Produces("application/json")
	@Path("/sections")
	Response getAllSections();

	@POST
	@Path("/sections/")
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({ "PROFESSOR", "ADMIN" })
	public Response addSection(Section section);

	@DELETE
	@Path("/sections/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({ "PROFESSOR", "ADMIN" })
	public Response deleteSection(@PathParam("id") Long section_id);

	@PUT
	@Path("/sections/{sectionId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({ "PROFESSOR", "ADMIN" })
	public Response updateSection(@PathParam("sectionId") long id,
			Section current);

	@GET
	@Path("/sections/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findSectionsQueryParams(
			@QueryParam("coursename") String coursename,
			@QueryParam("lastname") String lastname,
			@QueryParam("price") int price,
			@QueryParam("gteprice") int gteprice,
			@QueryParam("lteprice") int lteprice,
			@QueryParam("dayofweek") String dayofweek,
			@QueryParam("semester") String semester,
			@QueryParam("coursecode") String coursecode,
			@Context UriInfo uriInfo);
}
