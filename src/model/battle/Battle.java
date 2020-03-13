package model.battle;

import model.unit.Rank;
import model.unit.Unit;

public class Battle {

    private Unit attackingUnit;
    private Unit defendingUnit;
    private BattleResult result;

    public Battle(Unit attackingUnit, Unit defendingUnit) {
        this.attackingUnit = attackingUnit;
        this.defendingUnit = defendingUnit;
        process();
    }

    private void process() {

        Rank attackingRank = attackingUnit.getRank();
        Rank defendingRank = defendingUnit.getRank();
        int attackingStrength = attackingRank.getStrength();
        int defendingStrength = defendingRank.getStrength();

        if (attackingRank == Rank.Spy && defendingRank == Rank.Marshal) {
            processWin();
            return;
        }
        if (attackingRank == Rank.Miner && defendingRank == Rank.Bomb) {
            processWin();
            return;
        }
        if (attackingStrength > defendingStrength) {
            processWin();
            return;
        }
        if (attackingStrength == defendingStrength) {
            processDraw();
            return;
        }
        processLoss();
    }

    /*Status van de verloren model.unit verandert*/
    private void processWin() {
        this.result = BattleResult.WIN;
        defendingUnit.captured();
    }

    private void processDraw() {
        this.result = BattleResult.DRAW;
        attackingUnit.captured();
        defendingUnit.captured();
    }

    private void processLoss() {
        this.result = BattleResult.LOSS;
        attackingUnit.captured();
    }

    public BattleResult getResult() {
        return result;
    }
}