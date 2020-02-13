package game;

import unit.*;
import board.Board;
import common.Position;
import turn.Turn;

import java.util.ArrayList;
import java.util.Map;

public class Game {

    private ArrayList<Unit> units;
    private Board board;
    private Turn currentTurn;
    private ArrayList<Turn> turnHistory;

    public Game(Map<Unit, Position> initialUnitPositions) {
        board = new Board();
        units = new ArrayList<>(initialUnitPositions.keySet());
        for (Map.Entry<Unit, Position> entry : initialUnitPositions.entrySet()) {
            board.setUnitIdOnTile(entry.getValue(), entry.getKey().getId());
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
        Position source = new Position(xPos, yPos);
        int unitId = board.getUnitIdOnTile(source);
        Unit selectedUnit = getUnitById(unitId);
        currentTurn.setSelectedUnit(selectedUnit);
        currentTurn.setSource(source);
    }

    public void processMove(int xPos, int yPos) {
        Position destination = new Position(xPos, yPos);
        if (!validateMove(destination)) {
            return;
        }
        moveUnit(destination);
        nextTurn();
    }

    public void moveUnit(Position destination) {
        Unit selectedUnit = currentTurn.getSelectedUnit();
        board.setUnitIdOnTile(destination, selectedUnit.getId());
        board.clearTile(currentTurn.getSource());
    }

    public boolean validateMove(Position destination) {
        Unit selectedUnit = currentTurn.getSelectedUnit();
        Position distance = currentTurn.getSource().distanceTo(destination);
        boolean canReach = selectedUnit.canReach(distance);
        if (canReach) {
            return true;
        }
        System.out.println("Invalid move!");
        return false;
    }

    public void computeValidMoves() {

    }

    public Unit getUnitById(int unitId) {
        return units.stream().filter(unit -> unit.getId() == unitId).findFirst().orElse(null);
    }

    public Unit getUnitOnTile(int xPos, int yPos) {
        int unitId = board.getUnitIdOnTile(new Position(xPos, yPos));
        return getUnitById(unitId);
    }

    public boolean isUnitSelected() {
        return this.currentTurn.isUnitSelected();
    }
}


