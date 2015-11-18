package org.courseregistration.client.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

public interface StudentResource {

	// 1. See logged in student profile
	@GET
	@Produces("application/json")
	@Path("/students/{id}")
	Response getStudent(@PathParam("id") Long id);

	// 6 from Main. See all students
	@GET
	@Produces("application/json")
	@Path("/students")
	Response getStudents();

	// 2. Update logged in student details
	@PUT
	@Produces("text/plain")
	@Consumes("application/json")
	@Path("/students/{id}")
	Response updateStudent(@PathParam("id") int id);

	// 3. Delete logged in student profile
	@DELETE
	@Produces("text/plain")
	@Path("/students/{id}")
	Response deleteStudent(@PathParam("id") Long id);

	// 4. Search for a course
	@GET
	@Produces("application/json")
	@Path("/courses/{id}")
	Response getCourseDetails(@PathParam("id") int id);

	// 5. Search for a Professor
	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/professors/{id}")
	Response getProfessorDetails(@PathParam("id") int id);

	// 6. List all sections for the logged in student
	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/sections/{id}")
	Response getAllSections(@PathParam("id") Long id);

	// 7. Enroll for a section
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("{id}/sections/{section_id}")
	Response enrollSection(@PathParam("id") int id,
			@PathParam("section_id") int section_id);

	// 8. Drop a section
	@DELETE
	@Produces("application/json")
	@Path("{id}/sections/{section_id}")
	Response deleteSection(@PathParam("id") int id,
			@PathParam("section_id") int section_id);

}
