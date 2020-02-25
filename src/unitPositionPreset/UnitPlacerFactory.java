package unitPositionPreset;

import unit.UnitColor;

public class UnitPlacerFactory {

    public UnitPlacer create(String type, UnitColor color) {
        return new RandomUnitPlacer(color);
    }
}
