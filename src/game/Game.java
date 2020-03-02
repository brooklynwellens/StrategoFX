package game;

import battle.Battle;
import battle.BattleResult;
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
    private GameStatus status;

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
        status = GameStatus.RUNNING;
    }

    public void selectUnit(Position source) {
        Unit selectedUnit = getUnitOnTile(source);
        currentTurn.setSelectedUnit(selectedUnit);
        currentTurn.setStart(source);
    }

    public void processMove(Position destination) {
        if (!isMoveValid(destination)) {
            return;
        }
        currentTurn.setDestination(destination);
        board.clearTile(currentTurn.getStart());
        Unit selectedUnit = currentTurn.getSelectedUnit();
        if (!board.isTileOccupied(destination)) {
            board.setUnitIdOnTile(destination, selectedUnit.getId());
        } else {
            Unit enemyUnit = getUnitOnTile(destination);
            processBattleResult(selectedUnit, enemyUnit);
        }
        updateStatus();
        updateUnitVisibility();
        nextTurn();
    }

    private boolean isMoveValid(Position destination) {
        if (!canSelectedUnitReach(destination)) {
            return false;
        }
        if (!board.isRouteAvailable(currentTurn.getStart(), destination)) {
            return false;
        }
        if (board.isTileOccupied(destination) && isFriendlyUnitAt(destination)) {
            return false;
        }
        if (!board.isTileAccessible(destination)) {
            return false;
        }
        return true;
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

    private void updateStatus() {
        if (isFlagCaptured(UnitColor.RED)) {
            status = GameStatus.RED_CAPTURED;
        }
        if (isFlagCaptured(UnitColor.BLUE)) {
            status = GameStatus.BLUE_CAPTURED;
        }
        if (!areMovesAvailable(UnitColor.RED)) {
            status = GameStatus.RED_NO_MOVES;
        }
        if (!areMovesAvailable(UnitColor.BLUE)) {
            status = GameStatus.BLUE_NO_MOVES;
        }
    }

    private boolean isFlagCaptured(UnitColor color) {
        return units.stream().anyMatch(unit -> unit.isColor(color) && unit.isCaptured() && unit.getRank() == Rank.Flag);
    }

    private boolean areMovesAvailable(UnitColor color) {
        return units.stream().anyMatch(unit -> unit.isColor(color) && !unit.isCaptured() && unit.getRank().getMovementspeed() >= 1);
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

    public List<Unit> getVisibleUnits() {
        return new ArrayList<>(visibleUnitsWithCounter.keySet());
    }

    public boolean isGameOver() {
        return status != GameStatus.RUNNING;
    }
}


