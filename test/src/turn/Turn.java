package turn;

import common.Position;
import unit.Unit;

public class Turn {

    TurnType turnType;
    Unit selectedUnit;
    Position position;

    public Turn(TurnType turnType) {
        this.turnType = turnType;
    }

    public TurnType getTurnType() {
        return turnType;
    }

    public void setTurnType(TurnType turnType) {
        this.turnType = turnType;
    }

    public Unit getSelectedUnit() {
        return selectedUnit;
    }

    public void setSelectedUnit(Unit selectedUnit) {
        this.selectedUnit = selectedUnit;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
