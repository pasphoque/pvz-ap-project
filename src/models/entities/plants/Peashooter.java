package models.entities.plants;

import models.entities.Plant;
import models.entities.PlantCategory;
import models.entities.PlantTag;

public class Peashooter extends Plant {
    private int ticksSinceLastShot = 0;
    private final int fireRate = 15; // Shoots every 1.5 seconds (15 ticks)

    public Peashooter(int x, int y) {
        super(x, y, "Peashooter", 100, 300, 20, 50); // HP 300, Damage 20
        this.category = PlantCategory.SHOOTER;
        this.tags.add(PlantTag.PEA);
    }

    @Override
    public void performAction() {
        ticksSinceLastShot++;
        if (ticksSinceLastShot >= fireRate) {
            shoot();
            ticksSinceLastShot = 0;
        }
    }

    private void shoot() {
        // TODO: In Step 4, we will hook this up to the ProjectileManager
        // System.out.println("Peashooter fired a pea at x=" + x + ", y=" + y);
    }
}