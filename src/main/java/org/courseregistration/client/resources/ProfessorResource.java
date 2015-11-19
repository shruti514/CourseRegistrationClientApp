package org.courseregistration.client.resources;

/** Created by SHITAL on 11/13/2015.**/

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

import org.courseregistration.client.model.Course;
import org.courseregistration.client.model.Professor;

public interface ProfessorResource {

	/**
	 * See logged in professor profile
	 */

	@GET
	@Produces("application/json")
	@Path("/professors/{id}")
	Response getProfessor(@PathParam("id") int id);

	/**
	 * Display all professors list
	 * @return
	 */
	@GET
	@Produces("application/json")
	@Path("/professors")
	Response getAllProfessors();

	/**
	 * Update logged in professor details
	 * @param id
	 * @param current
	 * @return
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("professors/{id}")
	@RolesAllowed({ "PROFESSOR", "ADMIN" })
	public Response updateProfessor(@PathParam("id") Long id,Professor current);

	/**
	 *  Delete logged in professor profile
	 * @param id
	 * @return
	 */
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("professors/{id}")
	@RolesAllowed({ "PROFESSOR", "ADMIN" })
	public Response deleteProfessor(@PathParam("id") Long id);

	/**
	 * Add new course
	 * @param course
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/courses/")
	@RolesAllowed({ "PROFESSOR", "ADMIN" })
	public Response addCourse(Course course);

	/**
	 * List of all sections
	 *
	 * */
	 @GET
	 @Produces("application/json")
	 @Path("/sections") Response getAllSections();


	/**
	 * // 6. Search for a student
	 * */
	 @GET
	 @Produces("application/json")
	 @Consumes("application/json")
	 @Path("/students/{id ") public Response
	   getStudentDetails(@PathParam("id") int id);


	/**
	 * // 7. Search for a Course
	 **/
	 @GET
	 @Produces("application/json")
	 @Path("/courses/{id ") public Response getCourseDetails();

	/**
	 * adds professor
	 * @param professor
	 * @return Response
	 */
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
    @Path("professors")
	public Response addProfessor(Professor professor);

	/**
	 * create multiple professors
	 * @return Response
	 */
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/professors")
	public Response setMultipleProfessors();

	/**
	 * delete multiple professor
	 * @return
	 */
	@DELETE
	@Produces("application/json")
	@Path("professor/{ids}")
	public Response deleteMultipleProfessors();

}
