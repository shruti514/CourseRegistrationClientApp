package org.courseregistration.client.menus;

import java.util.List;
import java.util.Scanner;

import org.courseregistration.client.auth.UserContext;
import org.courseregistration.client.client.ProfessorClient;
import org.courseregistration.client.client.SectionClient;
import org.courseregistration.client.client.ServerException;
import org.courseregistration.client.client.StudentClient;
import org.courseregistration.client.model.CriteriaDTO;
import org.courseregistration.client.model.Professor;
import org.courseregistration.client.model.Student;
import org.courseregistration.client.resources.ProfessorResource;
import org.courseregistration.client.responses.SectionResponse;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public class ProfessorMenu {

    private ProfessorResource proxy = null;
    private ResteasyWebTarget target = null;

    UserContext userContext;
	Scanner reader = new Scanner(System.in);
	SectionClient sectionClient;
    StudentClient studentClient;
    ProfessorClient professorClient;
	Professor professor;
	SectionResponse currentSectionResposne;

	public ProfessorMenu(UserContext userContext) {
		this.userContext = userContext;
		this.professor = getProfessor();
		this.sectionClient = new SectionClient();
	}

	private Professor getProfessor() {
		if (UserContext.isProfessor()) {
			return (Professor) this.userContext.getLoggedInUser();
		}
		return null;
	}

	public void showProfessorMenu() {

		while (true) {
			System.out.println();
			System.out.println();
			System.out.printf(
					"*****************%s Professor Menu********************\n",
					userContext != null ? userContext.getUsername() : "**");
			System.out.println("1. See profile");
			System.out.println("2. Update profile");
			System.out.println("3. Delete profile");
			System.out.println("4. Add new Course");
			System.out.println("5. Update Course");
			System.out.println("6. Delete Course");
			System.out.println("7. List of all Sections");
			System.out.println("8. List of all Student for a Section");
			System.out.println("9. Search for a Course");
			System.out.println("10. Logout");

			String input = getUserInput();
			if (input.equalsIgnoreCase("10")) {
				userContext = null;
				break;
			} else {
				switch (input) {
				case "1":
					getProfessorDetails();
					break;
				case "2":
					updateAProfile();
					break;
				case "3":
					deleteProfile();
					break;
				case "4":
					addNewCourse();
					break;
				case "5":
					updateSection();
					break;
				case "6":
					deleteSection();
					break;
				case "7":
					listOfSections();
					break;
				case "8":
					showListOfStudentsForSection();
					break;
				case "9":
					searchForACourse();
					break;
				default:
					System.out.println();
					System.out.println("Invalid input");
					System.out.println();
				}
			}
		}
	}

	private void listOfSections() {
		CriteriaDTO dto = new CriteriaDTO();
		dto.setLastname(this.professor.getLastName());
		try {
			sectionClient.getConnection(userContext);
			SectionResponse sectionResponse = sectionClient
					.getSectionBySearch(dto);
			System.out.println("__________________________________________");
			System.out.println(sectionResponse.toString());
			sectionClient.closeConection();
		} catch (ServerException e) {
			System.out.printf(
					"\nSorry! Could not find courses for Professor %s\n",
					this.professor.getLastName());
			System.out.println();
		}
	}

	private boolean deleteProfile() throws Exception{
        try {
            professorClient.getConnection(userContext);
            Long id = userContext.getProfessor().getId();
            String professorResponse = professorClient.deleteProfessor(id);
            System.out.println("_____________________________________________");
            System.out.println(professorResponse.toString());
            professorClient.closeConection();
            return true;
        } catch (ServerException e) {
            System.out.println("Sorry! Cannot delete user. Try Again.");
            return false;
        }
	}

	private String updateAProfile(Professor current)
        throws ServerException {
            Professor professor = updateFormProfessor(current);

            if (professor != null) {
                professor.setLinks(null);
                Response response = proxy.updateProfessor(current);
                if (response.getStatus() == 200) {
                    System.out.println(response.toString());
                    return response.readEntity(String.class);
                }
                throwNewException(response);
            }
            return null;
    }

    private Professor updateFormProfessor(Professor professor) {

        try {
            System.out.println();
            System.out
                    .println("___________________________________________________________________");
            System.out.println("Professor update form");
            System.out
                    .println("___________________________________________________________________");
            System.out.println("Please enter values for fields to update: ");
            String input = "";

            System.out.println("Email-Id: [ " + professor.getEmailId() + " ]:");
            input = (reader.nextLine());
            if (!input.trim().isEmpty())
                professor.setEmailId(input);

            System.out.println("Phone Number [ " + professor.getPhoneNumber()
                    + " ]:");
            input = (reader.nextLine());
            if (!input.trim().isEmpty())
              professor.setPhoneNumber(input);

            System.out.println("Faculty Type [ " + professor.getFacultyType()
                    + " ]:");
            input = (reader.nextLine());
            if (!input.trim().isEmpty())
                professor.setFacultyType(input);

            System.out.println("Bio [ " + professor.getBio()
                    + " ]:");
            input = (reader.nextLine());
            if (!input.trim().isEmpty())
                professor.setBio(input);

            System.out.println("Years of Experience : [ " + professor.getYearsOfExperience()
                    + " ]:");
            input = (reader.nextLine());
            if (!input.trim().isEmpty())
                professor.setYearsOfExperience(Integer.parseInt(input));


            System.out.println("Do you want to Submit update? [y:n]: ");
            if (reader.nextLine().equalsIgnoreCase("y")) {
                System.out.println("You are about to Update above fields.");
                return professor;
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

	private void getProfessorDetails() {
		System.out.println(professor.toString());
	}

	private void showListOfStudentsForSection() {

		// TODO - List is not populating check JSONIGNORE in model on Server
		// side
		try {
			searchForACourse();
			List<Student> students = this.currentSectionResposne.getSection()
					.getStudent();
			if (students != null && students.size() > 0) {
				System.out.println();
				System.out.println("List of Students for course "
						+ this.currentSectionResposne.getSection().getCourse()
								.getName());
				System.out
						.println("______________________________________________________");
				int counter = 1;
				for (Student student : students) {
					System.out.println("\t" + counter + ": "
							+ student.getFirstName() + " "
							+ student.getLastName());
				}
			} else {
				System.out
						.println("______________________________________________________");
				System.out.println("Course with section "
						+ this.currentSectionResposne.getSection().getId()
						+ " does not have any students enrolled.");
			}
		} catch (Exception e) {
			System.out.println("Course with section "
					+ this.currentSectionResposne.getSection().getId()
					+ " does not exist or have any students enrolled.");
			e.printStackTrace();
		}
	}

	private void deleteSection() {
		try {
			System.out.println("Enter the ID of Section to Delete: ");
			String input = getUserInput();

			this.sectionClient.getConnection(userContext);
			String deleteMessage = this.sectionClient.deleteSection(Long
					.parseLong(input));
			System.out.println("Section " + deleteMessage
					+ " got deleted successfully.");
			this.sectionClient.closeConection();
		} catch (ServerException e) {
			System.out.println("Course with section "
					+ this.currentSectionResposne.getSection().getId()
					+ " could not be deleted.");
			e.printStackTrace();
		}
	}

	private void updateSection() {
		try {
			searchForACourse();
			if (currentSectionResposne != null) {
				this.sectionClient.getConnection(userContext);
				String updateMessage = this.sectionClient.updateSection(
						this.currentSectionResposne.getSection().getId(),
						this.currentSectionResposne.getSection());
				System.out.println("Section " + updateMessage
						+ " got updated successfully.");
				this.sectionClient.closeConection();
			}
		} catch (ServerException e) {
			System.out.println("Course with section "
					+ this.currentSectionResposne.getSection().getId()
					+ " could not be updated.");
			e.printStackTrace();
		}
	}

	private void searchForACourse() {
		System.out.println("Enter the ID of Section to select: ");
		String input = getUserInput();

		try {
			sectionClient.getConnection(userContext);
			int id = Integer.parseInt(input);
			SectionResponse sectionResponse = sectionClient.getSection(id);
			System.out.println("__________________________________________");
			System.out.println(sectionResponse.toString());
			this.currentSectionResposne = sectionResponse;
			sectionClient.closeConection();
		} catch (ServerException e) {
			System.out.printf("\nSorry! Could not find course of Id: %s\n",
                    input);
			System.out.println();
		}
	}

	private void addNewCourse() {
		try {
			sectionClient.getConnection(userContext);
			System.out.println(professor.toString());
			String sectionResponse = sectionClient.addSection(professor);
			System.out.println("CREATE Section: " + sectionResponse);
			sectionClient.closeConection();
		} catch (ServerException e) {
			System.out
					.printf("\nSorry! Could not Add a new Section to the Database\n");
			System.out.println();
			e.printStackTrace();
		}
	}

	private String getUserInput() {
		String input = "INVALID";
		if ((input = reader.nextLine()) != null) {
			return input.trim();

		} else {

			System.out.println("Invalid input - Enter again");
			getUserInput();
		}
		return input;
	}
    private void throwNewException(Response response) throws ServerException {
        String errorResponse = response.readEntity(String.class);
        target.getResteasyClient().close();
        System.out.println("Error:" + response.getStatus() + errorResponse);
        throw new ServerException(errorResponse);
    }

}
