package org.courseregistration.client.resources;

import org.courseregistration.client.model.Student;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface StudentResource {

	/**
	 * add a student
	 * @param student
	 * @return Response
	 */
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
	@Path("students")
    public Response addStudent(Student student);

	/**
	 * See logged in student profile
	 * @param id
	 * @return Response
	 */
	@GET
	@Produces("application/json")
	@Path("/students/{id}")
	Response getStudent(@PathParam("id") Long id);

	/**
	 *  See all students
	 * @return Response
	 */
	@GET
	@Produces("application/json")
	@Path("/students")
	Response getAllStudents(@QueryParam("page")  int page,
							@QueryParam("size")  int size);

	/**
	 * update student
	 * @param id
	 * @param student
	 * @return Response
	 */
	@PUT
	@Produces("text/plain")
	@Consumes("application/json")
	@Path("/students/{id}")
	Response updateStudent(@PathParam("id") long id, Student student);

	/**
	 * Delete logged in student profile
	 * @param id
	 * @return Response
	 */
	@DELETE
	@Produces("text/plain")
	@Path("/students/{id}")
	Response deleteStudent(@PathParam("id") Long id);

	/**
	 * Search for a course
	 * @param id
	 * @return Response
	 */
	@GET
	@Produces("application/json")
	@Path("/courses/{id}")
	Response getCourseDetails(@PathParam("id") long id);

	/**
	 * Search for a Professor
	 * @param id
	 * @return Response
	 */
	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/professors/{id}")
	Response getProfessorDetails(@PathParam("id") long id);

	/**
	 * // List all sections for the logged in student
	 * @param id
	 * @return Response
	 */
	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/sections/{id}")
	Response getAllSections(@PathParam("id") Long id);

	/**
	 * Enroll Student
	 * @param id
	 * @param section_id
	 * @return Response
	 */
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("students/{id}/sections/{section_id}")
	Response enrollStudent(@PathParam("id") long id,
			@PathParam("section_id") long section_id);

	/**
	 * Drop a section
	 * @param id
	 * @return Response
	 */
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("students/{id}/sections/{section_id}")
	Response deleteSection(@PathParam("id") long id, @PathParam ("section_id") long section_id);

}
