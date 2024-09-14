package Skills;

import Character.CharacterType;
import Character.GameValues;

public class MagicAttack extends Skills {
    @Override
    public void executeSkill(CharacterType attacker, CharacterType target) {
        int damage = attacker.getAd() * 2 - GameValues.ENEMY_AP_DEFENCE.getValue();
        target.decreaseHp(damage);
        printer.printAttack(skillTypes[2].getSkill(), damage);
    }

    @Override
    public void executeSkill(CharacterType target) { }
}
