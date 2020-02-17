package turn;

import common.Position;
import unit.Unit;
import unit.UnitColor;

public class Turn {

    Unit selectedUnit;
    UnitColor turnType;
    Position start;
    Position destination;

    public Turn(UnitColor turnType) {
        this.turnType = turnType;
        this.selectedUnit = null;
    }

    public Unit getSelectedUnit() {
        return selectedUnit;
    }

    public UnitColor getTurnType() {
        return turnType;
    }

    public boolean isType(UnitColor turnType) {
        return this.turnType == turnType;
    }

    public Position getStart() {
        return start;
    }

    public Position getDestination() {
        return destination;
    }

    public void setSelectedUnit(Unit selectedUnit) {
        if (selectedUnit == null) {
            System.out.println("No unit at this position");
            return;
        }
        if (selectedUnit.isColor(turnType)) {
            this.selectedUnit = selectedUnit;
            System.out.println("Selected: " + selectedUnit);
        } else {
            this.selectedUnit = null;
            System.out.println("Not your unit!");
        }
    }

    public void setStart(Position start) {
        this.start = start;
    }

    public void setDestination(Position destination) {
        this.destination = destination;
    }

    public boolean isUnitSelected() {
        return this.selectedUnit != null;
    }
}
