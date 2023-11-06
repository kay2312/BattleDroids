package droids;

import design.Design;
import java.util.Random;

/**
 * �������� ����, ������ ��� ����� - TankDroid
 */
public class TankDroid extends Droid {
    private int bonusHealth;

    /**
     * �����������
     * @param bonusHealth - - ������� ������'�
     */
    public TankDroid(String name, int health, int damage, int bonusHealth) {
        super(name, health, damage);
        this.bonusHealth = bonusHealth;
    }

    public String toString() {
        return "��� ����� = TankDroid" + super.toString() + ", ������� ������'� = +" + bonusHealth;
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
            System.out.print(Design.BONUS + "������������ ����� = +20 �� ������'�!\n");
        }
    }
}

