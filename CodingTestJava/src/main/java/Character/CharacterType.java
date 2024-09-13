package Character;

public abstract class CharacterType {
    protected int hp;
    protected int ad;
    protected int maxHp;

    CharacterType(int hp, int ad) {
        this.hp = hp;
        this.ad = ad;
        this.maxHp = hp;
    }

    public int getHp() { return hp; }
    public int getAd() { return ad; }
    public int getMaxHp() { return maxHp; }
    public void setHp(int hp) {
        this.hp = hp;
    }

    public void decreaseHp(int damage) {
        hp = Math.max(hp - damage, 0);
    }

    public void attack(Enemy enemy, int playerIndex) { } // player
    public void attack(Player player, int playerIndex) { } // enemy
}
