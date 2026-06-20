package models.grid;

import models.entities.Plant;
import models.entities.Zombie;
import java.util.ArrayList;
import java.util.List;

public class Tile {
    private final int x; // Column (0 to 8)
    private final int y; // Row (0 to 4)
    private TileType type;

    private final List<Plant> plantedPlants;
    private final List<Zombie> residentZombies;
    private boolean hasDangerLine;

    public Tile(int x, int y, TileType type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.plantedPlants = new ArrayList<>();
        this.residentZombies = new ArrayList<>();
        this.hasDangerLine = false;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public TileType getType() { return type; }
    public void setType(TileType type) { this.type = type; }
    public List<Plant> getPlantedPlants() { return plantedPlants; }
    public List<Zombie> getResidentZombies() { return residentZombies; }
    public void setHasDangerLine(boolean hasDangerLine) { this.hasDangerLine = hasDangerLine; }
}