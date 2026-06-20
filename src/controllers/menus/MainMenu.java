package controllers.menus;

import enums.Command;

import java.util.Map;

public class MainMenu extends BaseMenu {
    @Override
    protected void processCommand(Command command, Map<String, String> args) {
        switch (command) {
            case MENU_ENTER:
                String menuName = args.get("menuName").toLowerCase();
                switch (menuName) {
                    case "game":
                        view.displayMessage("Transitioning to Game Menu...");
                        app.changeMenuState(new GameMenu());
                        break;
                    case "profile":
                        view.displayMessage("Transitioning to Profile Menu...");
                        break;
                    case "settings":
                        view.displayMessage("Transitioning to Settings Menu...");
                        break;
                    case "network":
                        view.displayMessage("Transitioning to Network Menu...");
                        break;
                    case "news":
                        view.displayMessage("Transitioning to News Menu...");
                        break;

                    // TODO: complete the logic here and add the actual menus

                    default:
                        view.displayError("Invalid menu name. Available menus from here:" +
                                "game, profile, settings, network, news.");
                        break;
                }
                break;

            case MENU_LOGOUT:
                app.setActiveUser(null);
                app.changeMenuState(new RegistrationMenu());
                break;

            case MENU_EXIT:
                view.displayError("Use menu logout to sign out of your account.");
                break;

            case SHOW_CURRENT_MENU:
                view.displayMessage("Main Menu");
                break;

            default:
                view.displayError("Command not supported in the Main Menu.");
                break;
        }
    }
}