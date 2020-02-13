package turn;

import unit.Unit;
import unit.UnitColor;

public class Turn {

    Unit selectedUnit;
    UnitColor turnType;
    int xPos;
    int yPos;

    public Turn(UnitColor turnType) {
        this.turnType = turnType;
    }

    public Unit getSelectedUnit() {
        return selectedUnit;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public boolean isType(UnitColor turnType) {
        return this.turnType == turnType;
    }

    public void setSelectedUnit(Unit selectedUnit) {
        if (selectedUnit.isColor(turnType)) {
            this.selectedUnit = selectedUnit;
        } else {
            System.out.println("Not your unit!");
        }
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }
}
