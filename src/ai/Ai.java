package ai;

import common.Position;
import common.PositionGenerator;
import unit.Unit;

import java.util.List;
import java.util.Random;

public class Ai {

    private List<Unit> units;
    private Random rand;
    private PositionGenerator generator;

    public Ai(List<Unit> units) {
        this.units = units;
        this.rand = new Random();
        this.generator = new PositionGenerator();
    }

    public Unit chooseUnit() {
        boolean unitChosen = false;
        Unit chosenUnit = null;
        while (!unitChosen) {
            chosenUnit = units.get(rand.nextInt(units.size()));
            if (chosenUnit.isAlive()) {
                unitChosen = true;
            }
        }
        return chosenUnit;
    }

    public Position choosePosition() {
        return generator.generatePosition(10, 10);
    }
}
