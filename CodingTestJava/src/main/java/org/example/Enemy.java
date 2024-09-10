package org.example;

import java.util.Random;

class Enemy extends Character {
    private final int maxHp, adDefence, apDefence;

    public Enemy(int listNum) {
        super(100 * listNum, 25);
        this.maxHp = 100 * listNum;
        this.adDefence = 7;
        this.apDefence = 7;
    }
    public int getApDefence() { return apDefence; }
    public int getAdDefence() { return adDefence; }

    @Override
    public void basicAttack(Player player, int playerIndex) {
        player.decreaseHp(hp);
        System.out.println((playerIndex + 1) + "번 유저에게 " + ad + "의 데미지. 적의 공격으로 현재 남은 체력은 " + hp + "입니다.");
    }

    @Override
    public void healSelf() {
        int healingAmount = 7;
        if (maxHp < (hp + healingAmount)) {
            System.out.println("적이 회복했지만 적은 이미 최대체력입니다.\n");
        } else {
            hp += healingAmount;
            System.out.println("적의 회복으로 현재 적의 체력은 " + hp + "입니다.\n");
        }
    }

    @Override
    public void attack(Player player, int playerIndex) {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("적의 차례입니다.\n");

        Random random = new Random();
        int enemyAction = random.nextInt(10) + 1; // 1 ~ 10 사이의 난수 생성
        if (enemyAction == 1) {
            System.out.println("적은 섣불리 움직이지 못하고 있습니다.\n");
        } else if (2 <= enemyAction && enemyAction <= 8) {
            basicAttack(player, playerIndex);
        } else {
            healSelf();
        }
    }
}
