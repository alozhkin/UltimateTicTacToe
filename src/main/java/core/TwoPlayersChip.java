package core;

public enum TwoPlayersChip implements Chip {
    EMPTY,
    PLAYER1,
    PLAYER2;

    @Override
    public Chip opposite() {
        if (this == PLAYER1) return PLAYER2;
        else if (this == PLAYER2) return PLAYER1;
        return TwoPlayersChip.EMPTY;
    }

    @Override
    public String toString() {
        if (this == EMPTY) {
            return "-";
        } else if (this == PLAYER1) {
            return "x";
        } else {
            return "o";
        }
    }
}

