package org.courseregistration.client;

import java.util.List;
import java.util.Scanner;

import org.courseregistration.client.auth.User;
import org.courseregistration.client.auth.UserContext;
import org.courseregistration.client.client.SectionClient;
import org.courseregistration.client.client.ServerException;
import org.courseregistration.client.client.StudentClient;
import org.courseregistration.client.client.UserClient;
import org.courseregistration.client.model.LoginResponse;
import org.courseregistration.client.responses.SectionResponse;

public class Main {
	Scanner scanner = new Scanner(System.in);
	String exitCode = "Quit";
	UserContext userContext = null;

	public static void main(String arg[]) {
		Main main = new Main();
		main.start();
	}

	private void start() {
		System.out.println("Welcome login to Course Registration");
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.println("3. List of courses");
		System.out.println("4. List of professors");
		System.out.println("5. List of students");
		System.out.println("6. Search for a course");
		System.out.println("7. Search for a professor");
		System.out.println("8. Search for a student");
		System.out.println("Quit. Exit from the system");

		String userInput = getUserInput();

		switch (userInput) {
		case "1":
			handleLogin();
			break;
		case "2":
			handleRegistration();
			break;
		case "3":
			showListOfCourses();
			break;
		case "4":
			showListOfProfessors();
			break;
		case "5":
			showListOfStudents();
			break;
		case "6":
			searchForACourse();
			break;
		case "7":
			searchForACourse();
			break;
		case "8":
			searchForACourse();
			break;
		case "quit":
			break;
		default:
			System.out.println("Invalid input");
		}

	}

	private void handleLogin() {
		System.out.println("Enter Username : ");
		String username = getUserInput();
		System.out.println("Enter Password : ");
		String password = getUserInput();

		try {
			LoginResponse loginResponse = UserClient.login(username, password);
			User user = loginResponse.isProfessor() ? loginResponse
					.getProfessor().getProfessor() : loginResponse.getStudent()
					.getStudent();
			userContext = UserContext.forUser(username, password, user);

			System.out.println("Welcome " + userContext.getUsername());
			if (userContext.isStudent()) {
				showStudentMenu();
			} else {
				showProfessorMenu();
			}
		} catch (ServerException error) {
			System.out
					.println("Sorry! Could not find user with given user name. Try again.");
			System.out.println();
			start();
		}

	}

	private void handleRegistration() {
		System.out.println("1 - Want to register as Student?: ");
		System.out.println("2 - Want to register as Professor?: ");

		String input = getUserInput();
		switch (input) {
		case "1":
			handleStudentRegistration();
			break;
		case "2":
			handleProfessorRegistration();
			break;
		default:
			System.out.println();
			System.out.println("Invalid input");
			System.out.println();
			start();
		}

	}

	private void showListOfCourses() {
		// show all section
		SectionClient client = new SectionClient();
		client.getConnection(null);
		try {
			SectionResponse sectionResponse = client.getAllSections();
			List<SectionResponse> contents = sectionResponse.getContent();
			for (SectionResponse content : contents) {
				System.out
						.println("__________________________________________");
				System.out.println(content.toString());
			}
			showAllCoursesMenu();
		} catch (ServerException e) {
			System.out.println("Sorry! Could not find courses.");
			System.out.println();
			start();
		}
	}

	private void showAllCoursesMenu() {
		System.out.println("select - Select a course by Id ");
		System.out.println("return - Return to main Menu ");

		String input = getUserInput();
		switch (input) {
		case "select":
			searchForACourse();
			// handleStudentRegistration();
			break;
		case "return":
			start();
			break;
		default:
			System.out.println();
			System.out.println("Invalid input");
			System.out.println();
			start();
		}
	}

	private void searchForACourse() {
		// show selected section
		SectionClient client = new SectionClient();
		client.getConnection(null);
		System.out.println("Enter the ID of Section to select: ");
		String input = getUserInput();

		try {
			int id = Integer.parseInt(input);
			SectionResponse sectionResponse = client.getSection(id);
			System.out.println("__________________________________________");
			System.out.println(sectionResponse.toString());
			showACourseMenu();
		} catch (ServerException e) {
			System.out.printf("\nSorry! Could not find course of Id: %s\n",
					input);
			System.out.println();
			start();
		}
	}

	private void showACourseMenu() {
		System.out.println("register - Register for the Course ");
		System.out.println("unregister -  Unregister for the Course ");
		System.out.println("edit - Edit the Course ");
		System.out.println("delete -  Delete the Course ");
		System.out.println("return - Return to main menu");

		String input = getUserInput();
		switch (input) {
		case "register":
			if (userContext == null) {
				System.out
						.println("Please Login as a Student. Follow the Login Menu");
				handleLogin();
			}
			// registerToACourse(); //TODO Student can register to the course in
			// this method
			break;
		case "unregister":
			if (userContext == null) {
				System.out
						.println("Please Login as a Student. Follow the Login Menu");
				handleLogin();
			}
			// unRegisterToACourse(); //TODO Student can unregister to the
			// course in
			// this method
			break;
		case "edit":
			if (userContext == null) {
				System.out
						.println("Please Login as a Professor or Admin. Follow the Login Menu");
				handleLogin();
			}
			// editCourseDetails(); //TODO Professor or admin can edit the
			// course;
			break;
		case "delete":
			if (userContext == null) {
				System.out
						.println("Please Login as a Professor or Admin. Follow the Login Menu");
				handleLogin();
			}
			// deleteCourseDetails(); //TODO Professor or admin can delete the
			// course;
			break;
		case "return":
			start();
			break;
		default:
			System.out.println();
			System.out.println("Invalid input");
			System.out.println();
			start();
		}
	}

	private void showListOfStudents() {
		// TODO Auto-generated method stub
		StudentClient.getAllStudents();
	}

	private void showListOfProfessors() {
		// TODO
	}

	private void handleProfessorRegistration() {
		// TODO
	}

	private void handleStudentRegistration() {
		// TODO
	}

	private void showProfessorMenu() {
		System.out.println();
		System.out.println("1. See profile");
		System.out.println("2. Update profile");
		System.out.println("3. Delete profile");
		System.out.println("4. List of all sections");
	}

	private void showStudentMenu() {
		System.out.println();
		System.out.println("1. See profile");
		System.out.println("2. Update profile");
		System.out.println("3. Delete profile");
		System.out.println("4. List of all sections");
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
