
package model.unitPositionPreset;

import model.common.Position;
import model.unit.Unit;

import java.util.Map;

public interface UnitPlacer {

    void placeUnits();
    Map<Position, Unit> getUnitPositions();

}