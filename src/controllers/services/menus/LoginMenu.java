package controllers.services.menus;

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

            default:
                view.displayError("Command not supported in the Login Menu.");
                break;
        }
    }
}