package Character;

import Output.Printer;
import Skills.*;
import org.example.Game;

import java.util.Random;

public class Enemy extends CharacterType {
    private Printer printer = new Printer();

    public Enemy(int listNum) {
        super(100 * listNum, 25);
        this.maxHp = GameValues.ENEMY_INITIAL_MAXHP.getValue() * listNum;
    }

    @Override
    public void attack(Player player, int playerIndex) {
        printer.printDivider();
        printer.printMessage("적의 차례입니다.\n");

        BasicAttack basicAttack = new BasicAttack();
        HealSelf healSelf = new HealSelf();
        Game game = new Game();

        Random random = new Random();
        int enemyAction = random.nextInt(10) + 1; // 1 ~ 10 사이의 난수 생성
        if (enemyAction == 1) {
            printer.printMessage("적은 섣불리 움직이지 못하고 있습니다.\n");
        } else if (2 <= enemyAction && enemyAction <= 8) {
            game.executeSkill(this, player, basicAttack);
        } else {
            game.executeSkill(this, this, healSelf);
        }
    }
}
