package controllers.menus;

import enums.Command;
import models.GameEngine;
import java.util.Map;

public class GameMenu extends BaseMenu {

    @Override
    public void enterState(models.Application app) {
        super.enterState(app);
        // If we don't have an active game engine, initialize one
        if (app.getCurrentGameEngine() == null) {
            app.setCurrentGameEngine(new GameEngine(app.getActiveUser()));
            view.displayMessage("Game Initialized!");
        }
    }

    @Override
    protected void processCommand(Command command, Map<String, String> args) {
        GameEngine engine = app.getCurrentGameEngine();

        switch (command) {
            case ADVANCE_TIME:
                int ticks = Integer.parseInt(args.get("ticks"));
                engine.advanceTime(ticks);
                view.displaySuccess(ticks + " ticks have passed. Current time: " + engine.getCurrentTicks() + " ticks.");
                break;

            case MENU_EXIT:
                // Back to main menu
                app.changeMenuState(new MainMenu());
                break;

            case SHOW_CURRENT_MENU:
                view.displayMessage("Game Menu");
                break;

            default:
                view.displayError("Command not supported in the Game Menu.");
                break;
        }
    }
}