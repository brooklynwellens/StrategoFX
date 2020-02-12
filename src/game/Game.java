package game;

import unit.*;
import board.Board;
import common.Position;
import turn.Turn;

import java.util.ArrayList;
import java.util.Map;

public class Game {

    private static final int maxTurnVisibilty = 3;

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

    public void validateMove(Unit selectedUnit, Position destination) {
    }

    public void moveUnit(Unit selectedUnit, Position destination) {

    }

    public void computeValidMoves() {

    }
}


