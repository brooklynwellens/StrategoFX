package model.positionInitializer;

import model.unit.UnitColor;

public class PositionInitializerFactory {

    public IPositionInitializer create(UnitColor color) {
        return color == UnitColor.RED ? new RedPositionInitializer() : new BluePositionInitializer();
    }
}
