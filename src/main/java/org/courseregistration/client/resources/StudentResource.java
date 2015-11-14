package org.courseregistration.client.resources;

import javax.ws.rs.*;

import org.courseregistration.client.responses.StudentResponse;

public interface StudentResource {

	// get single student
	@GET
	@Produces("application/json")
	@Path("/students/{id}")
	StudentResponse getStudent(@PathParam("id") int id);

	// get all students
	@GET
	@Produces("application/json")
	@Path("/students")
	StudentResponse getAllStudents();

	// create single student
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/students/{id}")
	StudentResponse setStudent(@PathParam("id") int id);

	// create multiple students
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/students/list")
	StudentResponse setAllStudents();

	// delete student
	@DELETE
	@Produces("application/json")
	@Path("/students/{id}")
	StudentResponse deleteStudent(@PathParam("id") int id);

	// update student details
	@PUT
	@Produces("text/plain")
	@Consumes("application/json")
	@Path("/students/{id}")
	StudentResponse updateStudent(@PathParam("id") int id);


	// enroll to a section
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("{id}/sections/{section_id}")
	StudentResponse enrollSection(@PathParam("id") Long id,@PathParam("section_id") Long section_id);


	// delete a section
	@POST
	@Produces("application/json")
	@Path("{id}/sections/{section_id}")
	StudentResponse deleteSection(@PathParam("id") Long id, @PathParam("section_id") Long section_id);

}
