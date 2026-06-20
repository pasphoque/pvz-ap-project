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
                printBoard();
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

    private void printBoard() {
        GameEngine engine = app.getCurrentGameEngine();
        models.grid.Board board = engine.getBoard();

        System.out.println("\n=======================================================");
        System.out.println("  Time: " + engine.getCurrentTicks() + " ticks  |  Sun: " + engine.getCurrentSun());
        System.out.println("=======================================================");

        for (int y = 0; y < board.getRows(); y++) {
            System.out.print("R" + y + " ");
            for (int x = 0; x < board.getCols(); x++) {
                String symbol = "   "; // Empty tile

                // 1. Check for Plants
                models.grid.Tile tile = board.getTile(x, y);
                if (!tile.getPlantedPlants().isEmpty()) {
                    String pName = tile.getPlantedPlants().get(0).getName();
                    if (pName.equals("Peashooter")) symbol = " P ";
                    else if (pName.equals("Sunflower")) symbol = " S ";
                    else symbol = " * ";
                }

                // 2. Check for Zombies (takes visual priority over plants if overlapping)
                for (models.entities.Zombie z : engine.getActiveZombies()) {
                    // Cast float X to int to find which tile the zombie is currently standing on
                    if ((int)z.getY() == y && (int)z.getX() == x) {
                        symbol = "[Z]";
                    }
                }

                // 3. Optional: Check for projectiles
                for (models.entities.Projectile p : engine.getActiveProjectiles()) {
                    if (p.getY() == y && (int)p.getX() == x && symbol.equals("   ")) {
                        symbol = " - ";
                    }
                }

                System.out.print("|" + symbol);
            }
            System.out.println("|");
            System.out.println("   ------------------------------------------------");
        }
        System.out.println();
    }
}