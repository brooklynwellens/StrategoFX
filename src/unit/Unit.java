package unit;


import common.Position;

public class Unit {

    static int ID = 1;

    private final Rank rank;
    private final UnitColor color;
    private boolean alive = true;
    private int id;

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

    public boolean isAlive() {
        return alive;
    }

    public void die() {
        alive = false;
    }

    public boolean canReach(Position distance) {
        return (distance.getX() <= this.rank.getMovementspeed() && distance.getY() == 0) || (distance.getY() <= this.rank.getMovementspeed() && distance.getX() == 0);
    }

    public ComparisonResult getBattleResult(Unit enemyUnit) {
        return this.rank.compare(enemyUnit.rank);
    }

    public String toString() {
        return String.format("%s - (%d) color: %s id: %d\n", this.rank.name(), this.rank.getStrength(),color,id);
    }
}