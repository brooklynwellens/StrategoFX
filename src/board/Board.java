package board;

import common.Position;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final int HEIGHT = 10;
    private final int WIDTH = 10;
    private Tile[][] gameField;

    public Board() {
        gameField = new Tile[WIDTH][HEIGHT];
        initializeField();
    }

    public void initializeField() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if ((y == 4 || y == 5) && (x == 2 || x == 3 || x == 6 || x == 7)) {
                    Tile waterTile = new Tile(Surface.WATER);
                    gameField[x][y] = waterTile;
                    continue;
                }
                gameField[x][y] = new Tile(Surface.GRASS);
            }
        }
    }

    public boolean isInBounds(Position position) {
        return position.getX() >= 0 && position.getX() < WIDTH && position.getY() >= 0 && position.getY() < HEIGHT;
    }

    public boolean positionsAreFree(List<Position> tilePositions) {
        if (tilePositions.isEmpty()) {
            return true;
        }
        boolean tilesAvailable = true;
        for (Position tilePosition : tilePositions) {
            if (!tileIsOccupied(tilePosition) || !tileIsAccessible(tilePosition)) {
                tilesAvailable = false;
            }
        }
        return tilesAvailable;
    }

    public boolean tileIsOccupied(Position tilePosition) {
        return this.gameField[tilePosition.getX()][tilePosition.getY()].isOccupied();
    }

    public boolean tileIsAccessible(Position tilePosition) {
        return this.gameField[tilePosition.getX()][tilePosition.getY()].isAccessible();
    }


    public List<Position> getRouteTo(Position start, Position destination) {

        List<Position> destinationPath = new ArrayList<>();

        int xMin = Math.min(start.getX(), destination.getX());
        int xMax = Math.max(start.getX(), destination.getX());
        int yMin = Math.min(start.getY(), destination.getY());
        int yMax = Math.max(start.getY(), destination.getY());

        for (int x = xMin; x < xMax + 1; x++) {
            for (int y = yMin; y < yMax + 1; y++) {
                destinationPath.add(new Position(x, y));
            }
        }
        if (destinationPath.size() >= 2) {
            destinationPath.remove(destinationPath.size() - 1);
        }
        destinationPath.remove(0);
        return destinationPath;
    }

    public Tile[][] getGameField() {
        return gameField;
    }
}
