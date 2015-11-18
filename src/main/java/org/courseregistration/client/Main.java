package org.courseregistration.client;

import java.io.Console;
import java.util.List;
import java.util.Scanner;

import org.courseregistration.client.auth.User;
import org.courseregistration.client.auth.UserContext;
import org.courseregistration.client.client.ProfessorClient;
import org.courseregistration.client.client.SectionClient;
import org.courseregistration.client.client.ServerException;
import org.courseregistration.client.client.StudentClient;
import org.courseregistration.client.client.UserClient;
import org.courseregistration.client.menus.ProfessorMenu;
import org.courseregistration.client.menus.StudentMenu;
import org.courseregistration.client.model.LoginResponse;
import org.courseregistration.client.model.Professor;
import org.courseregistration.client.model.Student;
import org.courseregistration.client.responses.ProfessorResponse;
import org.courseregistration.client.responses.SectionResponse;
import org.courseregistration.client.responses.StudentResponse;

public class Main {
	Scanner scanner = new Scanner(System.in);

	String exitCode = "Quit";
	UserContext userContext = null;

	SectionClient sectionClient;
	StudentClient studentClient;
	// CourseClient courseClient;
	ProfessorClient professorClient;

	SectionResponse currentSectionResposne;

	public static void main(String arg[]) throws Exception {
		Main main = new Main();

		main.sectionClient = new SectionClient();
		main.studentClient = new StudentClient();
		// main.courseClient = new CourseClient();
		main.professorClient = new ProfessorClient();
		main.currentSectionResposne = null;

		main.start();
	}

	private void start() throws Exception {
		while (true) {
			System.out.println("\n\nWelcome login to Course Registration");
			System.out.println("1. Login");
			System.out.println("2. Register");

			System.out.println("3. List of courses");
			System.out.println("4. Search for a course");

			System.out.println("5. List of professors");
			System.out.println("6. List of students");

			System.out.println("Quit. Exit from the system");

			String userInput = getUserInput();
			if (!userInput.equalsIgnoreCase("quit")) {
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
					searchForACourse();
					break;
				case "5":
					showListOfProfessors();
					break;
				case "6":
					showListOfStudents();
					break;
				default:
					System.out.println("Invalid input");
				}
			} else {
				break;
			}
		}
	}

	private void handleLogin() throws Exception {
		System.out.println("Enter Username : ");
		String username = getUserInput();
		System.out.println("Enter Password : ");
		String password = getUserInput();
		// String password = getPassword();

		try {
			LoginResponse loginResponse = UserClient.login(username, password);
			User user = loginResponse.isProfessor() ? loginResponse
					.getProfessor().getProfessor() : loginResponse.getStudent()
					.getStudent();
			userContext = UserContext.forUser(username, password, user);

			System.out.println("Welcome " + userContext.getUsername());
			if (UserContext.isStudent()) {
				StudentMenu studMenu = new StudentMenu(userContext);
				studMenu.showAllStudentsMenu();
			} else {
				ProfessorMenu profMenu = new ProfessorMenu(userContext);
				profMenu.showProfessorMenu();
			}
		} catch (ServerException error) {
			System.out
					.println("Sorry! Could not find user with given user name. Try again.");
			System.out.println();
		}

	}

	private void handleRegistration() {
		System.out.println("1 - Want to register as Student? ");
		System.out.println("2 - Want to register as Professor? ");

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
		}
	}

	private void handleProfessorRegistration() {
		try {
			this.professorClient.getConnection(null);
			String professorResponse = this.professorClient
					.addProfessor();
			System.out
					.println("______________________________________________________");
			System.out.println(professorResponse);
			this.professorClient.closeConnection();
		} catch (Exception e) {
			System.out.println("Sorry! Could not create professor.");
			e.printStackTrace();
			System.out.println();
		}
	}

	private void handleStudentRegistration() {
		try {
			this.studentClient.getConnection(null);
			StudentResponse studentResponse = this.studentClient.addStudent();
			System.out.println("_______________________________________________________");
			System.out.println(studentResponse.toString());
			this.studentClient.closeConnection();
		} catch (Exception e) {
			System.out.println("Sorry! Could not create student. Try Again");
			e.printStackTrace();
			System.out.println();
		}
	}

	private void showListOfCourses() {
		try {
			sectionClient.getConnection(userContext);
			SectionResponse sectionResponse = sectionClient.getAllSections();
			List<SectionResponse> contents = sectionResponse.getContent();
			for (SectionResponse content : contents) {
				System.out
						.println("__________________________________________");
				System.out.println(content.toString());
			}
			sectionClient.closeConection();

		} catch (ServerException e) {
			System.out.println("Sorry! Could not find courses.");
			System.out.println();
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

	private void showListOfStudents() {
		try {
			this.studentClient.getConnection(null);
			StudentResponse studentResponse = this.studentClient
					.getAllStudents();
			List<StudentResponse> contents = studentResponse.getContent();
			if (contents.size() > 0) {
				System.out.println();
				System.out.println("List of All Students");
				System.out
						.println("______________________________________________________");
				int counter = 1;

				for (StudentResponse content : contents) {
					Student student = content.getStudent();
					System.out.println("\t" + counter + ": "
							+ student.getFirstName() + " "
							+ student.getLastName());
					counter++;
				}
			} else {
				System.out
						.println("______________________________________________________");
				System.out.println("Oops!! No Students in the system.");
			}
			this.studentClient.closeConnection();

		} catch (Exception e) {
			System.out.println("Sorry! Could not find students.");
			e.printStackTrace();
			System.out.println();

		}
	}

	private void showListOfProfessors() {
		try {
			this.professorClient.getConnection(null);
			ProfessorResponse professorResponse = this.professorClient
					.getAllProfessors();
			List<ProfessorResponse> contents = professorResponse.getContent();
			if (contents.size() > 0) {
				System.out.println();
				System.out.println("List of All Professors");
				System.out
						.println("______________________________________________________");
				int counter = 1;

				for (ProfessorResponse content : contents) {
					Professor professor = content.getProfessor();
					System.out.println("\t" + counter + ": "
							+ professor.getFirstName() + " "
							+ professor.getLastName());
					counter++;
				}
			} else {
				System.out
						.println("______________________________________________________");
				System.out.println("Oops!! No Professors in the system.");
			}
			this.professorClient.closeConnection();

		} catch (Exception e) {
			System.out.println("Sorry! Could not find students.");
			e.printStackTrace();
			System.out.println();
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

	private String getPassword() {
		String password = "";
		Console console = System.console();
		if (console == null) {
			System.out.println("Couldn't get Console instance");
			System.exit(0);
		}
		if (console != null) {
			char passwordArray[] = console.readPassword();
			if (passwordArray.toString().trim().isEmpty()) {
				System.out.println("Invalid input - Enter again");
				getPassword();
			}

			return new String(passwordArray).trim();
		}
		return password;
	}

}
