package game;

import unit.*;
import board.Board;
import common.Position;
import turn.Turn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Game implements Serializable {

    static int maxTurnVisibilty = 3;

    private ArrayList<Unit> units;
    private Board board;
    private Turn turn;

    public Game(Map<Unit, Position> initalUnitPositions) {
        board = new Board();
        units = new ArrayList<>(initalUnitPositions.keySet());
        for (Map.Entry<Unit, Position> entry : initalUnitPositions.entrySet()) {
            board.setUnitIdOnTile(entry.getKey().getId(), entry.getValue().getX(), entry.getValue().getY());
        }
    }

    public void placeUnit(Unit unit, int x, int y) {
        unit.place(new Position(x, y));
        board.setUnitIdOnTile(unit.getId(),x ,y);
    }

}


