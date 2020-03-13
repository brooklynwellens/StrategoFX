package model.unit;


import model.common.Position;

public class Unit {

    private static int ID = 1;

    private final Rank rank;
    private final UnitColor color;
    private int id;
    private boolean captured = false;

    public Unit(Rank rank, UnitColor color) {
        this.rank = rank;
        this.color = color;
        this.id = ID++;
    }

    public Rank getRank() {
        return rank;
    }

    public int getId() {
        return id;
    }

    public UnitColor getColor() {
        return color;
    }

    public boolean isColor(UnitColor color) {
        return this.color == color;
    }

    public boolean canReach(Position distance) {
        return (distance.getX() <= this.rank.getMovementspeed() && distance.getY() == 0) || (distance.getY() <= this.rank.getMovementspeed() && distance.getX() == 0);
    }

    public String toString() {
        return String.format("%s - (%d) color: %s\n", this.rank.name(), this.rank.getStrength(),color);
    }

    public boolean isCaptured() {
       return captured();
    }

    public boolean captured() {
        return captured = true;
    }
}