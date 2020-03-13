package model.game;

import javafx.geometry.Pos;
import model.unit.*;
import model.board.Board;
import model.turn.Turn;

import java.util.ArrayList;

public class Game {

    private static final int maxTurnVisibilty = 3;

    private ArrayList<Unit> units;
    private Board board;
    private Turn currentTurn;

    public Game() {
        board = new Board();
        units = new ArrayList<>();
        currentTurn = new Turn(UnitColor.BLUE);
        Unit testUnit = new Unit(Rank.Colonel, UnitColor.BLUE);
        Unit testUnit2 = new Unit(Rank.Major, UnitColor.BLUE);
        units.add(testUnit);
        units.add(testUnit2);
        board.setUnitIdOnTile(testUnit.getId(), 9,9);
        board.setUnitIdOnTile(testUnit2.getId(),5,7);
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


