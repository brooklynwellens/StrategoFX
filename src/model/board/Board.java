package model.board;

import model.common.Position;
import model.common.Route;

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
                    gameField[x][y] = new Tile(Surface.WATER);
                    continue;
                }
                gameField[x][y] = new Tile(Surface.GRASS);
            }
        }
    }

    public boolean isRouteAvailable(Position start, Position destination) {
        Route route = new Route(start, destination);
        List<Position> path = route.getPath();
        for (Position position : path) {
            Tile tile = gameField[position.getX()][position.getY()];
            if (!tile.isAccessible() || tile.isOccupied()) {
                return false;
            }
        }
        return true;
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

    public boolean isTileOccupied(Position position) {
        return this.gameField[position.getX()][position.getY()].isOccupied();
    }

    public boolean isTileAccessible(Position position) {
        return this.gameField[position.getX()][position.getY()].isAccessible();
    }

    public Position getPositionById(int unitId) {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (gameField[x][y].getUnitId() == unitId) {
                    return new Position(x, y);
                }
            }
        }
        return new Position(-1, -1);
    }

    public Tile[][] getGameField() {
        return gameField;
    }
}
