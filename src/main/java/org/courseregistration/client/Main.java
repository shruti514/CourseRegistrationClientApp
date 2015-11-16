package org.courseregistration.client;

import org.courseregistration.client.auth.TestAuthResource;
import org.courseregistration.client.client.UserClient;
import org.courseregistration.client.model.LoginResponse;

import java.util.Scanner;

public class Main {
    Scanner scanner = new Scanner(System.in);
    String exitCode = "Quit";

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

        LoginResponse loginResponse = UserClient.login(username, password);

        System.out.println("Welcome " + loginResponse.getUsername());

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
