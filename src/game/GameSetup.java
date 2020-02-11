package game;

import common.Position;
import javafx.geometry.Pos;
import unit.Rank;
import unit.Unit;
import unit.UnitColor;

import java.util.*;

public class GameSetup {

    Map<Unit, Position> initialUnitPositions;

    public GameSetup() {
        initialUnitPositions = new TreeMap<>(new Comparator<Unit>() {
            @Override
            public int compare(Unit unit, Unit otherUnit) {
                return unit.getId() - otherUnit.getId();
            }
        });
        initializeUnits();
    }

    private void initializeUnits() {
        for (UnitColor unitColor : UnitColor.values()) {
            for (Rank rank : Rank.values()) {
                for (int i = 0; i < rank.getAmountOf(); i++) {
                    if (unitColor == UnitColor.RED) {
                        initialUnitPositions.put(new Unit(rank, unitColor), null);
                    } else {
                        initialUnitPositions.put(new Unit(rank, unitColor), null);
                    }

                }
            }
        }
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

    private boolean isValidStartingPosition(UnitColor color, Position position) {
        if (color == UnitColor.RED) {
            return position.getX() < 10 && position.getX() >= 0 && position.getY() >= 0 && position.getY() < 4;
        }
        if (color == UnitColor.BLUE) {
            return position.getX() < 10 && position.getX() >= 0 && position.getY() >= 6 && position.getY() < 10;
        }
        return false;
    }

    public boolean isSetupDone() {
        return getUnplacedUnits().size() == 0;
    }
}
