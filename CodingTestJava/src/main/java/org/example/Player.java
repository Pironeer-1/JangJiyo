package org.example;

import java.util.Random;
import java.util.Scanner;

class Player extends Character {
    private int ap;
    private Printer printer = new Printer();

    public Player() {
        super(50, 10);
        ap = 5;
    }
    public int getAp() { return ap;}

    public void setStatus(int point) {
        Scanner sc = new Scanner(System.in);
        printer.printDivider();
        printer.printMessage(point + "만큼의 스테이터스를 추가합니다. 체력, 공격력, 마법력 순으로 입력하세요\n(1 포인트 당 체력 = 3, 공격력 = 1, 마법력 = 1 증가)\n");
        printer.printMessage("플레이어의 기본 스탯은 체력: 50, 공격력: 10, 마법력: 5입니다.\n");

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
                    printer.printStatus(this);
                    break;
                } else {
                    printer.printMessage("입력한 능력치의 총합이 " + point + "와 같아야 합니다. 다시 입력하세요.");
                }
            } catch (Exception e) {
                printer.printMessage("잘못된 입력입니다. 다시 시도하세요.");
                sc.nextLine();
            }
        }
    }

    public void checkStatus(Enemy enemy) {
        printer.printStatus(this);
        printer.printStatus(enemy);

        int damage = this.ad - enemy.getAdDefence();
        enemy.decreaseHp(damage);
        printer.printAttack("일반 공격", damage);
    }

    @Override
    public void basicAttack(Enemy enemy) {
        Random random = new Random();
        int criticalPoint = random.nextInt(10) + 1; // 1 ~ 10까지의 난수 생성

        int damage = this.ad - enemy.getAdDefence();

        if (criticalPoint > 2) {
            enemy.decreaseHp(damage);
            printer.printAttack("일반 공격", damage);
        } else {
            damage *= 2;
            enemy.decreaseHp(damage);
            printer.printAttack("일반 공격", damage);
        }
    }

     public void magicAttack(Enemy enemy) {
        int damage = this.ap * 2 - enemy.getApDefence();
        enemy.decreaseHp(damage);
         printer.printAttack("마법 공격", damage);
     }

     @Override
     public void healSelf() {
        Random random = new Random();
        int healPoint = random.nextInt(6) + 5; // 5 ~ 10까지의 난수 생성
        this.hp += healPoint;
        printer.printHealing(this);
     }

     @Override
     public void attack(Enemy enemy, int playerIndex) {
        while (true) {
            Scanner sc = new Scanner(System.in);
            try {
                printer.printDivider();
                printer.printTurn(playerIndex);
                String strInputKey = sc.nextLine();

                if (strInputKey.equals("exit")) {
                    printer.printMessage("프로그램을 종료합니다.");
                    System.exit(0);
                }
                int inputKey = Integer.parseInt(strInputKey);
                if (inputKey < 1 || inputKey > 4) {
                    printer.printMessage("1~4 사이의 정수를 입력해주세요.");
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
                printer.printMessage("잘못된 입력입니다. 숫자를 입력해주세요.");
            }
        }
     }
}
