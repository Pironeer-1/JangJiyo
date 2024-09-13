package Skills;

import java.util.Random;
import Character.*;

public class HealSelf extends Skills {
    @Override
    public void executeSkill(CharacterType attacker, CharacterType target) {
        int healPoint;
        if (attacker instanceof Player) {
            Random random = new Random();
            healPoint = random.nextInt(6) + 5; // 5 ~ 10까지의 난수 생성
            attacker.setHp(attacker.getHp() + healPoint);
            printer.printHealing(attacker);
        } else {
            healPoint = GameValues.ENEMY_HEALINGAMOUNT.getValue();
            if (attacker.getMaxHp() >= (attacker.getHp() + healPoint)) {
                attacker.setHp(attacker.getHp() + healPoint);
            }
            printer.printHealing(attacker, healPoint);
        }
    }
}
