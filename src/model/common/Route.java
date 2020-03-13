package model.common;

import java.util.ArrayList;
import java.util.List;

public class Route {

    private Position start;
    private Position destination;
    private List<Position> path;

    public Route(Position start, Position destination) {
        this.start = start;
        this.destination = destination;
        path = new ArrayList<>();
        calculatePath();
    }

    private void calculatePath() {
        int xMin = Math.min(start.getX(), destination.getX());
        int xMax = Math.max(start.getX(), destination.getX());
        int yMin = Math.min(start.getY(), destination.getY());
        int yMax = Math.max(start.getY(), destination.getY());

        for (int x = xMin; x < xMax + 1; x++) {
            for (int y = yMin; y < yMax + 1; y++) {
                path.add(new Position(x, y));
            }
        }
        if (path.size() >= 2) {
            path.remove(path.size() - 1);
        }
        path.remove(0);
    }

    public List<Position> getPath() {
        return path;
    }
}
