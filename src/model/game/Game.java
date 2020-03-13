package model.game;

import model.Exception.StrategoException;
import model.battle.Battle;
import model.ai.Ai;
import model.battle.BattleResult;
import model.board.Tile;
import model.unit.*;
import model.board.Board;
import model.common.Position;
import model.turn.Turn;
import java.util.*;
import java.util.stream.Collectors;

/**
 * de werking van het spel wordt hierin uitgewerkt.
 *
 * @author LABR,WEBR
 * @version 1.12
 */

public class Game {

    private ArrayList<Unit> units;
    private Board board;
    private Turn currentTurn;
    private ArrayList<Turn> turnHistory;
    private Ai ai;
    private Map<Unit, Integer> visibleUnitsWithVisibilityCounter;
    private GameStatus status;

    /**
     * De constructor krijgt alles binnen van de benodigde instanties.
     *
     * @param initialUnitPositions de beginplaatsen van units
     */
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
        visibleUnitsWithVisibilityCounter = new HashMap<>();
        status = GameStatus.RUNNING;
    }

    /**
     * Deze methode gaat de unit selecteren op de plaats waar je hebt geklikt en gaat daar de source positie van maken.
     *
     * @param source de beginpositie van de geselcteerde pion
     */
    public void selectUnit(Position source) throws StrategoException {
        Unit selectedUnit = getUnitOnTile(source);
        currentTurn.setSelectedUnit(selectedUnit);
        currentTurn.setStart(source);
    }

    /**
     * Deze methode gaat de gevraagde move uitvoeren, de status van de pion updaten, de visibility v/d pion updaten en
     * gaat de beurt doorgeven aan de andere speler.
     *
     * @param destination de eindpositie van de geselecteerde pion
     * @return processmove true/false
     */
    public boolean processMove(Position destination) {
        if (!isMoveValid(destination)) {
            return false;
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
        return true;
    }

    /**
     * Deze methode gaat kijken of het wel aan jou is om een move te doen en of de tile wel kan gebruikt worden.
     *
     * @param destination de positie van de gekozen plaats op het veld
     * @return isMoveValid true/false
     */
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

    /**
     *Deze methode gaat kijken of de geselcteerde unit wel op de plaats kan geraken die je geselcteerd hebt.
     *
     * @param destination de positie van de gekozen bestemming
     * @return canSelectedUnitReach
     */
    private boolean canSelectedUnitReach(Position destination) {
        Unit selectedUnit = currentTurn.getSelectedUnit();
        Position start = currentTurn.getStart();
        Position distance = start.getDistanceTo(destination);
        return selectedUnit.canReach(distance);
    }

    /**
     * Deze methode gaat kijken als er een pion op deze positie staat of het een vriend of een vijand is.
     *
     * @param destination de plaats van het gekozen vak
     * @return de unitId op de gevraagde plaats en de kleur van de unit op die plaats
     */
    private boolean isFriendlyUnitAt(Position destination) {
        int idOnDestination = board.getUnitIdOnTile(destination);
        return getUnitById(idOnDestination).isColor(currentTurn.getTurnType());
    }

    /**
     * De beurt wordt opgeslagen in de beurtengeschiedenis en de beurt wordt dan doorgegeven aan de volgende speler.
     *
     */
    private void nextTurn() {
        turnHistory.add(currentTurn);
        UnitColor color = currentTurn.isType(UnitColor.BLUE) ? UnitColor.RED : UnitColor.BLUE;
        currentTurn = new Turn(color);
    }

    /**
     * Deze methode gaat kijken welke pion de sterkste is en aan de hand daarvan gaat hij zeggen wat er moet gebeuren met de pionnen.
     *
     * @param attackingUnit de unit van de aanvaller
     * @param defendingUnit de unit van de verdediger
     */
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
            visibleUnitsWithVisibilityCounter.put(defendingUnit, 3);
        }
    }

    /**
     * De methode gaat de visibility van de unit updaten.
     *
     */
    private void updateUnitVisibility() {
        for (Iterator<Map.Entry<Unit, Integer>> it = visibleUnitsWithVisibilityCounter.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Unit, Integer> entry = it.next();
            if (entry.getValue() == 0) {
                it.remove();
            } else {
                visibleUnitsWithVisibilityCounter.replace(entry.getKey(), entry.getValue() - 1);
            }
        }
    }

    /**
     * Deze methode gaat eerst kijken wat voor soort unitvlag er geselecteerd is en gaat dan zien welke moves er nog
     * kunnen gedaan worden.
     *
     */
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

    /**
     * Deze methode gaat kijken of de geselecteerde unit de vlag is van het andere team of niet.
     *
     * @param color de kleur van de vlag (blauw of rood)
     * @return isFlagCaptured true/false
     */
    private boolean isFlagCaptured(UnitColor color) {
        return units.stream().anyMatch(unit -> unit.isColor(color) && unit.isCaptured() && unit.getRank() == Rank.Flag);
    }

    /**
     * Deze methode gaat kijken of je nog een move kan doen.
     *
     * @param color de kleur van de geselecteerde pion
     * @return
     */
    private boolean areMovesAvailable(UnitColor color) {
        return units.stream().anyMatch(unit -> unit.isColor(color) && !unit.isCaptured() && unit.getRank().getMovementspeed() >= 1);
    }

    /**
     * Deze methode gaat de move van de computer verwerken en uitvoeren.
     *
     */
    public void computerMove() throws StrategoException {
        boolean isMoveCompleted = false;
        while (!isMoveCompleted) {
            Unit selectedUnit = ai.chooseUnit();
            Position source = board.getPositionById(selectedUnit.getId());
            selectUnit(source);
            Position destination = ai.choosePosition();
            if (isMoveValid(destination)) {
                isMoveCompleted = true;
                processMove(destination);
            }
        }
    }

    /**
     * Deze methode gaat kijken of de gamestatus nog op bezig staat, of dat rood of blauw geen moves meer kunnen doen
     * of één van de twee kleuren de vlag van het andere team heeft kunnen pakken.
     *
     * @return if game is over or not
     */
    public boolean isGameOver() {
        return status != GameStatus.RUNNING;
    }

    /**
     * Deze methode gaat kijken of de geselecteerde unit wel één is van d ecurrentturn army.
     *
     * @return isUnitSelected true/false
     */
    public boolean isUnitSelected() {
        return this.currentTurn.isUnitSelected();
    }

    /**
     * Deze methode gaat de geselecteerde unit onselecteren.
     * @throws StrategoException
     */
    public void unSelectUnit() throws StrategoException {
        currentTurn.setSelectedUnit(null);
    }

    /**
     * Deze methode plaatst de gevangen units in een lijst.
     */
    public void completeUnitList() {
        UnitManager unitManager = new UnitManager();
        List<Unit> freshUnitList = unitManager.getUnits();
        for (Unit unitToAdd : freshUnitList) {
            if (units.stream().noneMatch(unit -> unit.getId() == unitToAdd.getId())) {
                unitToAdd.captured();
                units.add(unitToAdd);
            }
        }
    }

    private Unit getUnitById(int unitId) {
        return units.stream().filter(unit -> unit.getId() == unitId).findFirst().orElse(null);
    }

    public Unit getUnitOnTile(Position position) {
        int unitId = board.getUnitIdOnTile(position);
        return getUnitById(unitId);
    }

    public List<Unit> getCapturedUnits(UnitColor color) {
        return units.stream().filter(unit -> unit.isColor(color) && !unit.isCaptured()).collect(Collectors.toList());
    }

    public List<Unit> getVisibleUnits() {
        return new ArrayList<>(visibleUnitsWithVisibilityCounter.keySet());
    }

    public GameStatus getStatus() {
        return status;
    }

    public Tile[][] getGameField(){
        return board.getGameField();
    }


}