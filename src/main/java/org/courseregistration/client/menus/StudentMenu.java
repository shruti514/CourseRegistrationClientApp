package org.courseregistration.client.menus;

import java.util.List;
import java.util.Scanner;

import org.courseregistration.client.auth.UserContext;
import org.courseregistration.client.client.ProfessorClient;
import org.courseregistration.client.client.SectionClient;
import org.courseregistration.client.client.ServerException;
import org.courseregistration.client.client.StudentClient;
import org.courseregistration.client.model.Section;
import org.courseregistration.client.model.Student;
import org.courseregistration.client.resources.StudentResource;
import org.courseregistration.client.responses.ProfessorResponse;
import org.courseregistration.client.responses.SectionResponse;
import org.courseregistration.client.responses.StudentResponse;

public class StudentMenu {
	UserContext userContext;
	Scanner scanner = new Scanner(System.in);
	SectionClient sectionClient;
	StudentClient studentClient;
	ProfessorClient professorClient;

	public StudentMenu(UserContext userContext) {
		this.userContext = userContext;
		this.sectionClient = new SectionClient();
		this.studentClient = new StudentClient();
	}

/*	private Student getStudent() {
		if (this.userContext.isStudent()) {
			return (Student) this.userContext.getLoggedInUser();
		}
		return null;
	}*/

	public void showAllStudentsMenu() throws Exception {
		while (true) {
			System.out.println();
			System.out.println();
			System.out.printf(
					"*****************%s Student Menu********************\n",
					userContext != null ? userContext.getUsername() : "**");
			System.out.println("1. See profile");
			System.out.println("2. Update profile");
			System.out.println("3. Delete profile");
			System.out.println("4. Search for a course");
			System.out.println("5. List all professors");
			System.out.println("6. List all sections");
			System.out.println("7. Enroll for a section");
			System.out.println("8. Drop a section");
			System.out.println("9. Logout");

			String input = getUserInput();
			if (input.equalsIgnoreCase("9")) {
				userContext = null;
				return;
			} else {
				switch (input) {
				case "1":
					getStudentDetails();
					break;
				case "2":
					updateProfile();
					break;
				case "3":
					if(deleteProfile()==true)
						return;
					else
						break;
				case "4":
					searchForACourse();
					break;
				case "5":
					showListOfProfessors();
					break;
				case "6":
					listAllSections();
					break;
				case "7":
					enrollToSection();
					// Make it as student and enroll for a section.
					break;
				case "8":
					dropFromSection();
					// Make it as student and drop the section.
					break;
				default:
					System.out.println();
					System.out.println("Invalid input");
					System.out.println();
				}
			}
		}
	}

	private void dropFromSection() {
		// TODO Auto-generated method stub
		System.out.println("Yet to implement");
	}

	private void enrollToSection() {
		// TODO Auto-generated method stub
		System.out.println("Yet to implement");
	}

	private void listAllSections() throws Exception {
		try {
			studentClient.getConnection(userContext);
			Long id = userContext.getStudent().getId();
			List<Section> sections = (List<Section>) studentClient.getAllSections(id);
			for (Section section : sections) {
				System.out.println(section.toString());
			}
		} catch(ServerException e) {
			System.out.println("Sorry! Cannot get enrolled sections! Try Again.");
		}

	}

	private void showListOfProfessors() throws Exception {
		try{
			//studentClient.getConnection(userContext);
			ProfessorResponse professorResponse = professorClient.getAllProfessors();
			System.out.println("_____________________________________________");
			System.out.println();

		} catch(ServerException e){
			System.out.println("Sorry! Cannot get list of professor! Try Again.");
		}
	}

	private boolean deleteProfile() throws Exception {
		try {
			studentClient.getConnection(userContext);
			Long id = userContext.getStudent().getId();
			String studentResponse= studentClient.deleteStudent(id);
			System.out.println("_____________________________________________");
			System.out.println(studentResponse.toString());
			studentClient.closeConnection();
			return true;
		} catch (ServerException e) {
			System.out.println("Sorry! Cannot delete user. Try Again.");
			return false;
		}
	}

	private void updateProfile() {
		// TODO Auto-generated method stub
		System.out.println("Yet to implement");
	}

	private void getStudentDetails() throws Exception {
		try {
			studentClient.getConnection(userContext);
			Long id = userContext.getStudent().getId();
			StudentResponse studentResponse = studentClient.getStudent(id);
			System.out.println("____________________________________________");
			System.out.println(studentResponse.toString());
			studentClient.closeConnection();
		} catch(ServerException e) {
			System.out.println("Sorry! Couldn't fetch student details. Try Again");
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
			sectionClient.closeConection();
		} catch (ServerException e) {
			System.out.printf("\nSorry! Could not find course of Id: %s\n",
					input);
			System.out.println();
		}
	}

	private String getUserInput() {
		String input = "INVALID";
		if ((input = scanner.nextLine()) != null) {
			// && !exitCode.equalsIgnoreCase(input)) {
			return input.trim();

		} else {

			System.out.println("Invalid input - Enter again");
			getUserInput();
		}
		return input;
	}

}
