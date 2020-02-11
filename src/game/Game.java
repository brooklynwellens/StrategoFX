package game;

import unit.*;
import board.Board;
import common.Position;
import turn.Turn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game implements Serializable {

    static int maxTurnVisibilty = 3;

    private ArrayList<Unit> units;
    private Board board;
    private Turn turn;

    public Game() {
        board = new Board();
        units = new ArrayList<>();
        initializeUnits();
        placeRedArmy();
    }

    private void initializeUnits() {
        for (UnitColor unitColor : UnitColor.values()) {
            for (Rank value : Rank.values()) {
                for (int i = 0; i < value.getAmountOf(); i++) {
                    units.add(new Unit(value, unitColor));
                }
            }
        }
    }

    private void placeRedArmy() {
        List<Unit> redUnits = units.stream().filter(unit -> unit.getColor() == UnitColor.RED).collect(Collectors.toList());
        int index = 0;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 10; x++) {
                redUnits.get(index++).place(new Position(x, y));
            }
        }
    }

    public List<Unit> getUnplacedUnits() {
        return units.stream().filter(unit -> !unit.isPlaced()).collect(Collectors.toList());
    }

    public void placeUnit(Unit unit, int x, int y) {
        unit.place(new Position(x, y));
        board.setUnitIdOnTile(unit.getId(),x ,y);
    }

}


