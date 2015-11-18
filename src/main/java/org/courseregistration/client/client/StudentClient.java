package org.courseregistration.client.client;

import java.sql.Date;
import java.util.Scanner;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.courseregistration.client.HttpClientFactory;
import org.courseregistration.client.auth.UserContext;
import org.courseregistration.client.filter.StudentEtagFilter;
import org.courseregistration.client.model.Student;
import org.courseregistration.client.resources.ProfessorResource;
import org.courseregistration.client.resources.StudentResource;
import org.courseregistration.client.responses.StudentResponse;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class StudentClient {

    private StudentResource studentResource = null;
    private ProfessorResource professorResource = null;
    private ResteasyWebTarget target = null;
    //keeping this just for etags
    private StudentWithHeaders currentStudent;

	Scanner reader = new Scanner(System.in);

	public void getConnection(UserContext userContext) throws ServerException {
		if (userContext != null) {
			target = HttpClientFactory.getWebTarget(userContext.getUsername(),
					userContext.getPassword());
		} else {
			target = HttpClientFactory.getWebTargetForAnonymousUser();
		}
		studentResource = target.proxy(StudentResource.class);
	}

	public void closeConnection() {
		try {
			if (!target.getResteasyClient().isClosed())
				target.getResteasyClient().close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

    //1. See Profile
    public StudentResponse getStudent(Long id) throws ServerException {
        target.register(new StudentEtagFilter(currentStudent));
        Response response = studentResource.getStudent(id);
         if(response.getStatus() == 200) {
             this.currentStudent = StudentWithHeaders.getStudentWithHeaders(response);
             return currentStudent.getCurrent();
         }if(response.getStatus() == 304){
            return currentStudent.getCurrent();
        }

		throwNewException(response);
		return null;
	}

	// 2. Update Profile
	public StudentResponse updateStudent(@PathParam("id")long id, Student current) throws ServerException {
		Student student = updateFormStudent(current);

        if(student!=null) {
            student.setLink(null);
            Response response = studentResource.updateStudent(id, student);
            if (response.getStatus() == 200) {
                System.out.println(response.toString());
                return response.readEntity(StudentResponse.class);
            }
            throwNewException(response);
        }
		return null;
	}
    // 2. Update Profile
    public String updateStudent(int id) throws ServerException {
        target.register(new StudentEtagFilter(currentStudent));
        Response response = studentResource.updateStudent(id);
        if(response.getStatus() == 200) {
            return response.readEntity(String.class);
        }if(response.getStatus() == 412){
            return "Other user has changed this student details simultaneously. Please try updating again!";
        }
        throwNewException(response);
                return null;
    }

	// 3. Delete Profile
	public String deleteStudent(Long id) throws ServerException {
		Response response = studentResource.deleteStudent(id);
		if (response.getStatus() == 200) {
			return response.readEntity(String.class);
		}

		throwNewException(response);
		return null;

	}

	// 4. Search for a course
	public StudentResponse getCourseDetails(int id) throws ServerException {
		Response response = studentResource.getCourseDetails(id);
		if (response.getStatus() == 200) {
			return response.readEntity(StudentResponse.class);
		}

		throwNewException(response);
		return null;
	}

	// 5. Search for Professor
	public StudentResponse getAllProfessorDetails() throws ServerException {
		Response response = professorResource.getAllProfessors();
		if (response.getStatus() == 200) {
			return response.readEntity(StudentResponse.class);
		}

		throwNewException(response);
		return null;
	}

    // Main Menu: 6. Get all students
	public StudentResponse getAllStudents() throws ServerException {
		Response response = studentResource.getAllStudents();
		if (response.getStatus() == 200) {
			return response.readEntity(StudentResponse.class);
		}

		throwNewException(response);
		return null;
	}

    // 6. List all sections for the logged in student
    public StudentResponse getAllSections(Long id) throws ServerException {
        Response response = studentResource.getAllSections(id);
        if (response.getStatus() == 200) {
            return response.readEntity(StudentResponse.class);
        }

        throwNewException(response);
        return null;
    }

	// 7. Enroll to a student
	public StudentResponse enrollStudent(int id, int student_id)
			throws ServerException {
		Response response = studentResource.enrollStudent(id, student_id);
		if (response.getStatus() == 200) {
			return response.readEntity(StudentResponse.class);
		}

		throwNewException(response);
		return null;
	}

	// 8. Drop a student
	public StudentResponse deleteStudent(long id)
			throws ServerException {
		Response response = studentResource.deleteStudent(id);
		if (response.getStatus() == 200) {
			return response.readEntity(StudentResponse.class);
		}

		throwNewException(response);
		return null;
	}

    private Student updateFormStudent(Student student) {

        try {

            System.out.println();
            System.out
                    .println("___________________________________________________________________");
            System.out.println("Student update form");
            System.out
                    .println("___________________________________________________________________");
            System.out.println("Please enter values for fields to update: ");
            String input = "";

            System.out.println("Username: [ " + student.getUsername() + " ]:");
            input = (reader.nextLine());
            if (!input.trim().isEmpty())
                student.setUsername(input);

            System.out.println("Admission Type: [ " + student.getAdmissionType()
                    + " ]:");
            input = (reader.nextLine());
            if (!input.trim().isEmpty())
                student.setAdmissionType(input);

            System.out.println("Date of Birth: [ " + student.getDateOfBirth() + " ]:");
            input = (reader.nextLine());
            if (!input.trim().isEmpty())
                student.setDateOfBirth(Date.valueOf(input));

            System.out.println("Email Id: [ "
                    + student.getEmailId() + " ]:");
            input = (reader.nextLine());
            if (!input.trim().isEmpty())
                student.setEmailId(input);

            System.out.println("First Name: [ " + student.getFirstName()
                    + " ]:");
            input = (reader.nextLine());
            if (!input.trim().isEmpty())
                student.setFirstName(input);

            System.out.println("Last Name: [ " + student.getLastName()
                    + " ]:");
            input = (reader.nextLine());
            if (!input.trim().isEmpty())
                student.setLastName(input);

            System.out.println("Middle Name: [ " + student.getMiddleName()
                    + " ]:");
            input = (reader.nextLine());
            if (!input.trim().isEmpty())
                student.setMiddleName(input);

            System.out.println("Phone Number: [ "
                    + student.getPhoneNumber() + " ]:");
            input = (reader.nextLine());
            if (!input.trim().isEmpty())
                student.setPhoneNumber(input);

            System.out.println("Previous Degree: [ " + student.getPreviousDegree()
                    + " ]:");
            input = (reader.nextLine());
            if (!input.trim().isEmpty())
                student.setPreviousDegree((input));

            
            System.out.println("Do you want to Submit update? [y:n]: ");
            if (reader.nextLine().equalsIgnoreCase("y")) {
                System.out.println("You are about to Update above fields.");
                return student;
            } else {
                System.out.println("Successfully Cancelled.");
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out
                .println("You are not able to update as some values are either empty or not set properly.");
        return null;
    }


	private void throwNewException(Response response) throws ServerException {
		String errorResponse = response.readEntity(String.class);
		target.getResteasyClient().close();
		System.out.println("Error:" + response.getStatus() + errorResponse);
		throw new ServerException(errorResponse);
	}


}
