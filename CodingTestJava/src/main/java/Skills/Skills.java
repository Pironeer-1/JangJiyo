package Skills;

import Output.Printer;
import Character.*;

public abstract class Skills {
    protected Printer printer = new Printer();
    protected SkillType skillTypes[] = SkillType.values();

    public abstract void executeSkill(CharacterType attacker, CharacterType target);
}
