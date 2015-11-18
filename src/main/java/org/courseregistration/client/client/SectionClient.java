package org.courseregistration.client.client;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.courseregistration.client.HttpClientFactory;
import org.courseregistration.client.auth.UserContext;
import org.courseregistration.client.filter.SectionEtagFilter;
import org.courseregistration.client.model.Course;
import org.courseregistration.client.model.CriteriaDTO;
import org.courseregistration.client.model.Professor;
import org.courseregistration.client.model.Section;
import org.courseregistration.client.resources.SectionResource;
import org.courseregistration.client.responses.CourseResponse;
import org.courseregistration.client.responses.SectionResponse;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class SectionClient {

	private SectionResource proxy = null;
	private ResteasyWebTarget target = null;

	Scanner reader = new Scanner(System.in);
	private UserContext userContext;
    private SectionWithHeaders currentSection;

	/**
	 * Gets connection according to user context
	 * 
	 * @param userContext
	 * @throws ServerException
	 */
	public void getConnection(UserContext userContext) throws ServerException {
		this.userContext = userContext;
		if (userContext != null) {
			target = HttpClientFactory.getWebTarget(userContext.getUsername(),
					userContext.getPassword());
		} else {
			target = HttpClientFactory.getWebTargetForAnonymousUser();
		}
		proxy = target.proxy(SectionResource.class);
	}

	/**
	 * Close Connection
	 */
	public void closeConection() {
		try {
			if (!target.getResteasyClient().isClosed())
				target.getResteasyClient().close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Get Section by ID - no need of user context
	 * 
	 * @param id
	 * @return SectionResponse
	 * @throws ServerException
	 */
	public SectionResponse getSection(int id) throws ServerException {
        target.register(new SectionEtagFilter(currentSection));
        Response response = proxy.getSection(id);
        if (response.getStatus() == 200) {
            this.currentSection = SectionWithHeaders.getSectionWithHeaders(response);
            return currentSection.getCurrent();
        }if(response.getStatus() == 304){
            return currentSection.getCurrent();
        }
        throwNewException(response);
		return null;
	}

	/**
	 * Get all sections - no need of user context
	 * 
	 * @return SectionResponse
	 * @throws ServerException
	 */
	public SectionResponse getAllSections() throws ServerException {
		Response response = proxy.getAllSections();

		if (response.getStatus() == 200) {
			return response.readEntity(SectionResponse.class);
		}

		throwNewException(response);
		return null;
	}

	/**
	 * Professor or Admin can add a section(Course) into the system
	 * 
	 * @param p
	 * @return String
	 * @throws ServerException
	 */
	public String addSection(Professor p) throws ServerException {

		Section section = registrationForm(p);

		if (section != null) {
			System.out
					.println("*******************************************************");
			System.out.println(section.toString());
			System.out
					.println("*******************************************************");
			Response response = proxy.addSection(section);
			if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
				return response.readEntity(String.class);
			}
			throwNewException(response);
		}
		return null;
	}

	/**
	 * Delete Section by ID - Professor role required
	 * 
	 * @param id
	 * @return String
	 * @throws ServerException
	 */
	public String deleteSection(Long id) throws ServerException {
		Response response = proxy.deleteSection(id);
		if (response.getStatus() == 200) {
			return response.readEntity(String.class);
		}

		throwNewException(response);
		return null;
	}

	/**
	 * Update given Section by ID
	 * 
	 * @param id
	 * @param current
	 * @return String
	 * @throws ServerException
	 */
	public String updateSection(@PathParam("sectionId") long id, Section current)
			throws ServerException {
		Section section = updateFormSection(current);

		if (section != null) {
			section.setLinks(null);
			section.getProfessor().setLink(null);
            target.register(new SectionEtagFilter(currentSection));
			Response response = proxy.updateSection(id, section);
			if (response.getStatus() == 200) {
				System.out.println(response.toString());
				return "Section " + response.readEntity(String.class)
                        + " got updated successfully.";
			}
            if(response.getStatus() == 412){
                return "Other user has changed the section simultaneously. Please try updating again!";
            }
			throwNewException(response);
		}
		return null;
	}

	/**
	 * Update form for Section object
	 * 
	 * @param section
	 * @return Section
	 */
	private Section updateFormSection(Section section) {

		try {

			System.out.println();
			System.out
					.println("___________________________________________________________________");
			System.out.println("Section update form");
			System.out
					.println("___________________________________________________________________");
			System.out.println("Please enter values for fields to update: ");
			String input = "";

			System.out.println("Price: [ " + section.getPrice() + " ]:");
			input = (reader.nextLine());
			if (!input.trim().isEmpty())
				section.setPrice(Integer.parseInt(input));

			System.out.println("Day of Week: [ " + section.getDayOfWeek()
					+ " ]:");
			input = (reader.nextLine());
			if (!input.trim().isEmpty())
				section.setDayOfWeek(input);

			System.out.println("Semester: [ " + section.getSemester() + " ]:");
			input = (reader.nextLine());
			if (!input.trim().isEmpty())
				section.setSemester(input);

			System.out.println("Class Start Time: [ "
					+ section.getClassStartTime() + " ]:");
			input = (reader.nextLine());
			if (!input.trim().isEmpty())
				section.setClassStartTime(input);

			System.out.println("Class End Time: [ " + section.getClassEndTime()
					+ " ]:");
			input = (reader.nextLine());
			if (!input.trim().isEmpty())
				section.setClassEndTime(input);

			System.out.println("Class Start Date: [ " + section.getStartDate()
					+ " ]:");
			input = (reader.nextLine());
			if (!input.trim().isEmpty())
				section.setStartDate(Date.valueOf(input));

			System.out.println("Class End Date: [ " + section.getEndDate()
					+ " ]:");
			input = (reader.nextLine());
			if (!input.trim().isEmpty())
				section.setEndDate(Date.valueOf(input));

			System.out.println("Mode of Instruction: [ "
					+ section.getModeOfInstruction() + " ]:");
			input = (reader.nextLine());
			if (!input.trim().isEmpty())
				section.setModeOfInstruction(input);

			System.out.println("Room Number: [ " + section.getRoomNumber()
					+ " ]:");
			input = (reader.nextLine());
			if (!input.trim().isEmpty())
				section.setRoomNumber((input));

			System.out.println("Total Capacity: [ "
					+ section.getTotalCapacity() + " ]:");
			input = (reader.nextLine());
			if (!input.trim().isEmpty())
				section.setTotalCapacity(Integer.parseInt(input));

			System.out.println("Wait List Capacity: [ "
					+ section.getWaitListCapacity() + " ]:");
			input = (reader.nextLine());
			if (!input.trim().isEmpty())
				section.setWaitListCapacity(Integer.parseInt(input));

			System.out.println("Do you want to Submit update? [y:n]: ");
			if (reader.nextLine().equalsIgnoreCase("y")) {
				System.out.println("You are about to Update above fields.");
				return section;
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

	/**
	 * Get Section by search criteria
	 * 
	 * @param dto
	 * @return SectionResponse
	 * @throws ServerException
	 */
	public SectionResponse getSectionBySearch(CriteriaDTO dto)
			throws ServerException {
		Response response = proxy.findSectionsQueryParams(dto);
		if (response.getStatus() == 200) {
			return response.readEntity(SectionResponse.class);
		}

		throwNewException(response);
		return null;
	}

	/**
	 * Registration form for adding section(course)
	 * 
	 * @param p
	 * @return Section
	 */
	private Section registrationForm(Professor p) {

		try {

			Section section = new Section();
			System.out.println();
			System.out
					.println("___________________________________________________________________");
			System.out.println("Section registration form");
			System.out
					.println("___________________________________________________________________");
			System.out.println("Please enter details: ");

			section.setProfessor(p);

			section.setCourse(getCourse());

			section.setStudent(null);

			System.out.println("Semester: ");
			section.setSemester(reader.nextLine());

			System.out.println("Class Start Time [hh:MM:ss]: ");
			section.setClassStartTime(reader.nextLine());

			System.out.println("Class End Time [hh:MM:ss]: ");
			section.setClassEndTime(reader.nextLine());

			System.out.println("Day of week: ");
			section.setDayOfWeek(reader.nextLine());

			System.out.println("Class Start Date [yyyy-mm-dd]: ");
			section.setStartDate(Date.valueOf(reader.nextLine()));

			System.out.println("Class End Date [yyyy-mm-dd]: ");
			section.setEndDate(Date.valueOf(reader.nextLine()));

			section.setNumberOfEnrolledStudents(0);

			System.out.println("Room Number: ");
			section.setRoomNumber(reader.nextLine());

			System.out.println("Total Capacity: ");
			section.setTotalCapacity(Integer.parseInt(reader.nextLine()));

			System.out.println("Wait List Capacity: ");
			section.setWaitListCapacity(Integer.parseInt(reader.nextLine()));

			System.out.println("Mode of Instruction: ");
			section.setModeOfInstruction(reader.nextLine());

			System.out.println("Price: ");
			section.setPrice(Integer.parseInt(reader.nextLine()));

			System.out.println("Do you want to Submit registration? [y:n]: ");
			if (reader.nextLine().equalsIgnoreCase("y")) {
				return section;
			} else {
				System.out.println("Successfully Cancelled.");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out
				.println("You are not registered as some values are either empty or not set properly.");
		return null;
	}

	/**
	 * Get course
	 * 
	 * @return Select course from list of courses
	 */
	private Course getCourse() {
		try {

			CourseClient client = new CourseClient();
			client.getConnection(userContext);
			CourseResponse response = client.getAllCourses();
			List<CourseResponse> courses = response.getContent();
			StringBuilder sb = new StringBuilder();
			sb.append("Select a Course ID [ ");
			for (CourseResponse content : courses) {
				sb.append(content.getCourse().getId() + ",");
			}
			sb.replace(sb.lastIndexOf(","), sb.length(), "]: ");

			System.out.println(sb.toString());
			String input = reader.nextLine();

			for (CourseResponse content : courses) {
				if (content.getCourse().getId() == Long.parseLong(input)) {
					System.out.println(content.getCourse().toString());
					return content.getCourse();
				}
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Course is not created properly");
		}
		return null;
	}

	/**
	 * Create a search criteria
	 * 
	 * @return CriteriaDTO
	 */
	public CriteriaDTO createDTOForm() {
		CriteriaDTO dto = new CriteriaDTO();
		try {

			System.out.println();
			System.out
					.println("___________________________________________________________________");
			System.out.println("Search Criteria form");
			System.out
					.println("___________________________________________________________________");
			System.out.println("Just enter to skip the criteria");
			String input = "";

			System.out.println("Course Name: ");
			input = (reader.nextLine());
			if (!input.trim().isEmpty())
				dto.setCoursename(input);

			System.out.println("Professor Last Name: ");
			input = (reader.nextLine());
			if (!input.trim().isEmpty())
				dto.setLastname(input);

			System.out.println("Price: ");
			input = (reader.nextLine());
			if (!input.trim().isEmpty())
				dto.setPrice(Integer.parseInt(input));

			System.out.println("Greater than Price: ");
			input = (reader.nextLine());
			if (!input.trim().isEmpty())
				dto.setGteprice(Integer.parseInt(input));

			System.out.println("Less than Price: ");
			input = (reader.nextLine());
			if (!input.trim().isEmpty())
				dto.setLteprice(Integer.parseInt(input));

			System.out.println("Day of Week: ");
			input = (reader.nextLine());
			if (!input.trim().isEmpty())
				dto.setDayofweek(input);

			System.out.println("Semester: ");
			input = (reader.nextLine());
			if (!input.trim().isEmpty())
				dto.setSemester(input);

			System.out.println("Course code: ");
			input = (reader.nextLine());
			if (!input.trim().isEmpty())
				dto.setCoursecode(input);

			System.out.println("Do you want to Submit Search? [y:n]: ");
			if (reader.nextLine().equalsIgnoreCase("y")) {
				return dto;
			} else {
				System.out.println("Successfully Cancelled.");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void throwNewException(Response response) throws ServerException {
		String errorResponse = response.readEntity(String.class);
		target.getResteasyClient().close();
		System.out.println("Error:" + response.getStatus() + errorResponse);
		throw new ServerException(errorResponse);
	}

}
