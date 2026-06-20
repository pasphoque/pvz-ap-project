package models.grid;

public class LawnMower {
    private final int row;
    private boolean isActivated;

    public LawnMower(int row) {
        this.row = row;
        this.isActivated = false;
    }

    public int getRow() { return row; }
    public boolean isActivated() { return isActivated; }

    public void activate() {
        this.isActivated = true;
        // Logic to kill all zombies in the row will go here later
    }
}