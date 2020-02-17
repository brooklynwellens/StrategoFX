package game;

import unit.*;
import board.Board;
import common.Position;
import turn.Turn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public void selectUnit(Position source) {
        Unit selectedUnit = getUnitOnTile(source);
        currentTurn.setSelectedUnit(selectedUnit);
        currentTurn.setStart(source);
    }

    public void processMove(Position destination) {
        Unit selectedUnit = currentTurn.getSelectedUnit();
        if (!validateMove(destination)) {
            return;
        }
        currentTurn.setDestination(destination);
        board.clearTile(currentTurn.getStart());
        if (!board.tileIsOccupied(destination)) {
            board.setUnitIdOnTile(destination, selectedUnit.getId());
            nextTurn();
            return;
        }
        Unit enemyUnit = getUnitOnTile(destination);
        processBattle(selectedUnit, enemyUnit);
        nextTurn();
    }

    private boolean validateMove(Position destination) {
        boolean canReach = selectedUnitCanReach(destination);
        boolean isRouteAvailable = board.isRouteAvailable(currentTurn.getStart(), destination);
        boolean friendlyUnitAtDestination = false;
        boolean destinationIsAccessible = board.tileIsAccessible(destination);
        if (board.tileIsOccupied(destination)) {
            friendlyUnitAtDestination = friendlyUnitAt(destination);
        }
        if (canReach && isRouteAvailable && !friendlyUnitAtDestination && destinationIsAccessible) {
            return true;
        }
        System.out.println("Invalid move!");
        return false;
    }

    private boolean selectedUnitCanReach(Position destination) {
        Unit selectedUnit = currentTurn.getSelectedUnit();
        Position start = currentTurn.getStart();
        Position distance = start.distanceTo(destination);
        return selectedUnit.canReach(distance);
    }

    private boolean friendlyUnitAt(Position destination) {
        int idOnDestination = board.getUnitIdOnTile(destination);
        return getUnitById(idOnDestination).isColor(currentTurn.getTurnType());
    }

    private void nextTurn() {
        turnHistory.add(currentTurn);
        UnitColor color = currentTurn.isType(UnitColor.BLUE) ? UnitColor.RED : UnitColor.BLUE;
        currentTurn = new Turn(color);
    }

    private void processBattle(Unit friendlyUnit, Unit enemyUnit) {
        ComparisonResult battleResult = friendlyUnit.getBattleResult(enemyUnit);
        Position destination = currentTurn.getDestination();
        if (battleResult == ComparisonResult.DRAW) {
            friendlyUnit.die();
            enemyUnit.die();
            board.clearTile(destination);
            return;
        }
        if (battleResult == ComparisonResult.WIN) {
            enemyUnit.die();
            board.setUnitIdOnTile(destination, friendlyUnit.getId());
        }
        if (battleResult == ComparisonResult.LOSS) {
            friendlyUnit.die();
        }
    }

    private Unit getUnitById(int unitId) {
        return units.stream().filter(unit -> unit.getId() == unitId).findFirst().orElse(null);
    }

    public Unit getUnitOnTile(Position position) {
        int unitId = board.getUnitIdOnTile(position);
        return getUnitById(unitId);
    }

    public boolean isUnitSelected() {
        return this.currentTurn.isUnitSelected();
    }

    public List<Unit> getCapturedUnits(UnitColor color) {
        return units.stream().filter(unit -> unit.isColor(color) && !unit.isAlive()).collect(Collectors.toList());
    }
}


