package org.example;

import java.util.Random;

class Enemy extends Character {
    private final int maxHp, adDefence, apDefence;
    private Printer printer = new Printer();

    public Enemy(int listNum) {
        super(100 * listNum, 25);
        this.maxHp = 100 * listNum;
        this.adDefence = 7;
        this.apDefence = 7;
    }
    public int getMaxHp() { return maxHp; }
    public int getApDefence() { return apDefence; }
    public int getAdDefence() { return adDefence; }

    @Override
    public void basicAttack(Player player, int playerIndex) {
        player.decreaseHp(hp);
        printer.printAttack(this, player, playerIndex);
    }

    @Override
    public void healSelf() {
        int healingAmount = 7;
        printer.printHealing(this, healingAmount);
        if (maxHp >= (hp + healingAmount)) {
            hp += healingAmount;
        }
        printer.printHealing(this, healingAmount);
    }

    @Override
    public void attack(Player player, int playerIndex) {
        printer.printDivider();
        printer.printMessage("적의 차례입니다.\n");

        Random random = new Random();
        int enemyAction = random.nextInt(10) + 1; // 1 ~ 10 사이의 난수 생성
        if (enemyAction == 1) {
            printer.printMessage("적은 섣불리 움직이지 못하고 있습니다.\n");
        } else if (2 <= enemyAction && enemyAction <= 8) {
            basicAttack(player, playerIndex);
        } else {
            healSelf();
        }
    }
}
