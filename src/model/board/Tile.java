package model.board;

public class Tile {

    private int xPos;
    private int yPos;
    private int unitId;
    private final Surface surface;

    public Tile(int xPos, int yPos, Surface surface) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.surface = surface;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
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
