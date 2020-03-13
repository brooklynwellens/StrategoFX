package model.positionInitializer;

import model.common.Position;

import java.util.ArrayList;
import java.util.List;

public class RedPositionInitializer implements IPositionInitializer {

    List<Position> positions;

    public RedPositionInitializer() {
        super();
        positions = new ArrayList<>();
        initializePositions();
    }

    @Override
    public List<Position> initializePositions() {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 10; x++) {
                positions.add(new Position(x, y));
            }
        }
        return positions;
    }
}