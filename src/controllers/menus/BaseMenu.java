package controllers.menus;

import enums.Command;
import enums.CommandParser;
import models.Application;
import views.ConsoleView;
import views.OutputView;

import java.util.Map;

public abstract class BaseMenu implements MenuState {
    protected Application app;
    protected CommandParser parser;
    protected OutputView view;

    public BaseMenu() {
        this.parser = new CommandParser();
        this.view = new ConsoleView();
    }

    @Override
    public void enterState(Application app) {
        this.app = app;
        view.displayMessage("--- " + this.getClass().getSimpleName() + " ---");
    }

    @Override
    public void exitState(Application app) {
    }

    @Override
    public void handleInput(String input) {
        Command command = parser.parseCommand(input);
        if (command == Command.UNKNOWN) {
            view.displayError("Invalid command format.");
            return;
        }

        Map<String, String> args = parser.extractArguments(command, input);
        processCommand(command, args);
    }

    protected abstract void processCommand(Command cmd, Map<String, String> args);
}