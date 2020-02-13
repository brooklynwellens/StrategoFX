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
    private Turn currentTurn;
    private ArrayList<Turn> turnHistory;

    public Game(Map<Unit, Position> initalUnitPositions) {
        board = new Board();
        units = new ArrayList<>(initalUnitPositions.keySet());
        for (Map.Entry<Unit, Position> entry : initalUnitPositions.entrySet()) {
            board.setUnitIdOnTile(entry.getKey().getId(), entry.getValue().getX(), entry.getValue().getY());
        }
        turnHistory = new ArrayList<>();
        currentTurn = new Turn(UnitColor.BLUE);
    }

    private void nextTurn() {
        turnHistory.add(currentTurn);
        UnitColor color = currentTurn.isType(UnitColor.BLUE) ? UnitColor.RED : UnitColor.BLUE;
        currentTurn = new Turn(color);
    }

    public void selectUnit(int xPos, int yPos) {
        int unitId = board.getUnitIdOnTile(xPos, yPos);
        Unit selectedUnit = getUnitById(unitId);
        currentTurn.setSelectedUnit(selectedUnit);
        currentTurn.setxPos(xPos);
        currentTurn.setyPos(yPos);
    }

    public void processMove() {

    }

    public void moveUnit(int xPos, int yPos) {
        Unit selectedUnit = currentTurn.getSelectedUnit();
        // validate
        board.setUnitIdOnTile(xPos, yPos, selectedUnit.getId());

    }

    public void validateMove(Position destination) {
        Unit selectedUnit = currentTurn.getSelectedUnit();
        Position source = new Position(currentTurn.getxPos(), currentTurn.getyPos());
        selectedUnit.canReach(source, destination);
    }

    public void computeValidMoves() {

    }

    public Unit getUnitById(int unitId) {
        return units.stream().filter(unit -> unit.getId() == unitId).findFirst().orElse(null);
    }

    public Unit getUnitOnTile(int xPos, int yPos) {
        int unitId = board.getUnitIdOnTile(xPos, yPos);
        return getUnitById(unitId);
    }
}


