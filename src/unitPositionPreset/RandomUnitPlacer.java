package unitPositionPreset;

import common.Position;
import unit.Unit;
import unit.UnitColor;
import unit.UnitManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomUnitPlacer implements UnitPlacer, PositionInitializer {

    private Map<Position, Unit> unitPositions;
    private UnitColor unitColor;
    private List<Unit> unplacedUnits;

    public RandomUnitPlacer(UnitColor unitColor) {
        this.unitColor = unitColor;
        unitPositions = new HashMap<>();
        UnitManager unitManager = new UnitManager();
        unplacedUnits = unitManager.getUnitsOfColor(unitColor);
        initializeStartingPositions();
    }

    @Override
    public void initializeStartingPositions() {
        int yStart = unitColor == UnitColor.RED ? 0 : 6;
        int yEnd = unitColor == UnitColor.RED ? 4 : 10;
        for (int y = yStart; y < yEnd; y++) {
            for (int x = 0; x < 10; x++) {
                unitPositions.put(new Position(x, y), null);
            }
        }
    }

    @Override
    public void placeUnits() {
        Random rand = new Random();
        for (Position position : unitPositions.keySet()) {
            Unit unitToPlace = unplacedUnits.get(rand.nextInt(unplacedUnits.size()));
            unitPositions.replace(position, unitToPlace);
            unplacedUnits.remove(unitToPlace);
        }
    }

    public Map<Position, Unit> getUnitPositions() {
        return unitPositions;
    }
}
