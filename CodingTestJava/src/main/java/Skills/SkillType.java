package Skills;

public enum SkillType {
    CHECKSTATUS("상태 확인"),
    BASICATTACK("일반 공격"),
    MAGICATTACK("마법 공격"),
    HEALSELF("회복");

    private final String skill;
    SkillType(String skill) {
        this.skill = skill;
    }

    public String getSkill() {
        return skill;
    }
}