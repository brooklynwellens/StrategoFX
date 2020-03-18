package model.board;

public class Tile {

    private int unitId;
    private final Surface surface;

    public Tile(Surface surface) {
        this.surface = surface;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public void clearUnitId() {
        unitId = 0;
    }

    public boolean isOccupied() {
        return unitId > 0;
    }

    public boolean isAccessible() {
        return this.surface.isAccessible();
    }

    public int getUnitId() {
        return unitId;
    }
}
