package game;

import common.Position;
import unit.Unit;
import unit.UnitColor;
import unit.UnitManager;

import java.util.*;

public class GameSetup {

    Map<Unit, Position> initialUnitPositions;
    UnitManager unitManager;

    public GameSetup() {
        unitManager = new UnitManager();
        initialUnitPositions = new TreeMap<>(new Comparator<Unit>() {
            @Override
            public int compare(Unit unit, Unit otherUnit) {
                return unit.getId() - otherUnit.getId();
            }
        });
        createInitialUnitPositions();
    }

    private void createInitialUnitPositions() {
        List<Unit> units = unitManager.getUnits();
        for (Unit unit : units) {
            if (unit.isColor(UnitColor.RED)) {
                initialUnitPositions.put(unit, null); // generate position
            } else {
                initialUnitPositions.put(unit, new Position(9,9));
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

    private Position generateRandomPosition() {
        Random rand = new Random();
        return new Position(rand.nextInt(10), rand.nextInt(10));
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
        initialUnitPositions.replace(unit, position);
    }

    public boolean isSetupDone() {
        return getUnplacedUnits().size() == 0;
    }
}
