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
import org.courseregistration.client.model.LoginResponse;
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

	public static void main(String arg[]) {
		Main main = new Main();

		main.sectionClient = new SectionClient();
		main.studentClient = new StudentClient();
		// main.courseClient = new CourseClient();
		main.professorClient = new ProfessorClient();
		main.currentSectionResposne = null;

		main.start();
	}

	private void start() {
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
				case "8":
					searchForACourse();
					break;
				case "9":
					searchForACourse();
					break;
				default:
					System.out.println("Invalid input");
				}
			} else {
				break;
				// System.exit(0);
			}
		}
	}

	private void handleLogin() {
		System.out.println("Enter Username : ");
		String username = getUserInput();
		System.out.println("Enter Password : ");
		String password = getUserInput();
		//String password = getPassword();

		try {
			LoginResponse loginResponse = UserClient.login(username, password);
			User user = loginResponse.isProfessor() ? loginResponse
					.getProfessor().getProfessor() : loginResponse.getStudent()
					.getStudent();
			userContext = UserContext.forUser(username, password, user);

			System.out.println("Welcome " + userContext.getUsername());
			if (userContext.isStudent()) {
				showAllStudentsMenu();
			} else {
				showProfessorMenu();
			}
		} catch (ServerException error) {
			System.out
					.println("Sorry! Could not find user with given user name. Try again.");
			System.out.println();
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
		}

		return;
	}

	private void handleProfessorRegistration() {
		// TODO Post Method call for Professor registration
	}

	private void handleStudentRegistration() {
		// TODO Post Method call for Student registration

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
			showAllCoursesMenu();
			sectionClient.closeConection();

		} catch (ServerException e) {
			System.out.println("Sorry! Could not find courses.");
			System.out.println();
		}
	}

	private void showAllCoursesMenu() {
		while (true) {
			System.out.println();
			System.out.println();
			System.out.println("select - Select a course by Id ");
			System.out.println("return - Return to main Menu ");

			String input = getUserInput();
			if (input.equalsIgnoreCase("return")) {
				break;
			} else {
				switch (input) {
				case "select":
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

	private void addNewCourse() {
		try {
			sectionClient.getConnection(userContext);
			SectionResponse sectionResponse = sectionClient.addSection();
			System.out.println("__________________________________________");
			System.out.println(sectionResponse.toString());
			this.currentSectionResposne = sectionResponse;
			sectionClient.closeConection();
		} catch (ServerException e) {
			System.out
					.printf("\nSorry! Could not Add a new Section to the Database\n");
			System.out.println();
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
			if (userContext != null)
				showACourseMenu();
		} catch (ServerException e) {
			System.out.printf("\nSorry! Could not find course of Id: %s\n",
					input);
			System.out.println();
		}
	}

	private void showACourseMenu() {
		while (true) {
			System.out.println();
			System.out.println();
			System.out.println("register - Register for the Course ");
			System.out.println("unregister -  Unregister for the Course ");
			System.out.println("show course -  Show Course details");
			System.out.println("edit - Edit the Course ");
			System.out.println("delete -  Delete the Course ");
			System.out.println("return - Return to main menu");

			String input = getUserInput();
			if (input.equalsIgnoreCase("return")) {
				break;
				// return;
			}
			if (!input.equalsIgnoreCase("return")) {
				switch (input) {
				case "register":
					if (userContext == null) {
						System.out
								.println("Please Login as a Student. Follow the Login Menu");
						handleLogin();
					}
					// registerToACourse(); //TODO Student can register to the
					// course in
					// this method
					break;
				case "unregister":
					if (userContext == null) {
						System.out
								.println("Please Login as a Student. Follow the Login Menu");
						handleLogin();
					}
					// unRegisterToACourse(); //TODO Student can unregister to
					// the
					// course in
					// this method
					break;
				case "show course":
					// showCourseDetails(); //TODO Show course details
					break;
				case "edit":
					if (userContext == null) {
						System.out
								.println("Please Login as a Professor or Admin. Follow the Login Menu");
						handleLogin();
					}
					try {
						this.sectionClient.getConnection(userContext);
						this.sectionClient.updateSection(
								this.currentSectionResposne.getSection()
										.getId(), this.currentSectionResposne
										.getSection());
						this.sectionClient.closeConection();
					} catch (ServerException e) {
						System.out.println("Course with section "
								+ this.currentSectionResposne.getSection()
										.getId() + " could not be updated.");
						e.printStackTrace();
					}
					break;
				case "delete":
					if (userContext == null) {
						System.out
								.println("Please Login as a Professor or Admin. Follow the Login Menu");
						handleLogin();
					}
					try {
						this.sectionClient.getConnection(userContext);
						this.sectionClient
								.deleteSection(this.currentSectionResposne
										.getSection().getId());
						this.sectionClient.closeConection();
					} catch (ServerException e) {
						System.out.println("Course with section "
								+ this.currentSectionResposne.getSection()
										.getId() + " could not be deleted.");
						e.printStackTrace();
					}
					break;
				default:
					System.out.println();
					System.out.println("Invalid input");
					System.out.println();
				}
				// showACourseMenu();
			}
		}
	}

	private void showListOfStudents() {
		// TODO Auto-generated method stub

		try {
			StudentResponse studentResponse = null;
			// studentResponse = StudentClient.getAllStudents();
			List<StudentResponse> contents = studentResponse.getContent();
			for (StudentResponse content : contents) {
				System.out
						.println("__________________________________________");
				System.out.println(content.toString());
			}

		} catch (Exception e) {
			System.out.println("Sorry! Could not find students.");
			System.out.println();
		}
	}

	private void showAllStudentsMenu() {
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
			System.out.println("5. Search for a professor");
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
					searchForAStudent(); // Select a student by ID
					break;
				case "2":

					if (userContext == null) {
						System.out
								.println("Please Login as a Student. Follow the Login Menu");
						handleLogin();
					}
					// updateAProfile(userContext.getLoggedUser()) ;//TODO
					// Student
					// can
					// update his profile
					break;
				case "3":

					if (userContext == null) {
						System.out
								.println("Please Login as a Student. Follow the Login Menu");
						handleLogin();
					}
					// deleteAProfile(userContext.getLoggedUser()) ;//TODO
					// Student
					// can
					// delete his profile
					break;
				case "4":
					searchForACourse();
					break;
				case "5":
					showListOfProfessors();
					break;
				case "6":
					if (userContext != null) {
						userContext.getLoggedInUser();
						// Make it as student and list all sections
					}
					break;
				case "7":
					if (userContext != null) {
						userContext.getLoggedInUser();
						// Make it as student and enroll for a section.
					}
					break;
				case "8":
					if (userContext != null) {
						userContext.getLoggedInUser();
						// Make it as student and drop the section.
					}
					break;
				default:
					System.out.println();
					System.out.println("Invalid input");
					System.out.println();
				}
			}
		}
	}

	private void searchForAStudent() {
		// TODO Auto-generated method stub
		studentClient.getStudent();
	}

	private void showListOfProfessors() {
		// TODO Auto-generated method stub
		try {
			ProfessorResponse professorResponse = null;
			List<ProfessorResponse> contents = professorResponse.getContent();
			for (ProfessorResponse content : contents) {
				System.out
						.println("__________________________________________");
				System.out.println(content.toString());
			}
		} catch (Exception e) {
			System.out.println("Sorry! Could not find Professors.");
			System.out.println();
		}
	}

	private void showProfessorMenu() {

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
			System.out.println("5. List of all sections");
			System.out.println("6. Search for a student");
			System.out.println("7. Search for a Course");
			System.out.println("8. Logout");

			String input = getUserInput();
			if (input.equalsIgnoreCase("8")) {
				userContext = null;
				break;
			} else {
				switch (input) {
				case "1":
					searchForAProfessor(); // Select a student by ID
					break;
				case "2":

					if (userContext == null) {
						System.out
								.println("Please Login as a Professor. Follow the Login Menu");
						handleLogin();
					}
					// updateAProfile(userContext.getLoggedUser()) ;//TODO
					// Professor
					// can
					// update his profile
					break;
				case "3":

					if (userContext == null) {
						System.out
								.println("Please Login as a Professor. Follow the Login Menu");
						handleLogin();
					}
					// deleteAProfile(userContext.getLoggedUser()) ;//TODO
					// Professor
					// can
					// delete his profile
					break;
				case "4":
					if (userContext != null) {
						userContext.getLoggedInUser();
						addNewCourse();
					}
					break;

				case "5":
					if (userContext != null) {
						userContext.getLoggedInUser();
						// Make it as Professor and list all sections
					}
					break;
				case "6":
					showListOfStudents();
					break;
				case "7":
					searchForACourse();
					break;
				default:
					System.out.println();
					System.out.println("Invalid input");
					System.out.println();
				}
				// showProfessorMenu();
			}
		}
	}

	private void searchForAProfessor() {
		// TODO Auto-generated method stub
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
