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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.courseregistration.client.model.CriteriaDTO;
import org.courseregistration.client.model.Section;
import org.jboss.resteasy.annotations.Form;

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
	@Produces(MediaType.TEXT_PLAIN)
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
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({ "PROFESSOR", "ADMIN" })
	public Response updateSection(@PathParam("sectionId") long id,
			Section current);

	@GET
	@Path("/sections/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findSectionsQueryParams(@Form CriteriaDTO dto);
}
