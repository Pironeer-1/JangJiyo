package Skills;

import java.util.Random;
import Character.*;

public class HealSelf extends Skills {
    @Override
    public void executeSkill(CharacterType target) {
        int healPoint;
        if (target instanceof Player) {
            Random random = new Random();
            healPoint = random.nextInt(6) + 5; // 5 ~ 10까지의 난수 생성
            target.setHp(target.getHp() + healPoint);
            printer.printHealing(target);
        } else {
            healPoint = GameValues.ENEMY_HEALINGAMOUNT.getValue();
            if (target.getMaxHp() >= (target.getHp() + healPoint)) {
                target.setHp(target.getHp() + healPoint);
            }
            printer.printHealing(target, healPoint);
        }
    }

    @Override
    public void executeSkill(CharacterType attacker, CharacterType target) { }
}
