package model.turn;

import model.common.Position;
import model.exception.StrategoException;
import model.unit.Unit;
import model.unit.UnitColor;

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

    public void setSelectedUnit(Unit selectedUnit) throws StrategoException {
        if (selectedUnit == null) {
            throw new StrategoException("No friendly unit at this position");
        }
        if (selectedUnit.isColor(turnType)) {
            this.selectedUnit = selectedUnit;
        } else {
            this.selectedUnit = null;
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
