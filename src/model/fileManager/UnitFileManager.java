package model.fileManager;

import model.unit.Unit;
import model.unit.UnitManager;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class UnitFileManager {

    public List<Unit> read(String fileName, List<Unit> unitList) {
        /*List<Unit> unitList = new UnitManager().getUnits();*/
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNext()) {
                int id = scanner.nextInt();
                if (scanner.nextBoolean()) {
                    unitList.stream().filter(unit1 -> unit1.getId() == id).findAny().ifPresent(Unit::captured);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return unitList;
    }

    public void write(String fileName, List<Unit> unitList) {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (Unit unit : unitList) {
                if (unit.isCaptured()) {
                    pw.format("%d %b\n", unit.getId(), unit.isCaptured());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
