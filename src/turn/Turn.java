package turn;

import common.Position;
import unit.Unit;
import unit.UnitColor;

public class Turn {

    Unit selectedUnit;
    UnitColor turnType;
    Position source;
    Position destination;

    public Turn(UnitColor turnType) {
        this.turnType = turnType;
        this.selectedUnit = null;
    }

    public Unit getSelectedUnit() {
        return selectedUnit;
    }

    public boolean isType(UnitColor turnType) {
        return this.turnType == turnType;
    }

    public Position getSource() {
        return source;
    }

    public Position getDestination() {
        return destination;
    }

    public void setSelectedUnit(Unit selectedUnit) {
        if (selectedUnit.isColor(turnType)) {
            this.selectedUnit = selectedUnit;
            System.out.println("Selected: " + selectedUnit);
        } else {
            System.out.println("Not your unit!");
        }
    }

    public void setSource(Position source) {
        this.source = source;
    }

    public void setDestination(Position destination) {
        this.destination = destination;
    }

    public boolean isUnitSelected() {
        return this.selectedUnit != null;
    }
}
