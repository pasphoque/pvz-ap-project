package models;

import models.grid.Board;

public class GameEngine {
    private User activeUser;
    private int currentTicks;
    private int currentSun;
    private Board board;

    public GameEngine(User activeUser) {
        this.activeUser = activeUser;
        this.currentTicks = 0;
        this.currentSun = 50; // Starting sun amount
        this.board = new Board();
    }

    public void advanceTime(int ticks) {
        for (int i = 0; i < ticks; i++) {
            processTick();
        }
    }

    private void processTick() {
        currentTicks++;
        // TODO: In Step 4, we will add wave spawning, bullet movement, and sun dropping here.
    }

    public int getCurrentTicks() { return currentTicks; }
    public int getCurrentSun() { return currentSun; }
    public Board getBoard() { return board; }
}