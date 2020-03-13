package model.turn;

import model.Exception.StrategoException;
import model.common.Position;
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

    public boolean isType(UnitColor turnType) {
        return this.turnType == turnType;
    }

    public Position getStart(){
        return start;
    }

    public UnitColor getTurnType(){
        return turnType;
    }

    public Position getDestination() {
        return destination;
    }

    public void setSelectedUnit(Unit selectedUnit) throws StrategoException {
        /*if (selectedUnit == null) {
            throw new StrategoException("No friendly unit at this position");
        }*/
        if (selectedUnit != null && !selectedUnit.isColor(turnType)) {
            throw new StrategoException("Not a friendly unit!");
        }
        if (selectedUnit != null && selectedUnit.isColor(turnType)) {
            this.selectedUnit = selectedUnit;
        } else {
            this.selectedUnit = null;
        }
    }

    public void setDestination(Position destination) {
        this.destination = destination;
    }

    public boolean isUnitSelected() {
        return this.selectedUnit != null;
    }

    public Position setStart(Position start)  {
        return this.start = start;
    }
}
