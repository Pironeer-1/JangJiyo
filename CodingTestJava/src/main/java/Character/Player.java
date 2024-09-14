package Character;

import Skills.*;
import org.example.Game;

import java.util.Scanner;

public class Player extends CharacterType {
    private int ap;
    SkillType skillTypes[] = SkillType.values();

    public Player() {
        super(GameValues.PLAYER_INITIAL_HP.getValue(), GameValues.PLAYER_INITIAL_AD.getValue());
        this.ap = GameValues.PLAYER_INITIAL_AP.getValue();
    }

    public int getAp() { return ap; }

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

    public void checkStatus(CharacterType target) {
        printer.printStatus(this);
        printer.printStatus(target);

        int damage = this.ad - GameValues.ENEMY_AD_DEFENCE.getValue();
        target.decreaseHp(damage);
        printer.printAttack(skillTypes[1].name(), damage);
    }

     @Override
     public void attack(CharacterType target, int playerIndex) {
        while (true) {
            Scanner sc = new Scanner(System.in);
            BasicAttack basicAttack = new BasicAttack();
            MagicAttack magicAttack = new MagicAttack();
            HealSelf healSelf = new HealSelf();
            Game game = new Game();

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
                        this.checkStatus(target);
                        break;
                    case 2:
                        game.executeSkill(this, target, basicAttack);
                        break;
                    case 3:
                        game.executeSkill(this, target, magicAttack);
                        break;
                    case 4:
                        game.executeSkill(this, healSelf);
                        break;
                }
                break;
            } catch (Exception e) {
                printer.printMessage("잘못된 입력입니다. 숫자를 입력해주세요.");
            }
        }
     }
}
