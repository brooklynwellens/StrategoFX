package game;

import ai.AiUnitSetup;
import common.Position;
import javafx.geometry.Pos;
import unit.Unit;
import unit.UnitColor;
import unit.UnitManager;
import unitPositionPreset.UnitPlacer;
import unitPositionPreset.UnitPlacerFactory;

import java.util.*;

public class GameSetup {

    private Map<Position, Unit> unitStartingPositions;
    private List<Unit> unplacedUnits;
    private Unit placedUnit;

    public GameSetup() {
        UnitManager unitManager = new UnitManager();
        this.unplacedUnits = unitManager.getUnitsOfColor(UnitColor.BLUE);
        this.unitStartingPositions = new HashMap<>();
        AiUnitSetup aiUnitSetup = new AiUnitSetup(unitManager.getUnitsOfColor(UnitColor.RED));
        this.unitStartingPositions.putAll(aiUnitSetup.getUnitStartingPositions());
        initializeValidPlayerPositions();
    }

    private void initializeValidPlayerPositions() {
        for (int y = 6; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                unitStartingPositions.put(new Position(x, y), null);
            }
        }
    }

    public void setUnitPosition(Unit unit, Position position) {
        if (isValidPlayerStartingPosition(position)) {
            if (isUnitAt(position)) {
                unplacedUnits.add(unitStartingPositions.get(position));
            }
            unitStartingPositions.replace(position, unit);
            unplacedUnits.remove(unit);
            placedUnit = unit;
        }
    }

    // rework naar stream
    private boolean isValidPlayerStartingPosition(Position position) {
        for (Position positionKey : unitStartingPositions.keySet()) {
            if (positionKey.equals(position) && positionKey.getY() > 5 && positionKey.getY() < 10) {
                return true;
            }
        }
        return false;
    }

    private boolean isUnitAt(Position position) {
        return unitStartingPositions.get(position) != null;
    }

    public void useUnitPreset(UnitColor unitColor, String type) {
        UnitPlacerFactory factory = new UnitPlacerFactory();
        UnitPlacer unitPlacer = factory.create(type, unitColor);
        unitPlacer.placeUnits();
        Map<Position, Unit> unitPositions = unitPlacer.getUnitPositions();
        replaceUnitPositions(unitPositions);
    }

    private void replaceUnitPositions(Map<Position, Unit> unitPositions) {

    }

    public Unit getPlacedUnit() {
        return placedUnit;
    }

    public List<Unit> getUnplacedUnits() {
        return unplacedUnits;
    }

    public boolean isSetupDone() {
        return unplacedUnits.isEmpty();
    }
}
