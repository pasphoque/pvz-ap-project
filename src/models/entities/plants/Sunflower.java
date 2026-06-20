package models.entities.plants;

import models.entities.Plant;
import models.entities.PlantCategory;
import models.entities.PlantTag;
import models.GameEngine;

public class Sunflower extends Plant {
    private int ticksSinceLastProduction = 0;
    private final int productionRate = 240; // Produces sun every 24 seconds (240 ticks)
    private GameEngine engine; // We need a reference to the engine to add sun

    public Sunflower(int x, int y, GameEngine engine) {
        super(x, y, "Sunflower", 50, 300, 0, 50);
        this.category = PlantCategory.SUN_PRODUCER;
        this.tags.add(PlantTag.SUN);
        this.engine = engine;
    }

    @Override
    public void performAction() {
        ticksSinceLastProduction++;
        if (ticksSinceLastProduction >= productionRate) {
            engine.addSun(25); // Adds 25 sun to the player's bank
            ticksSinceLastProduction = 0;
        }
    }
}