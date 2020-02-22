package game;

import ai.AiUnitSetup;
import common.Position;
import unit.Unit;
import unit.UnitColor;
import unit.UnitManager;

import java.util.*;

public class GameSetup {

    private Map<Position, Unit> unitStartingPositions;
    private UnitManager unitManager;
    private List<Unit> unplacedUnits;
    private Unit placedUnit;
    private AiUnitSetup aiUnitSetup;

    public GameSetup() {
        unitManager = new UnitManager();
        this.unplacedUnits = unitManager.getUnitsOfColor(UnitColor.BLUE);
        unitStartingPositions = new HashMap<>();
        setInitialValidPositions();
    }

    private void setInitialValidPositions() {
        for (int y = 6; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                unitStartingPositions.put(new Position(x, y), null);
            }
        }
    }

    public void setUnitPosition(Unit unit, Position position) {
        if (isValidStartingPosition(position)) {
            if (unitAtPosition(position)) {
                unplacedUnits.add(unitStartingPositions.get(position));
            }
            unitStartingPositions.replace(position, unit);
            placedUnit = unit;
        }
    }

    // rework naar stream
    private boolean isValidStartingPosition(Position position) {
        for (Position positionKey : unitStartingPositions.keySet()) {
            if (positionKey.equals(position)) {
                return true;
            }
        }
        return false;
    }

    private boolean unitAtPosition(Position position) {
        return unitStartingPositions.get(position) != null;
    }

    public Unit getPlacedUnit() {
        return placedUnit;
    }

    public List<Unit> getUnplacedUnits() {
        return unplacedUnits;
    }

    public boolean isSetupDone() {
        return unplacedUnits.size() == 0;
    }
}
