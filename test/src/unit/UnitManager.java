package unit;

import java.util.ArrayList;
import java.util.List;

public class UnitManager {

    private static final int amountOfScouts = 8;
    private static final int amountOfBombs = 6;
    private static final int amountOfMiners = 5;
    private static final int amountOfSergeants = 4;
    private static final int amountOfLieutenants = 4;
    private static final int amountOfCaptains = 4;
    private static final int amountOfMajors = 3;
    private static final int amountOfColonels = 2;
    private List<Unit> units;

    public UnitManager() {
        units = new ArrayList<>();
        initializeUnits();
    }

    private void initializeUnits() {
        units.add(new Unit(Rank.Colonel, UnitColor.BLUE));
    }

    public List<Unit> getUnits() {
        return units;
    }
}
