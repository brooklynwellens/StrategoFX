package game;

import common.Position;
import common.PositionGenerator;
import unit.Unit;
import unit.UnitColor;
import unit.UnitManager;

import java.util.*;

public class GameSetup {

    Map<Unit, Position> initialUnitPositions;
    UnitManager unitManager;
    PositionGenerator generator;

    public GameSetup() {
        unitManager = new UnitManager();
        generator = new PositionGenerator();
        initialUnitPositions = new TreeMap<>(new Comparator<Unit>() {
            @Override
            public int compare(Unit unit, Unit otherUnit) {
                return unit.getId() - otherUnit.getId();
            }
        });
        setInitialUnitPositions();
    }

    private void setInitialUnitPositions() {
        List<Unit> units = unitManager.getUnits();
        for (Unit unit : units) {
            if (unit.isColor(UnitColor.RED)) {
                initialUnitPositions.put(unit, null); // generate position
            } else {
                initialUnitPositions.put(unit, null);
            }
        }
    }

    private boolean isValidStartingPosition(UnitColor color, Position position) {
        if (color == UnitColor.RED) {
            return position.getX() < 10 && position.getX() >= 0 && position.getY() >= 0 && position.getY() < 4;
        }
        if (color == UnitColor.BLUE) {
            return position.getX() < 10 && position.getX() >= 0 && position.getY() >= 6 && position.getY() < 10;
        }
        return false;
    }

    public List<Unit> getUnplacedUnits() {
        List<Unit> unplacedUnits = new ArrayList<>();
        for (Map.Entry<Unit, Position> unitPositionEntry : initialUnitPositions.entrySet()) {
            if (unitPositionEntry.getValue() == null) {
                unplacedUnits.add(unitPositionEntry.getKey());
            }
        }
        return unplacedUnits;
    }

    public void setUnitPosition(Unit unit, int x, int y) {
        Position position = new Position(x, y);
        if (!isValidStartingPosition(unit.getColor(), position)) {
            return;
        }
        eraseUnitPosition(position);
        initialUnitPositions.replace(unit, position);
    }

    private void eraseUnitPosition(Position position) {
        for (Map.Entry<Unit, Position> unitPositionEntry : initialUnitPositions.entrySet()) {
            if (unitPositionEntry.getValue().equals(position)) {
                initialUnitPositions.replace(unitPositionEntry.getKey(), null);
            }
        }
    }

    public boolean isSetupDone() {
        return getUnplacedUnits().size() == 0;
    }
}
