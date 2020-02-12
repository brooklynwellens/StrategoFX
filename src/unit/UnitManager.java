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
        for (UnitColor unitColor : UnitColor.values()) {
            units.add(new Unit(Rank.Flag, unitColor));
            units.add(new Unit(Rank.Spy, unitColor));
            units.add(new Unit(Rank.General, unitColor));
            units.add(new Unit(Rank.Marshal, unitColor));
            units.addAll(createUnits(Rank.Bomb, unitColor, amountOfBombs));
            units.addAll(createUnits(Rank.Scout, unitColor, amountOfScouts));
            units.addAll(createUnits(Rank.Miner, unitColor, amountOfMiners));
            units.addAll(createUnits(Rank.Sergeant, unitColor, amountOfSergeants));
            units.addAll(createUnits(Rank.Lieutenant, unitColor, amountOfLieutenants));
            units.addAll(createUnits(Rank.Captain, unitColor, amountOfCaptains));
            units.addAll(createUnits(Rank.Major, unitColor, amountOfMajors));
            units.addAll(createUnits(Rank.Colonel, unitColor, amountOfColonels));
        }
    }

    private List<Unit> createUnits(Rank rank,UnitColor color, int amount) {
        List<Unit> createdUnits = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            createdUnits.add(new Unit(rank, color));
        }
        return createdUnits;
    }

    public List<Unit> getUnits() {
        return units;
    }
}
