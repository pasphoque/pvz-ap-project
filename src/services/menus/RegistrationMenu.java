package services.menus;

import controllers.services.AuthenticationService;
import enums.Command;
import models.User;

import java.util.Map;

public class RegistrationMenu extends BaseMenu {
    // State variables to track the two-step registration process
    private boolean waitingForSecurityQuestion = false;
    private User pendingUser = null;

    // Hardcoded list of security questions for the user to choose from
    private final String[] securityQuestions = {
            "1. What is the name of your favorite pet?",
            "2. What city were you born in?",
            "3. What is your favorite food?"
    };

    @Override
    protected void processCommand(Command command, Map<String, String> args) {
        if (waitingForSecurityQuestion && command != Command.PICK_QUESTION && command != Command.MENU_EXIT) {
            view.displayError("You must pick a security question to complete registration.");
            return;
        }

        switch (command) {
            case REGISTER:
                String username = args.get("username");
                String password = args.get("password");
                String passwordConfirm = args.get("passwordConfirm");
                String nickname = args.get("nickname");
                String email = args.get("email");
                String gender = args.get("gender");

                if (app.getUserRepository().isUsernameTaken(username)) {
                    view.displayError("Username is already taken.");
                    return;
                }

                if (!AuthenticationService.validateEmailFormat(email)) {
                    view.displayError("Invalid email format.");
                    return;
                }

                if (!AuthenticationService.validatePasswordStrength(password)) {
                    view.displayError("Weak password. It must be at least 8 characters and include uppercase, lowercase, numbers, and special characters.");
                    return;
                }

                if (!password.equals(passwordConfirm)) {
                    view.displayError("Passwords do not match.");
                    return;
                }

                String hashedPassword = AuthenticationService.hashPassword(password);

                pendingUser = new User(username, hashedPassword, nickname, email, gender);
                waitingForSecurityQuestion = true;

                view.displaySuccess("Registration data valid. Please choose a security question:");
                for (String question : securityQuestions) {
                    view.displayMessage(question);
                }
                break;

            case PICK_QUESTION:
                if (!waitingForSecurityQuestion) {
                    view.displayError("You must register first before picking a security question.");
                    return;
                }

                int questionIndex;
                try {
                    questionIndex = Integer.parseInt(args.get("questionNumber")) - 1;
                } catch (NumberFormatException e) {
                    view.displayError("Invalid question number.");
                    return;
                }

                if (questionIndex < 0 || questionIndex >= securityQuestions.length) {
                    view.displayError("Invalid question number.");
                    return;
                }

                String answer = args.get("answer");
                String answerConfirm = args.get("answerConfirm");

                if (!answer.equals(answerConfirm)) {
                    view.displayError("Answers do not match.");
                    return;
                }

                pendingUser.setSecurityQuestion(securityQuestions[questionIndex]);
                pendingUser.setSecurityAnswer(answer);
                app.getUserRepository().addUser(pendingUser);

                view.displaySuccess("Account created successfully!");
                waitingForSecurityQuestion = false;
                pendingUser = null;

                app.changeMenuState(new MainMenu());
                break;

            case MENU_ENTER:
                if ("login".equalsIgnoreCase(args.get("menuName"))) {
                    app.changeMenuState(new LoginMenu());
                } else {
                    view.displayError("You can only access the login menu from here.");
                }
                break;

            case MENU_EXIT:
                app.exit();
                break;

            default:
                view.displayError("Command not supported in the Registration Menu.");
                break;
        }
    }
}