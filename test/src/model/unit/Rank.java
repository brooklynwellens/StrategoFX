package model.unit;

public enum Rank {
        Bomb(11,0), Captain(6,1), Colonel(8,1),
        Flag(0,0), General(9,1), Lieutenant(5,1),
        Major(7,1), Marshal(10,1), Miner(4,1),
        Scout(2,9), Sergeant(4,1), Spy(1,1);

        private final int strength;
        private final int movementspeed;

        Rank(int strength, int movementspeed) {
                this.strength = strength;
                this.movementspeed = movementspeed;
        }

        public int getMovementspeed() {
                return movementspeed;
        }

        public int getStrength() {
                return strength;
        }

        public ComparisonResult compare(Rank enemyRank) {
                if (this == Rank.Spy && enemyRank == Rank.Marshal) {
                        return ComparisonResult.WIN;
                }
                if (this == Rank.Miner && enemyRank == Rank.Bomb) {
                        return ComparisonResult.WIN;
                }
                if (this.strength > enemyRank.strength) {
                        return ComparisonResult.WIN;
                }

                if (this.strength == enemyRank.strength) {
                        return ComparisonResult.DRAW;
                }
                return ComparisonResult.LOSS;
        }
}
