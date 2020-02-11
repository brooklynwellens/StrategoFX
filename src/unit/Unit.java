package unit;

import common.Position;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Unit implements Serializable {

    static int ID;

    private final Rank rank;
    private final UnitColor color;
    protected Position position;
    private boolean alive = true;
    private int visibleForTurns = 0;
    private int id;
    private boolean isPlaced = false;

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

    public boolean isPlaced() {
        return this.position != null;
    }

    public void battle(Unit enemyUnit) {
        Rank enemyRank = enemyUnit.getRank();
        if (rank.getBattleResult(enemyRank) == BattleResult.WIN) {
            this.place(new Position(enemyUnit.getX(), enemyUnit.getY()));
            enemyUnit.die();
            return;
        }
        if (rank.getBattleResult(enemyRank) == BattleResult.DRAW) {
            enemyUnit.die();
            die();
            return;
        }
        if (rank.getBattleResult(enemyRank) == BattleResult.LOSS) {
            die();
        }
    }

    public void die() {
        alive = false;
        position = null;
    }

    public String toString() {
        return String.format("%s - (%d) color: %s id: %d\n", this.rank.name(), this.rank.getStrength(),color,id);
    }

    public void place(Position position) {
        this.position = position;
        isPlaced = true;
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }
}