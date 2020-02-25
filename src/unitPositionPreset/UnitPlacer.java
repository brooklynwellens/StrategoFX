package unitPositionPreset;

import common.Position;
import unit.Unit;

import java.util.Map;

public interface UnitPlacer {

    void placeUnits();
    Map<Position, Unit> getUnitPositions();

}
