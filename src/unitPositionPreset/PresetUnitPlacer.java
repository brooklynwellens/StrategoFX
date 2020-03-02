package unitPositionPreset;

import common.Position;
import positionInitializer.PositionInitializer;
import positionInitializer.PositionInitializerFactory;
import unit.Unit;
import unit.UnitColor;
import unit.UnitManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PresetUnitPlacer implements UnitPlacer {

    private Map<Position, Unit> unitPositions;
    private List<Unit> unplacedUnits;

    public PresetUnitPlacer(UnitColor unitColor) {
        PositionInitializerFactory factory = new PositionInitializerFactory();
        PositionInitializer positionInitializer = factory.create(unitColor);
        List<Position> positions = positionInitializer.getPositions();
        this.unitPositions = new HashMap<>();
        for (Position position : positions) {
            unitPositions.put(position, null);
        }
        UnitManager unitManager = new UnitManager();
        unplacedUnits = unitManager.getUnitsOfColor(unitColor);
    }

    @Override
    public void placeUnits() {

    }

    @Override
    public Map<Position, Unit> getUnitPositions() {
        return unitPositions;
    }
}
