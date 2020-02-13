package board;

import common.Position;
import javafx.geometry.Pos;
import unit.Unit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    private final int HEIGHT = 10;
    private final int WIDTH = 10;
    private Tile[][] gameField;

    public Board() {
        gameField = new Tile[WIDTH][HEIGHT];
        initializeGameField();
    }

    private void initializeGameField() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if ((y == 4 || y == 5) && (x == 2 || x == 3 || x == 6 || x == 7)) {
                    gameField[x][y] = new Tile(x, y, Surface.WATER);
                    continue;
                }
                gameField[x][y] = new Tile(x, y, Surface.GRASS);
            }
        }
    }

    public Tile getTileByUnitId(int unitId) {
        return Arrays.stream(gameField).flatMap(Arrays::stream).filter(tile -> tile.getUnitId() == unitId).findFirst().orElse(null);
    }

    public boolean tileIsOccupiedWithCoordinates(int x, int y) {
        return this.gameField[x][y].isOccupied();
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

    public int getUnitIdOnTile(Position position) {
        return this.gameField[position.getX()][position.getY()].getUnitId();
    }

    public void setUnitIdOnTile(Position position, int id) {
        this.gameField[position.getX()][position.getY()].setUnitId(id);
    }

    public void clearTile(Position position) {
        this.gameField[position.getX()][position.getY()].clearUnitId();
    }
}
