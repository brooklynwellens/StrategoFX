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
            for (int i = 0; i < amountOfBombs; i++) {
                units.add(new Unit(Rank.Bomb, unitColor));
            }
            for (int i = 0; i < amountOfScouts; i++) {
                units.add(new Unit(Rank.Scout, unitColor));
            }
            for (int i = 0; i < amountOfMiners; i++) {
                units.add(new Unit(Rank.Miner, unitColor));
            }
            for (int i = 0; i < amountOfSergeants; i++) {
                units.add(new Unit(Rank.Sergeant, unitColor));
            }
            for (int i = 0; i < amountOfLieutenants; i++) {
                units.add(new Unit(Rank.Lieutenant, unitColor));
            }
            for (int i = 0; i < amountOfCaptains; i++) {
                units.add(new Unit(Rank.Captain, unitColor));
            }
            for (int i = 0; i < amountOfMajors; i++) {
                units.add(new Unit(Rank.Major, unitColor));
            }
            for (int i = 0; i < amountOfColonels; i++) {
                units.add(new Unit(Rank.Colonel, unitColor));
            }
        }
    }

    public List<Unit> getUnits() {
        return units;
    }
}
