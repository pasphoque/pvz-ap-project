package models.entities.zombies;

import models.entities.Zombie;

public class BaseZombie extends Zombie {
    public BaseZombie(float x, float y) {
        // Standard Zombie: Speed 0.05 per tick, 100 Damage per bite, 200 HP, Cost 1
        super(x, y, "Zombie", 0.05f, 100, 200, 1);
    }

    @Override
    public void performAction() {
        // If there is a plant in front, it will eat. Otherwise, it moves.
        // For now, let's just make it move.
        move();
    }
}