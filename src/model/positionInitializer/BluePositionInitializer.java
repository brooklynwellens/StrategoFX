package model.positionInitializer;

import model.common.Position;

import java.util.ArrayList;
import java.util.List;

public class BluePositionInitializer extends PositionInitializer {

    List<Position> positions;

    public BluePositionInitializer() {
        super();
        positions = new ArrayList<>();
        initializePositions();
    }

    @Override
    public void initializePositions() {
        for (int y = 6; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                positions.add(new Position(x, y));
            }
        }
    }

    public List<Position> getPositions() {
        return positions;
    }
}
