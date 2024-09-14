package Character;

public enum GameValues {
    PLAYER_INITIAL_HP(50),
    PLAYER_INITIAL_AD(10),
    PLAYER_INITIAL_AP(5),
    ENEMY_INITIAL_HP(100),
    ENEMY_INITIAL_AD(25),
    ENEMY_INITIAL_MAXHP(100),
    ENEMY_AD_DEFENCE(7),
    ENEMY_AP_DEFENCE(7),
    ENEMY_HEALINGAMOUNT(7);

    private final int value;
    GameValues(int value) {
        this.value = value;
    }

    public int getValue() { return value; }
}
