package models.entities.zombies;

import models.entities.Armor;

public class ConeheadZombie extends BaseZombie {
    public ConeheadZombie(float x, float y) {
        super(x, y);
        this.name = "Conehead Zombie";
        this.waveCost = 2; // More expensive to spawn

        // Add the cone armor (370 HP) on top of the base zombie HP
        this.armors.push(new Armor("Cone", 370, false));
    }
}