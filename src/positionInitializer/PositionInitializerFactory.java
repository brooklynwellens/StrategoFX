package positionInitializer;

import unit.UnitColor;

public class PositionInitializerFactory {

    public PositionInitializer create(UnitColor color) {
        return color == UnitColor.RED ? new RedPositionInitializer() : new BluePositionInitializer();
    }
}
