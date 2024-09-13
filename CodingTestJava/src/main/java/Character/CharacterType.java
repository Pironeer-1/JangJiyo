package Character;

import Output.Printer;

public abstract class CharacterType {
    protected int hp;
    protected int ad;
    protected int maxHp;
    protected Printer printer = new Printer();

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

    public abstract void attack(CharacterType target, int playerIndex);
}
