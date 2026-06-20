package models;

import controllers.services.menus.MenuState;
import controllers.services.menus.RegistrationMenu;
import models.repositories.UserRepository;

import java.util.Scanner;

public class Application {
    private MenuState currentMenuState;
    private final Scanner scanner;
    private boolean isRunning;

    private UserRepository userRepository;
    private User activeUser;

    public Application() {
        this.scanner = new Scanner(System.in);
        this.isRunning = true;
        this.userRepository = new UserRepository();
    }

    public void run() {
        changeMenuState(new RegistrationMenu());

        while (isRunning) {
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                if (currentMenuState != null) {
                    currentMenuState.handleInput(input);
                }
            }
        }
        scanner.close();
    }

    public void changeMenuState(MenuState newState) {
        if (currentMenuState != null) {
            currentMenuState.exitState(this);
        }

        currentMenuState = newState;
        if (currentMenuState != null) {
            currentMenuState.enterState(this);
        }
    }

    public void exit() {
        this.isRunning = false;
        System.out.println("Exiting the application. BYE BYE!!!!!");
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }
}