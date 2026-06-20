package controllers.menus;

import controllers.services.AuthenticationService;
import enums.Command;
import models.User;

import java.util.Map;

public class LoginMenu extends BaseMenu {
    @Override
    protected void processCommand(Command command, Map<String, String> args) {
        switch (command) {
            case LOGIN:
                String username = args.get("username");
                String password = args.get("password");

                User user = app.getUserRepository().getUserByUsername(username);

                if (user == null) {
                    view.displayError("Username not found.");
                    return;
                }

                String hashedInput = AuthenticationService.hashPassword(password);
                if (!user.getPasswordHash().equals(hashedInput)) {
                    view.displayError("Incorrect password.");
                    return;
                }

                app.setActiveUser(user);
                view.displaySuccess("Welcome back, " + user.getNickname() + "!");
                app.changeMenuState(new MainMenu());

                break;

            case FORGET_PASSWORD:
                String fpUsername = args.get("username");
                String fpEmail = args.get("email");
                String fpAnswer = args.get("answer");

                User fpUser = app.getUserRepository().getUserByUsername(fpUsername);

                if (fpUser == null || !fpUser.getEmail().equals(fpEmail)) {
                    view.displayError("Invalid username or email.");
                    return;
                }

                if (!fpUser.getSecurityAnswer().equals(fpAnswer)) {
                    view.displayError("Incorrect security answer.");
                    return;
                }

                view.displaySuccess("Security answer correct! Password recovery verified.");
                // TODO: prompt the user for a new password here.
                break;

            case MENU_ENTER:
                if ("registration".equalsIgnoreCase(args.get("menuName"))) {
                    app.changeMenuState(new RegistrationMenu());
                } else {
                    view.displayError("You can only access the registration menu from here.");
                }
                break;

            case MENU_EXIT:
                app.changeMenuState(new RegistrationMenu());
                break;

            case SHOW_CURRENT_MENU:
                view.displayMessage("Login Menu");
                break;

            default:
                view.displayError("Command not supported in the Login Menu.");
                break;
        }
    }
}