package Skills;

import Character.*;
import org.example.Game;
import java.util.List;
import java.util.Random;

public class BasicAttack extends Skills {
    private int damage;

    @Override
    public void executeSkill(CharacterType attacker, CharacterType target) {
        // attacker가 player이라면
        if (attacker instanceof Player) {
            Random random = new Random();
            int criticalPoint = random.nextInt(10) + 1; // 1 ~ 10까지의 난수 생성
            damage = attacker.getAd() - GameValues.ENEMY_AD_DEFENCE.getValue();

            if (criticalPoint <= 2) {
                damage *= 2;
            }
            printer.printAttack(skillTypes[1].getSkill(), damage);
        } else { // attacker가 enemy라면
            Game game = new Game();
            List<Player> playerList = game.getPlayerList();
            damage = attacker.getHp();
            printer.printAttack(attacker, target, playerList.indexOf(target));
        }
        target.decreaseHp(damage);
    }
}
