package droids;

import design.Design;
import java.util.Random;

/**
 * �������� ����, ������ ��� ����� - AttackDroid
 */
public class AttackDroid extends Droid {
    private final int bonusDamage;
    private final int startDamage;

    /**
     * �����������
     * @param bonusDamage - �������� ����
     */
    public AttackDroid(String name, int health, int damage, int bonusDamage) {
        super(name, health, damage);
        this.bonusDamage = bonusDamage;
        this.startDamage = damage;
    }

    public String toString() {
        return "��� ����� = AtackDroid" + super.toString() + ", �������� ���� = +" + bonusDamage;
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
        if(this.damage > startDamage)
            this.damage -= bonusDamage;

        if (random.nextBoolean()) {
            this.damage += bonusDamage;
            System.out.print(Design.BONUS + "������������ ����� = +20 �� �����!\n");
        }
    }
}

