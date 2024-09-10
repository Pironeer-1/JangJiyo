package org.example;

import java.util.Random;
import java.util.Scanner;

class Player extends Character {
    private int ap;

    public Player() {
        super(50, 10);
        ap = 5;
    }

    public void setStatus(int point) {
        Scanner sc = new Scanner(System.in);
        System.out.println("------------------------------------------------------------------------------");
        System.out.println(point + "만큼의 스테이터스를 추가합니다. 체력, 공격력, 마법력 순으로 입력하세요\n(1 포인트 당 체력 = 3, 공격력 = 1, 마법력 = 1 증가)\n");
        System.out.println("플레이어의 기본 스탯은 체력: 50, 공격력: 10, 마법력: 5입니다.\n");

        while (true) {
            try {
                int hp, ad, ap;
                hp = sc.nextInt();
                ad = sc.nextInt();
                ap = sc.nextInt();

                if ((hp + ad + ap) == point) {
                    this.hp += hp * 3;
                    this.ad += ad;
                    this.ap += ap;
                    System.out.println("체력: " + this.hp + ", 공격력: " + this.ad + ", 마법력: " + this.ap);
                    break;
                } else {
                    System.out.println("입력한 능력치의 총합이 " + point + "와 같아야 합니다. 다시 입력하세요.");
                }
            } catch (Exception e) {
                System.out.println("잘못된 입력입니다. 다시 시도하세요.");
                sc.nextLine();
            }
        }
    }

    public void checkStatus(Enemy enemy) {
        System.out.println("현재 유저: 체력 " + this.hp + ", 공격력 " + this.ad + ", 마법력 " + this.ap);
        System.out.println("적 유저: 체력 " + enemy.getHp() + ", 공격력 " + enemy.getAd() + ", 방어력 " + enemy.getApDefence() + ", 마법방어력 " + enemy.getApDefence());

        int damage = this.ad - enemy.getAdDefence();
        enemy.decreaseHp(damage);
        System.out.println("일반 공격으로 "+ damage + "의 데미지를 주었습니다.\n");
    }

    @Override
    public void basicAttack(Enemy enemy) {
        Random random = new Random();
        int criticalPoint = random.nextInt(10) + 1; // 1 ~ 10까지의 난수 생성

        int damage = this.ad - enemy.getAdDefence();

        if (criticalPoint > 2) {
            enemy.decreaseHp(damage);
            System.out.println("일반 공격으로 " + damage + "의 데미지를 주었습니다.");
        } else {
            damage *= 2;
            enemy.decreaseHp(damage);
            System.out.println("치명타가 적용되어 " + damage + "의 데미지를 주었습니다.");
        }
    }

     public void magicAttack(Enemy enemy) {
        int damage = this.ap * 2 - enemy.getApDefence();
        enemy.decreaseHp(damage);
        System.out.println("마법 공격으로 " + damage + "의 데미지를 주었습니다.");
     }

     @Override
     public void healSelf() {
        Random random = new Random();
        int healPoint = random.nextInt(6) + 5; // 5 ~ 10까지의 난수 생성
        this.hp += healPoint;
        System.out.println("체력을 회복합니다. 현재 hp는 " + this.hp + "입니다.");
     }

     @Override
     public void attack(Enemy enemy, int playerIndex) {
        while (true) {
            Scanner sc = new Scanner(System.in);
            try {
                System.out.println("------------------------------------------------------------------------------");
                System.out.println((playerIndex + 1) + "번 플레이어의 차례입니다. 행동을 선택하세요. (1: 스테이터스 확인 + 일반 공격, 2: 기본 공격, 3: 마법 공격, 4: 체력 회복, exit: 종료)\n");
                String strInputKey = sc.nextLine();

                if (strInputKey.equals("exit")) {
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                }
                int inputKey = Integer.parseInt(strInputKey);
                if (inputKey < 1 || inputKey > 4) {
                    System.out.println("1~4 사이의 정수를 입력해주세요.");
                    continue;
                }
                switch (inputKey) {
                    case 1:
                        this.checkStatus(enemy);
                        break;
                    case 2:
                        this.basicAttack(enemy);
                        break;
                    case 3:
                        this.magicAttack(enemy);
                        break;
                    case 4:
                        this.healSelf();
                        break;
                }
                break;
            } catch (Exception e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
            }
        }
     }
}
