package org.courseregistration.client;

import org.courseregistration.client.auth.TestAuthResource;
import org.courseregistration.client.auth.User;
import org.courseregistration.client.auth.UserContext;
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
        TestAuthResource.test();
    }


    private void start() {
        System.out.println("Welcome login to Course Registration");
        System.out.println("Press 1 - to login");
        System.out.println("Press 2 - to register");

        String userInput = getUserInput();

        switch (userInput) {
            case "1":
                handleLogin();
                break;
            case "2":
                handleRegistration();
                break;
            default:
                System.out.println("Invalid input");
        }

    }

    private void handleRegistration() {

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
        }catch (ServerException error){
            System.out.println("Sorry! Could not find user with given user name. Try again.");
            System.out.println();
            start();
        }

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
