package models.grid;

public class Board {
    private final int rows = 5;
    private final int cols = 9;
    private final Tile[][] grid;
    private final LawnMower[] lawnMowers;

    public Board() {
        grid = new Tile[rows][cols];
        lawnMowers = new LawnMower[rows];
        initializeBoard();
    }

    private void initializeBoard() {
        // Initialize all tiles as NORMAL by default
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                grid[y][x] = new Tile(x, y, TileType.NORMAL);
            }
            // Add a lawnmower to the start of each row
            lawnMowers[y] = new LawnMower(y);
        }
    }

    public Tile getTile(int x, int y) {
        if (x >= 0 && x < cols && y >= 0 && y < rows) {
            return grid[y][x];
        }
        return null;
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }
}