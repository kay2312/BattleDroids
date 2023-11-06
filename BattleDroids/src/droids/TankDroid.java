package droids;

import design.Design;
import java.util.Random;

/**
 * Похідний клас, певний тип дроїда - TankDroid
 */
public class TankDroid extends Droid {
    private int bonusHealth;

    /**
     * Конструктор
     * @param bonusHealth - - бонусне здоров'я
     */
    public TankDroid(String name, int health, int damage, int bonusHealth) {
        super(name, health, damage);
        this.bonusHealth = bonusHealth;
    }

    public String toString() {
        return "Тип дроїда = TankDroid" + super.toString() + ", Бонусне здоров'я = +" + bonusHealth;
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

        if (random.nextBoolean()) {
            this.health += bonusHealth;
            System.out.print(Design.BONUS + "Використаний бонус = +20 до здоров'я!\n");
        }
    }
}

