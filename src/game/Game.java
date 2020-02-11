package game;

import unit.*;
import board.Board;
import common.Position;
import turn.Turn;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {

    static int maxTurnVisibilty = 3;

    private ArrayList<Unit> units;
    private Board board;
    private Turn turn;

    public Game() {
        board = new Board();
        units = new ArrayList<>();
        initializeUnits();
    }

    private void initializeUnits() {
        int id = 1;
        for (Rank value : Rank.values()) {
            for (UnitColor unitColor : UnitColor.values()) {
                for (int i = 0; i < value.getAmountOf(); i++) {
                    units.add(new Unit(value, unitColor,id++));
                }
            }
        }
    }

    public void placeUnit(Position unitDestination){
        turn.getSelectedUnit().place(unitDestination);
    }

    public void setSelectedUnit(int x, int y) {

    }

    public void printUnits() {
        for (Unit unit : units) {
            System.out.println(unit);
        }
    }
}


