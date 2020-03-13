 package model.fileManager;

        import model.board.Tile;
        import model.common.Position;
        import model.Exception.StrategoException;
        import model.game.Game;
        import model.unit.Unit;
        import model.unit.UnitManager;
        import java.io.*;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;
        import java.util.Scanner;

public class GameFileManager {

    static public void save(String fileName, Game game) throws StrategoException {

        Tile[][] gameField = game.getGameField();
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (int y = 0; y < 10; y++) {
                for (int x = 0; x < 10; x++) {
                    Tile tile = gameField[x][y];
                    pw.format("%d %d %d\n", tile.getUnitId(), x, y);
                }

            }
        } catch (IOException e) {
            throw new StrategoException("Error saving file");
        }
    }

    static public Game load(String fileName) {
        UnitManager unitManager = new UnitManager();
        List<Unit> units = unitManager.getUnits();
        Map<Position, Unit> unitPositionMap = new HashMap<>();
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNext()) {
                int id = scanner.nextInt();
                Unit unit = units.stream().filter(unit1 -> unit1.getId() == id).findFirst().orElse(null);
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                if (id > 0) {
                    unitPositionMap.put(new Position(x, y), unit);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Game game = new Game(unitPositionMap);
        game.completeUnitList();
        return game;
    }
}