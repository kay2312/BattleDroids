package droids;

import design.Design;

/**
 * Батьківський клас, який містить дані про дроїда та функціонал
 */
public class Droid {
    protected String name;
    protected int health;
    protected int damage;

    /**
     * Конструктор
     * @param name - ім'я дроїда
     * @param health - здоров'я
     * @param damage - урон
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
        return ", Ім'я = " + name +
                ", Здоров'я = " + health +
                ", Урон = " + damage;
    }

    /**
     * Метод для перевірки чи живий дроїд
     * @return true - живий, false - мертвий
     */
    public boolean isAlive() {
        return health > 0;
    }

    /**
     * Метод для атаки по іншому дроїду
     */
    public void attack() {
        System.out.println(Design.ATTACK + name + " атакує і завдає " + damage + " урону.");
    }

}

