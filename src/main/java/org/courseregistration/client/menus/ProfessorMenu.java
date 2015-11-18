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
import org.courseregistration.client.responses.SectionResponse;

public class ProfessorMenu {

	UserContext userContext;
	Scanner reader = new Scanner(System.in);
	SectionClient sectionClient;
	StudentClient studentClient;
	ProfessorClient professorClient;
	Professor professor;
	SectionResponse currentSectionResposne;

	/**
	 * Constructor
	 * 
	 * @param userContext
	 */
	public ProfessorMenu(UserContext userContext) {
		this.userContext = userContext;
		try {
			this.professor = UserContext.getProfessor();
		} catch (Exception e) {
			System.out.println("Professor is not looged in.");
		}
		this.sectionClient = new SectionClient();
		this.professorClient = new ProfessorClient();
	}

	/**
	 * showProfessorMenu - Show options which you can do.
	 */
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

	/**
	 * List of Sections as per professor
	 */
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

	/**
	 * Delete this profile
	 * 
	 * @return boolean
	 */
	private boolean deleteProfile() {
		try {
			professorClient.getConnection(userContext);
			Long id = UserContext.getProfessor().getId();
			String professorResponse = professorClient.deleteProfessor(id);
			System.out.println("_____________________________________________");
			System.out.println(professorResponse.toString());
			professorClient.closeConnection();
			return true;
		} catch (ServerException e) {
			System.out.println("Sorry! Cannot delete user. Try Again.");
			return false;
		} catch (Exception e) {
			System.out.println("Sorry! Cannot delete user. Try Again.");
			return false;
		}
	}

	/**
	 * update a Profile of Professor
	 */
	private void updateAProfile() {

		try {
			this.professorClient.getConnection(userContext);
			String updateMessage = this.professorClient.updateProfessor(
					professor.getId(), professor);
			System.out.println("Professor " + updateMessage
					+ " got updated successfully.");
			this.professorClient.closeConnection();
		} catch (ServerException e) {
			System.out.println("Professor Update Failed.");
		}
	}

	/**
	 * Get Professor Details
	 */
	private void getProfessorDetails() {
		System.out.println(professor.toString());
	}

	/**
	 * Show List of Students for Section
	 */
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

	/**
	 * Delete a Section by id
	 */
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

	/**
	 * Update a section by id
	 */
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

	/**
	 * Search for a course by ID
	 */
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

	/**
	 * Add new Section for Logged in professor
	 */
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

}
