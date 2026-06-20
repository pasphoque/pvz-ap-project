package controllers.services.menus;

import models.Application;

public interface MenuState {
    void enterState(Application app);
    void exitState(Application app);
    void handleInput(String input);
}
