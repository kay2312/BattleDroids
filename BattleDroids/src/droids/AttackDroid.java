package droids;

import design.Design;
import java.util.Random;

/**
 * Похідний клас, певний тип дроїда - AttackDroid
 */
public class AttackDroid extends Droid {
    private final int bonusDamage;
    private final int startDamage;

    /**
     * Конструктор
     * @param bonusDamage - бонусний урон
     */
    public AttackDroid(String name, int health, int damage, int bonusDamage) {
        super(name, health, damage);
        this.bonusDamage = bonusDamage;
        this.startDamage = damage;
    }

    public String toString() {
        return "Тип дроїда = AtackDroid" + super.toString() + ", Бонусний урон = +" + bonusDamage;
    }

    /**
     * Метод для атаки та використання бонуса
     */
    public void attack() {
        useBonus();
        super.attack();
    }

    /**
     * Метод для використання бонусу з певним шансом
     */
    public void useBonus() {
        Random random = new Random();
        if(this.damage > startDamage)
            this.damage -= bonusDamage;

        if (random.nextBoolean()) {
            this.damage += bonusDamage;
            System.out.print(Design.BONUS + "Використаний бонус = +20 до урону!\n");
        }
    }
}

