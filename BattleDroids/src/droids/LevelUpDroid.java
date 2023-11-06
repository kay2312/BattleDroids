package droids;

import design.Design;
import java.util.Random;

/**
 * �������� ����, ������ ��� ����� - LevelUpDroid
 */
public class LevelUpDroid extends Droid {
    private final int bonusHealth;
    private final int bonusDamage;

    /**
     * �����������
     * @param bonusHealth - ������� ������'�
     * @param bonusDemage - �������� ����
     */
    public LevelUpDroid(String name, int health, int damage, int bonusHealth, int bonusDemage) {
        super(name, health, damage);
        this.bonusHealth = bonusHealth;
        this.bonusDamage = bonusDemage;
    }

    public String toString() {
        return "��� ����� = ShieldDroid" + super.toString() + ", ������� ������'� = +10 �� �������� ���� = +20";
    }

    /**
     * ����� ��� ����� �� ������������ ������
     */
    public void attack() {
        useBonus();
        super.attack();
    }

    /**
     * ����� ��� ������������ ������ � ������ ������
     */
    public void useBonus() {
        Random random = new Random();
        if (random.nextBoolean()) {
            this.health += bonusHealth;
            this.damage += bonusDamage;
            System.out.print(Design.BONUS + "������������ ������� ������'� = + 10 �� �������� ���� = + 20\n");
        }
    }
}
