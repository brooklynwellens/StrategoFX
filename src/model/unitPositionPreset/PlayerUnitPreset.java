package model.unitPositionPreset;

import model.common.Position;
import model.unit.Rank;
import model.unit.Unit;
import model.unit.UnitColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerUnitPreset {

    Map<Position, Unit> unitStartingPositions;
    List<Position> positions;

    public PlayerUnitPreset() {
        positions = new ArrayList<>();
        unitStartingPositions = new HashMap<>();
        initializePositions();
    }

    private void initializePositions() {
        for (int y = 6; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                positions.add(new Position(x, y));
            }
        }
    }

    public void placeUnits() {
        unitStartingPositions.put(positions.get(0), new Unit(Rank.Captain, UnitColor.BLUE));

    }

    public Map<Position, Unit> getUnitStartingPositions() {
        return unitStartingPositions;
    }
}
