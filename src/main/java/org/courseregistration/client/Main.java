package org.courseregistration.client;

import org.courseregistration.client.auth.User;
import org.courseregistration.client.auth.UserContext;
import org.courseregistration.client.client.SectionClient;
import org.courseregistration.client.client.ServerException;
import org.courseregistration.client.client.UserClient;
import org.courseregistration.client.model.LoginResponse;

import java.util.Scanner;

public class Main {
    Scanner scanner = new Scanner(System.in);
    String exitCode = "Quit";
    UserContext userContext = null;

    public static void main(String arg[]) {
        Main main = new Main();
        main.start();
        //TestAuthResource.test();
    }


    private void start() {
        System.out.println("Welcome login to Course Registration");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. List of courses");
        System.out.println("4. List of professors");
        System.out.println("5. Search for a course");

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
                searchForACourse();
                break;
            default:
                System.out.println("Invalid input");
        }

    }

    private void searchForACourse() {
        //TODO
    }

    private void showListOfProfessors() {
        //TODO
    }

    private void showListOfCourses() {
        //show all section

        SectionClient.getAllSections();
    }

    private void handleRegistration() {
        System.out.println("1 - Want to register as Student?: ");
        System.out.println("2 - Want to register as Professor?: ");

        String input = getUserInput();
        switch (input){
            case "1":handleStudentRegistration();break;
            case "2":handleProfessorRegistration();break;
            default:
                System.out.println();
                System.out.println("Invalid input");
                System.out.println();
                start();
        }

    }

    private void handleProfessorRegistration() {
        //TODO
    }

    private void handleStudentRegistration() {
        //TODO
    }

    private void handleLogin() {
        System.out.println("Enter Username : ");
        String username = getUserInput();
        System.out.println("Enter Password : ");
        String password = getUserInput();

        try {
            LoginResponse loginResponse = UserClient.login(username, password);
            User user  = loginResponse.isProfessor()?loginResponse.getProfessor().getProfessor():loginResponse.getStudent().getStudent();
            userContext = UserContext.forUser(username,password,user);

            System.out.println("Welcome " + userContext.getUsername());
            if(userContext.isStudent()){
                showStudentMenu();
            }else{
                showProfessorMenu();
            }
        }catch (ServerException error){
            System.out.println("Sorry! Could not find user with given user name. Try again.");
            System.out.println();
            start();
        }

    }

    private void showProfessorMenu() {
        System.out.println();
        System.out.println("1. See profile");
        System.out.println("2. Update profile");
        System.out.println("3. Delete profile");
        System.out.println("4. List of all sections");
    }

    private void showStudentMenu() {

    }

    private String getUserInput() {
        String input = "INVALID";
        if ((input = scanner.nextLine()) != null
                && !exitCode.equalsIgnoreCase(input)) {
            return input.trim();

        } else {

            System.out.println("Invalid input - Enter again");
            getUserInput();
        }
        return input;
    }


}
