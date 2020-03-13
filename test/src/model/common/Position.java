package model.common;


import java.io.Serializable;
import java.util.Objects;

public class Position implements Serializable {

    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Position distanceTo(Position destination) {
        int deltaX = Math.abs(this.getX() - destination.getX());
        int deltaY = Math.abs(this.getY() - destination.getY());
        return new Position(deltaX, deltaY);
    }
}
