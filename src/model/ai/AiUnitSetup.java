package model.ai;

import model.common.Position;
import model.unit.Unit;

import java.util.*;

public class AiUnitSetup {

    Map<Position, Unit> unitStartingPositions;
    List<Unit> unplacedUnits;
    Random rand;

    public AiUnitSetup(List<Unit> unplacedUnits) {
        rand = new Random();
        this.unplacedUnits = unplacedUnits;
        unitStartingPositions = new HashMap<>();
        initializeAiStartingPositions();
        placeUnits();
    }

    private void initializeAiStartingPositions() {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 10; x++) {
                unitStartingPositions.put(new Position(x, y), null);
            }
        }
    }

    private void placeUnits() {
        for (Position position : unitStartingPositions.keySet()) {
            Unit unitToPlace = unplacedUnits.get(rand.nextInt(unplacedUnits.size()));
            unitStartingPositions.replace(position, unitToPlace);
            unplacedUnits.remove(unitToPlace);
        }
    }

    public Map<Position, Unit> getUnitStartingPositions() {
        return unitStartingPositions;
    }
}