package game;

import javafx.geometry.Pos;
import turn.TurnType;
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

    public Game() {
        board = new Board();
        units = new ArrayList<>();
        units.add(new Unit(Rank.Colonel, UnitColor.BLUE));
    }

    public void createTurn(int x, int y) {
        Unit selectedUnit = getUnitById(board.getUnitIdOnTile(x,y));
        turn = new Turn(TurnType.PLAYER);
        turn.setSelectedUnit(selectedUnit);
        turn.setPosition(new Position(x, y));
    }

    public void moveUnit(int desX, int desY) {
        Position destination = new Position(desX, desY);
        Unit selectedUnit = this.turn.getSelectedUnit();
        if (selectedUnit.canReach(turn.getPosition().distanceTo(destination))) {
            System.out.println("MOVED BABY");
            selectedUnit.place(destination);
            return;
        }
        System.out.println("NO ME GUSTA SENOR");
    }

    public void computeValidMoves() {

    }

    public Unit getUnitById(int unitId) {
        return units.stream().filter(unit -> unit.getId() == unitId).findFirst().orElse(null);
    }

    public Unit getUnitAtPosition(Position position) {
        return units.stream().filter(unit -> unit.getPosition().equals(position)).findFirst().orElse(null);
    }
}


