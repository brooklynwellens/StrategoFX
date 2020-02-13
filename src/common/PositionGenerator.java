package common;

import java.util.Random;

public class PositionGenerator {

    private Random rand;

    public PositionGenerator() {
        rand = new Random();
    }

    public Position generatePosition(int xLimit, int yLimit) {
        return new Position(rand.nextInt(xLimit), rand.nextInt(yLimit));
    }
}
