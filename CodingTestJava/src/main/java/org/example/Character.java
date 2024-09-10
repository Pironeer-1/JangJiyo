package org.example;

abstract class Character {
    protected int hp, ad;

    public Character(int hp, int ad) {
        this.hp = hp;
        this.ad = ad;
    }

    public int getHp() { return hp; }
    public int getAd() { return ad; }

    public void decreaseHp(int damage) {
        hp = Math.max(hp - damage, 0);
    }

    public void basicAttack(Enemy enemy) { } // player
    public void basicAttack(Player player, int playerIndex) { } // enemy

    public void attack(Enemy enemy, int playerIndex) { } // player
    public void attack(Player player, int playerIndex) { } // enemy

    public abstract void healSelf();
}
