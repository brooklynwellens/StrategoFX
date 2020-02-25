package positionInitializer;

import common.Position;
import java.util.List;

public abstract class PositionInitializer implements IPositionInitializer {

    public abstract void initializePositions();

    public abstract List<Position> getPositions();


}
