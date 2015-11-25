package org.courseregistration.client.menus;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import org.courseregistration.client.auth.UserContext;
import org.courseregistration.client.client.ProfessorClient;
import org.courseregistration.client.client.SectionClient;
import org.courseregistration.client.client.ServerException;
import org.courseregistration.client.client.StudentClient;
import org.courseregistration.client.model.CriteriaDTO;
import org.courseregistration.client.model.Section;
import org.courseregistration.client.model.Student;
import org.courseregistration.client.responses.ProfessorResponse;
import org.courseregistration.client.responses.SectionResponse;
import org.courseregistration.client.responses.StudentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentMenu {
    private static final Logger logger = LoggerFactory.getLogger(StudentMenu.class);
	UserContext userContext;
	Scanner scanner = new Scanner(System.in);
	SectionClient sectionClient;
	StudentClient studentClient;
	ProfessorClient professorClient;

	/**
	 * Constructor
	 * @param userContext
	 */
	public StudentMenu(UserContext userContext) {
		this.userContext = userContext;
		this.sectionClient = new SectionClient();
		this.studentClient = new StudentClient();
	}

	/**
	 * view all students menu
	 * @throws Exception
	 */
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
			System.out.println("9. Custom Search a Section");
			System.out.println("10. Logout");

			String input = getUserInput();
			if (input.equalsIgnoreCase("10")) {
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
					if (deleteProfile() == true)
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
				case "9":
					customSearchSection();
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
	 * Custom search for section
	 */
	private void customSearchSection() {
		try {
			CriteriaDTO dto = sectionClient.createDTOForm();
			if (dto != null) {
				sectionClient.getConnection(userContext);
				SectionResponse sectionResponse = sectionClient
						.getSectionBySearch(dto);
				System.out
						.println("__________________________________________");
				System.out.println(sectionResponse.toString());
				sectionClient.closeConection();
			} else {
				System.out.println();
				System.out
						.println("Sorry! Search is canclled or wrong values inserted in search.");
			}
		} catch (ServerException e) {
            logger.error("Error:"+ e.getMessage());
			System.out.println();
			System.out.println("Sorry! Could not find courses for this Search");
		}
	}

	/**
	 * drop from section
	 */
	private void dropFromSection() {
		try {
            studentClient.getConnection(userContext);
            StudentResponse studentResponse = studentClient.getStudent(UserContext.getStudent().getId());
            List<Section> sections = studentResponse.getStudent().getSections();
            if(sections.size() < 1) {
                System.out.println("Sorry! currently you are not enrolled for any course.");
                return;
            }
            System.out.println("Your courses : ");
            Map<Integer,Section> indexToSection = Maps.newHashMap();
			for(int i=0;i<sections.size();i++){
				Section section = sections.get(i);
				System.out.println("Press "+(i+1)+" to drop course - " + section.getCourse().getName()+" by "+
						section.getProfessor().getFirstName() + " "+section.getProfessor().getLastName());
                System.out.println();
				indexToSection.put((i+1),section);
			}

			String input = getUserInput();

			Section section = indexToSection.get(Integer.valueOf(input));

			this.studentClient.getConnection(userContext);
            String response = this.studentClient.deleteSection(UserContext.getStudent().getId(), section.getId());
            System.out.println();
            System.out.println(response);
        } catch(Exception e) {
            logger.error("Error:"+ e.getMessage());
			System.out.println("Sorry! Could not drop section");
		}
	}


	/**
	 * enroll to section
	 */
	private void enrollToSection() {
		try{
            sectionClient.getConnection(userContext);
            SectionResponse allSections = sectionClient.getAllSections();

            Map<Integer,Section> indexToSection = Maps.newHashMap();
            List<SectionResponse> content = allSections.getContent();
            int i = 1;
            for(SectionResponse sectionResponse : content) {
                Section section = sectionResponse.getSection();
                System.out.println("Press "+i+" to enroll for \""+section.getCourse().getName()+"\" by "+ section.getProfessor().getFirstName()+" "+section.getProfessor().getLastName());
                System.out.println("\t [ Total Capacity: "+section.getTotalCapacity()+"]");
                System.out.println("\t [ Wait List Capacity: "+section.getWaitListCapacity()+"]");
                System.out.println("\t [ Total Number Of Enrolled Students: "+section.getNumberOfEnrolledStudents()+"]");
                System.out.println("\t [ Time: "+section.getClassStartTimeForView()+" - "+section.getClassEndTimeForView()+"]");
                System.out.println("\t [ Day Of the Week: "+section.getDayOfWeek()+"]");
                System.out.println("\t [ Price: $"+section.getPrice()+"]");
                indexToSection.put(i++, section);
                System.out.println();
            }

            String input = getUserInput();

            if(indexToSection.containsKey(Integer.valueOf(input))){
                Section section = indexToSection.get(Integer.valueOf(input));
                studentClient.getConnection(userContext);
                String response = studentClient.enrollStudent(UserContext.getStudent().getId(), section.getId());
                System.out.println();
                System.out.println(response);
            }else{
                System.out.println("Invalid Input");
                return;
            }

        } catch(Exception e) {
            logger.error("Error:"+ e.getMessage());
			System.out.println("Sorry! Could not enroll to section");
		}
	}

	/**
	 * list all sections
	 * @throws Exception
	 */
	private void listAllSections() throws Exception {
		try {
			studentClient.getConnection(userContext);
			Long id = UserContext.getStudent().getId();
			StudentResponse studentResponse = studentClient.getAllSections(id);
            if(studentResponse.getStudent().getSections().size()<1){
                System.out.println("Currently you are not registered for any course.");
            }
			for (Section section : studentResponse.getStudent().getSections()) {
				System.out.println(section.toString());
			}
		} catch (ServerException e) {
            logger.error("Error:"+ e.getMessage());
			System.out
					.println("Sorry! Cannot get enrolled sections! Try Again.");
		}

	}

	/**
	 * view all professors
	 * @throws Exception
	 */
	private void showListOfProfessors() throws Exception {
		try {
			// studentClient.getConnection(userContext);
			ProfessorResponse professorResponse = professorClient
					.getAllProfessors();
			System.out.println("_____________________________________________");
			System.out.println();

		} catch (ServerException e) {
            logger.error("Error:"+ e.getMessage());
			System.out
					.println("Sorry! Cannot get list of professor! Try Again.");
		}
	}

	/**
	 * delete student profile
	 * @return
	 * @throws Exception
	 */
	private boolean deleteProfile() throws Exception {
		try {
			studentClient.getConnection(userContext);
			Long id = userContext.getStudent().getId();
			String studentResponse = studentClient.deleteStudent(id);
			System.out.println("_____________________________________________");
			System.out.println(studentResponse.toString());
			studentClient.closeConnection();
			return true;
		} catch (ServerException e) {
            logger.error("Error:"+ e.getMessage());
			System.out.println("Sorry! Cannot delete user. Try Again.");
			return false;
		}
	}

	/**
	 * update student profile
	 */
	private void updateProfile() {
        try {
            studentClient.getConnection(userContext);
            Long id = userContext.getStudent().getId();
            StudentResponse studentResponse = studentClient.getStudent(id);

            String responseMessage = studentClient.updateStudent(id,studentResponse.getStudent());
            System.out.println(responseMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }

	}

	/**
	 * retrieve student details
	 * @throws Exception
	 */
	private void getStudentDetails() throws Exception {
		try {
			studentClient.getConnection(userContext);
			Long id = userContext.getStudent().getId();
			StudentResponse studentResponse = studentClient.getStudent(id);
			System.out.println("____________________________________________");
			System.out.println(studentResponse.toString());
			studentClient.closeConnection();
		} catch (ServerException e) {
            logger.error("Error:"+ e.getMessage());
			System.out
					.println("Sorry! Couldn't fetch student details. Try Again");
		}
	}

	/**
	 * search for course
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
			sectionClient.closeConection();
		} catch (ServerException e) {
            logger.error("Error:"+ e.getMessage());
			System.out.printf("\nSorry! Could not find course of Id: %s\n",
					input);
			System.out.println();
		}
	}

	/**
	 * get input from user
	 * @return
	 */
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
