package model.game;

import model.ai.AiUnitSetup;
import model.common.Position;
import model.unit.Unit;
import model.unit.UnitColor;
import model.unit.UnitManager;
import model.unitPositionPreset.PlayerUnitPreset;

import java.util.*;

public class GameSetup {

    private Map<Position, Unit> unitStartingPositions;
    private UnitManager unitManager;
    private List<Unit> unplacedUnits;
    private Unit placedUnit;

    public GameSetup() {
        unitManager = new UnitManager();
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

    public void usePlayerUnitPreset() {
        PlayerUnitPreset preset = new PlayerUnitPreset(unitManager);
        preset.placeUnits();
        unitStartingPositions.putAll(preset.getUnitStartingPositions());
        unplacedUnits.clear();
    }

    public Unit getPlacedUnit() {
        return placedUnit;
    }

    public List<Unit> getUnplacedUnits() {
        return unplacedUnits;
    }

    public Unit getUnitAtPosition(Position source) {
        for (Position position : unitStartingPositions.keySet()) {
            if (source.equals(position)) {
                return unitStartingPositions.get(position);
            }
        }
        return null;
    }

    public Map<Position, Unit> getUnitStartingPositions() {
        return unitStartingPositions;
    }
}
