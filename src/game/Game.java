package game;

import Battle.Battle;
import Battle.BattleResult;
import ai.Ai;
import unit.*;
import board.Board;
import common.Position;
import turn.Turn;

import java.util.*;
import java.util.stream.Collectors;

public class Game {

    private ArrayList<Unit> units;
    private Board board;
    private Turn currentTurn;
    private ArrayList<Turn> turnHistory;
    private Ai ai;
    private Map<Unit, Integer> visibleUnitsWithCounter;

    public Game(Map<Position, Unit> initialUnitPositions) {
        board = new Board();
        units = new ArrayList<>(initialUnitPositions.values());
        for (Map.Entry<Position, Unit> entry : initialUnitPositions.entrySet()) {
            board.setUnitIdOnTile(entry.getKey(), entry.getValue().getId());
        }
        List<Unit> aiUnits = units.stream().filter(unit -> unit.isColor(UnitColor.RED)).collect(Collectors.toList());
        ai = new Ai(aiUnits);
        turnHistory = new ArrayList<>();
        currentTurn = new Turn(UnitColor.BLUE);
        visibleUnitsWithCounter = new HashMap<>();
    }

    public void selectUnit(Position source) {
        Unit selectedUnit = getUnitOnTile(source);
        currentTurn.setSelectedUnit(selectedUnit);
        currentTurn.setStart(source);
    }

    public void processMove(Position destination) {
        Unit selectedUnit = currentTurn.getSelectedUnit();
        if (!isMoveValid(destination)) {
            return;
        }
        currentTurn.setDestination(destination);
        board.clearTile(currentTurn.getStart());
        if (!board.isTileOccupied(destination)) {
            board.setUnitIdOnTile(destination, selectedUnit.getId());
            nextTurn();
            return;
        }
        Unit enemyUnit = getUnitOnTile(destination);
        processBattleResult(selectedUnit, enemyUnit);
        updateUnitVisibility();
        nextTurn();
    }

    private boolean isMoveValid(Position destination) {
        boolean canReach = canSelectedUnitReach(destination);
        boolean isRouteAvailable = board.isRouteAvailable(currentTurn.getStart(), destination);
        boolean friendlyUnitAtDestination = false;
        boolean destinationIsAccessible = board.isTileAccessible(destination);
        if (board.isTileOccupied(destination)) {
            friendlyUnitAtDestination = isFriendlyUnitAt(destination);
        }
        if (canReach && isRouteAvailable && !friendlyUnitAtDestination && destinationIsAccessible) {
            return true;
        }
        System.out.println("Invalid move!");
        return false;
    }

    private boolean canSelectedUnitReach(Position destination) {
        Unit selectedUnit = currentTurn.getSelectedUnit();
        Position start = currentTurn.getStart();
        Position distance = start.getDistanceTo(destination);
        return selectedUnit.canReach(distance);
    }

    private boolean isFriendlyUnitAt(Position destination) {
        int idOnDestination = board.getUnitIdOnTile(destination);
        return getUnitById(idOnDestination).isColor(currentTurn.getTurnType());
    }

    private void nextTurn() {
        turnHistory.add(currentTurn);
        UnitColor color = currentTurn.isType(UnitColor.BLUE) ? UnitColor.RED : UnitColor.BLUE;
        currentTurn = new Turn(color);
    }

    private void processBattleResult(Unit attackingUnit, Unit defendingUnit) {
        Battle battle = new Battle(attackingUnit, defendingUnit);
        BattleResult battleResult = battle.getResult();
        Position destination = currentTurn.getDestination();
        if (battleResult == BattleResult.DRAW) {
            board.clearTile(destination);
            return;
        }
        if (battleResult == BattleResult.WIN) {
            board.setUnitIdOnTile(destination, attackingUnit.getId());
        }
        if (battleResult == BattleResult.LOSS) {
            visibleUnitsWithCounter.put(defendingUnit, 3);
        }
    }

    private void updateUnitVisibility() {
        for (Iterator<Map.Entry<Unit, Integer>> it = visibleUnitsWithCounter.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Unit, Integer> entry = it.next();
            if (entry.getValue() == 0) {
                it.remove();
            } else {
                visibleUnitsWithCounter.replace(entry.getKey(), entry.getValue()-1);
            }
        }
    }

    public void computerMove() {
        boolean isMoveCompleted = false;
        while (!isMoveCompleted) {
            Unit selectedUnit = ai.chooseUnit();
            Position source = board.getPositionById(selectedUnit.getId());
            selectUnit(source);
            Position destination = ai.choosePosition();
            if (isMoveValid(destination)) {
                isMoveCompleted = true;
            }
            processMove(destination);
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
        return units.stream().filter(unit -> unit.isColor(color) && !unit.isCaptured()).collect(Collectors.toList());
    }
}


