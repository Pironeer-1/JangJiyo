package Output;

import Character.CharacterType;
import Character.Enemy;
import Character.GameValues;
import Character.Player;

public class Printer {
    public void printDivider() {
        System.out.println("------------------------------------------------------------------------------");
    }

    // status 출력 함수
    public void printStatus(Player player) {
        System.out.println("현재 유저: 체력 " + player.getHp() + ", 공격력 " + player.getAd() + ", 마법력 " + player.getAp());
    }
    public void printStatus(Enemy enemy) {
        System.out.println("적 유저: 체력 " + enemy.getHp() + ", 공격력 " + enemy.getAd() + ", 방어력 " + GameValues.ENEMY_AD_DEFENCE.getValue() + ", 마법방어력 " + GameValues.ENEMY_AP_DEFENCE.getValue());
    }

    // turn 출력 함수
    public void printTurn(int playerIndex) {
        System.out.println((playerIndex + 1) + "번 플레이어의 차례입니다. 행동을 선택하세요. (1: 스테이터스 확인 + 일반 공격, 2: 기본 공격, 3: 마법 공격, 4: 체력 회복, exit: 종료)\n");
    }

    // attack 출력 함수
    public void printAttack(String attackType, int damage) {  // player -> enemy
        System.out.println(attackType + "으로 "+ damage + "의 데미지를 주었습니다.\n");
    }
    public void printAttack(CharacterType attacker, CharacterType target, int playerIndex) {  // enemy -> player
        System.out.println((playerIndex + 1) + "번 유저에게 " + attacker.getAd() + "의 데미지. 적의 공격으로 현재 남은 체력은 " + target.getHp() + "입니다.");
    }

    // healing 출력 함수
    public void printHealing(CharacterType target) {  // player
        System.out.println("체력을 회복합니다. 현재 hp는 " + target.getHp() + "입니다.");
    }
    public void printHealing(CharacterType target, int healingAmount) {  // enemy
        if (target.getMaxHp() < (target.getHp() + healingAmount)) {
            System.out.println("적이 회복했지만 적은 이미 최대체력입니다.\n");
        } else {
            System.out.println("적의 회복으로 현재 적의 체력은 " + target.getHp() + "입니다.\n");
        }
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
