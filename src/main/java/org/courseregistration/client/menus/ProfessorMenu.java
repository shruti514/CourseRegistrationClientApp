package org.courseregistration.client.menus;

import java.util.Scanner;

import org.courseregistration.client.auth.UserContext;
import org.courseregistration.client.client.SectionClient;
import org.courseregistration.client.client.ServerException;
import org.courseregistration.client.model.Professor;
import org.courseregistration.client.responses.SectionResponse;

public class ProfessorMenu {

	UserContext userContext;
	Scanner scanner = new Scanner(System.in);
	SectionClient sectionClient;
	Professor professor;
	SectionResponse currentSectionResposne;

	public ProfessorMenu(UserContext userContext) {
		this.userContext = userContext;
		this.professor = getProfessor();
		this.sectionClient = new SectionClient();
	}

	private Professor getProfessor() {
		if (this.userContext.isProfessor()) {
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
			System.out.println("7. List of all sections");
			System.out.println("8. Search for a student");
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
					// Make it as Professor and list all sections
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

	private void deleteProfile() {
		// TODO Auto-generated method stub
		System.out.println("Yet to implement");
	}

	private void updateAProfile() {
		// TODO Auto-generated method stub
		System.out.println("Yet to implement");
	}

	private void getProfessorDetails() {
		// TODO
		System.out.println("Yet to implement");
	}

	private void showListOfStudentsForSection() {
		// TODO Auto-generated method stub
		System.out.println("Yet to implement");
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
			SectionResponse sectionResponse = sectionClient
					.addSection(professor);
			System.out.println("__________________________________________");
			System.out.println(sectionResponse.toString());
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
		if ((input = scanner.nextLine()) != null) {
			return input.trim();

		} else {

			System.out.println("Invalid input - Enter again");
			getUserInput();
		}
		return input;
	}
}
