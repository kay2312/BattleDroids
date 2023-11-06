package droids;

import design.Design;

/**
 * ����������� ����, ���� ������ ��� ��� ����� �� ����������
 */
public class Droid {
    protected String name;
    protected int health;
    protected int damage;

    /**
     * �����������
     * @param name - ��'� �����
     * @param health - ������'�
     * @param damage - ����
     */
    public Droid(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String toString() {
        return ", ��'� = " + name +
                ", ������'� = " + health +
                ", ���� = " + damage;
    }

    /**
     * ����� ��� �������� �� ����� ����
     * @return true - �����, false - �������
     */
    public boolean isAlive() {
        return health > 0;
    }

    /**
     * ����� ��� ����� �� ������ �����
     */
    public void attack() {
        System.out.println(Design.ATTACK + name + " ����� � ����� " + damage + " �����.");
    }

}

