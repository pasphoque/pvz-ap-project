package models;

import models.grid.Board;
import models.grid.Tile;
import models.entities.Plant;
import models.entities.Zombie;
import models.entities.Projectile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameEngine {
    private User activeUser;
    private int currentTicks;
    private int currentSun;
    private Board board;

    // Track active moving entities globally rather than strictly locking them to tiles
    private List<Zombie> activeZombies;
    private List<Projectile> activeProjectiles;

    public GameEngine(User activeUser) {
        this.activeUser = activeUser;
        this.currentTicks = 0;
        this.currentSun = 50;
        this.board = new Board();
        this.activeZombies = new ArrayList<>();
        this.activeProjectiles = new ArrayList<>();
    }

    public void advanceTime(int ticks) {
        for (int i = 0; i < ticks; i++) {
            processTick();
        }
    }

    private void processTick() {
        currentTicks++;

        // 1. Plant Actions (Shooting, Producing Sun)
        for (int y = 0; y < board.getRows(); y++) {
            for (int x = 0; x < board.getCols(); x++) {
                Tile tile = board.getTile(x, y);
                for (Plant plant : tile.getPlantedPlants()) {
                    plant.performAction();
                }
            }
        }

        // 2. Move & Action Zombies
        for (Zombie zombie : activeZombies) {
            // Check for collision with plant to eat, otherwise move
            Tile currentTile = board.getTile((int) zombie.getX(), (int) zombie.getY());
            if (currentTile != null && !currentTile.getPlantedPlants().isEmpty()) {
                Plant target = currentTile.getPlantedPlants().get(0);
                target.takeDamage(zombie.getBaseDamage()); // Assuming BaseDamage is per tick/attack cycle
                if (target.isDead()) {
                    currentTile.getPlantedPlants().remove(target);
                }
            } else {
                zombie.performAction(); // Calls move()
            }
        }

        // 3. Move Projectiles & Check Collisions
        Iterator<Projectile> projIterator = activeProjectiles.iterator();
        while (projIterator.hasNext()) {
            Projectile p = projIterator.next();
            p.move();

            // Check collision with zombies in the same row
            boolean hit = false;
            for (Zombie z : activeZombies) {
                // If zombie is in the same row and projectile x overlaps zombie x
                if ((int) z.getY() == p.getY() && p.getX() >= z.getX() && p.getX() <= z.getX() + 0.5f) {
                    z.takeDamage(p.getDamage());
                    hit = true;
                    break;
                }
            }

            // Remove projectile if it hit something or flew off the board (column 9)
            if (hit || p.getX() > board.getCols()) {
                projIterator.remove();
            }
        }

        // 4. Remove dead zombies
        activeZombies.removeIf(Zombie::isDead);
    }

    public void addProjectile(Projectile p) {
        activeProjectiles.add(p);
    }

    public void addSun(int amount) {
        this.currentSun += amount;
    }

    // Getters...
    public int getCurrentTicks() { return currentTicks; }
    public int getCurrentSun() { return currentSun; }
    public Board getBoard() { return board; }
    public List<Zombie> getActiveZombies() { return activeZombies; }
    public List<Projectile> getActiveProjectiles() { return activeProjectiles; }
}