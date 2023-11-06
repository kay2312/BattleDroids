package main;

import droids.*;
import battle.Battle;
import design.Design;
import file.DataFile;

import java.util.Scanner;
import java.util.Random;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Клас для виведення меню, формування дроїдів, виводу їх характеристик
 */
public class Main {
    private static final Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);

    /**
     *  Метод, який викликає основні функції для роботи гри
     */
    public static void main(String[] args) {
        while(true) {
            nameGame();
            int choice = choiceActionGame();
            int numb = numbDroids(choice);

            Droid[] yourDroids = new Droid[numb];
            Droid[] opponentDroids = new Droid[numb];
            choiceDroids(choice, numb, yourDroids, opponentDroids);

            participantsDroids(numb, yourDroids, opponentDroids);

            Battle fight = new Battle();
            fight.startRandomBattle(yourDroids, opponentDroids);

            endGame(numb, yourDroids, opponentDroids);
        }
    }

    /**
     * Метод для виведення назви гри та типів дроїдів
     */
    private static void nameGame(){
        System.out.print("\n");
        for(int i=0; i<5; i++)
            System.out.print(Design.CYAN + Design.ROBOT + "БІЙ ДРОЇДІВ" + Design.ROBOT + "\t\t" + Design.RESET);

        System.out.println("\n\nТипи дроїдів:");
        System.out.println(Design.TYPE +"Тип дроїда = AtackDroid, Здоров'я = 120-140, Урон = 30, Бонусний урон = +30");
        System.out.println(Design.TYPE +"Тип дроїда = TankDroid, Здоров'я = 180-200, Урон = 20, Бонусне здоров'я = +20");
        System.out.println(Design.TYPE +"Тип дроїда = LevelUpDroid, Здоров'я = 70-90, Урон = +20,"
                + " Бонусне здоров'я = +20 та Бонусний урон = +10");

    }

    /**
     * Метод для вибору режима гри або завершення її
     * @return choice - номер вибраного режиму
     */
    private static int choiceActionGame(){
        int choice = 0;
        while(choice <= 0 || choice > 4) {
            System.out.println("\nВведіть: \n" + Design.ARROW + "1 - хочете створити своїх дроїдів, \n" +
                    Design.ARROW + "2 - взяти дроїдів з минулої битви,\n" +
                    Design.ARROW + "3 - рандомно створити дроїдів, \n"  +
                    Design.ARROW +"4 - завершити гру.");
            System.out.print("Ваш вибір: ");
            choice = scanner.nextInt();
        }
        // завершення гри
        if (choice == 4) {
            System.out.println(Design.RED + "----------Гра завершена----------" + Design.RESET);
            System.exit(0);
        }

        return choice;
    }

    /**
     * Метод для визначення кількості дроїдів
     * @param choice - номер вибраного режиму гри
     * @return numb - кількість дроїдів
     */
    private static int numbDroids(int choice){
        File file = new File("dataFight.txt");
        int numb = 0;
        if (choice == 1 || choice == 3) {
            while(numb <= 0) {
                System.out.print(Design.NUMBER + "Введіть кількість дроїдів: ");
                numb = scanner.nextInt();
            }
        }
        // зчитування з файлу кількості дроїдів
        if (choice == 2){
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                numb = Integer.parseInt(br.readLine());
            } catch (IOException e) {
                System.out.println("Не вдалося зчитати кількість дроїдів" + e);
            }
        }

        return numb;
    }

    /**
     * Метод для формування дроїдів
     * @param choice - номер вибраного режиму гри
     * @param numb - кількість дроїдів
     * @param yourDroids - усі дроїди граця
     * @param opponentDroids - усі противники-дроїди
     */
    private static void choiceDroids(int choice, int numb, Droid[] yourDroids, Droid[] opponentDroids){
        File file = new File("dataFight.txt");
        if (choice == 1 || choice == 3) {
            DataFile dataFile = new DataFile();
            PrintWriter pw = dataFile.createFile(numb);

            for (int i = 0; i < numb; i++) {
                int yourType = 0, opponentType = 0;
                String yourName = " ", opponentName = " ";
                // гравець сам формує своїх дроїдів та противників-дроїдів
                if (choice == 1) {
                    while (yourType <= 0 || yourType > 3 || opponentType <= 0 || opponentType > 3) {
                        System.out.println(Design.ROBOT + "Виберіть тип дроїда, якого хочете створити" +
                                "(1 - AtackDroid, 2 - TankDroid, 3 - LevelUpDroid): ");
                        yourType = scanner.nextInt();

                        System.out.print(Design.NAME + "Введіть ім'я дроїда: ");
                        yourName = scanner.next();
                        yourDroids[i] = createDroid(yourType, yourName);

                        System.out.println(Design.SWORD + "Виберіть тип противника-дроїда" +
                                "(1 - AtackDroid, 2 - TankDroid, 3 - LevelUpDroid): ");
                        opponentType = scanner.nextInt();
                        opponentName = "Bot_" + (i + 1);
                        opponentDroids[i] = createDroid(opponentType, opponentName);
                    }
                }
                // рандомне формування дроїдів гравця та противника
                else if (choice == 3) {
                    yourType = random.nextInt(3) + 1;
                    yourName = "yourDroid_" + (i + 1);
                    yourDroids[i] = createDroid(yourType, yourName);

                    opponentType = random.nextInt(3) + 1;
                    opponentName = "Bot_" + (i + 1);
                    opponentDroids[i] = createDroid(opponentType, opponentName);
                }
                dataFile.writeToFile(pw, yourType, opponentType, yourName, opponentName);
            }
            dataFile.closeFile(pw);
        }
        // формування дроїдів з файлу
        if(choice == 2){
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                numb = Integer.parseInt(br.readLine()); // Кількість дроїдів
                for (int i = 0; i < numb; i++) {
                    int type = Integer.parseInt(br.readLine()); // Тип дроїда
                    String name = br.readLine(); // Ім'я дроїда

                    yourDroids[i] = createDroid(type, name);

                    type = Integer.parseInt(br.readLine()); // Тип дроїда опонента
                    name = br.readLine(); // Ім'я дроїда опонента

                    opponentDroids[i] = createDroid(type, name);
                }
            } catch (IOException e) {
                System.out.println("Помилка при відтворенні даних " + e);
            }
        }
    }

    /**
     * Метод для створення певного типу дроїда
     * @param type - тип дроїда
     * @param name - ім'я дроїда
     * @return створеного дроїда
     */
    private static Droid createDroid(int type, String name){
        int health = 0, damage = 0, bonusHealth = 0, bonusDamage = 0;

        switch (type) {
            case 1:
                health = (random.nextInt(3) + 12) * 10; // 120, 130 або 140
                damage = 30;
                bonusDamage = 20; // додатковий тимчасовий урон

                return new AttackDroid(name, health, damage, bonusDamage);

            case 2:
                health = 180 + random.nextInt(3) * 10; // 180, 190 або 200
                damage = 20;
                bonusHealth = 20; // додаткове здоров'я

                return new TankDroid(name, health, damage, bonusHealth);

            case 3:
                health = (random.nextInt(3) + 7) * 10; // 70, 80 або 90
                damage = 20;
                bonusHealth = 10; // додаткове здоров'я та урон
                bonusDamage = 20; // додатковий урон

                return new LevelUpDroid(name, health, damage, bonusHealth, bonusDamage);

            default:
                System.out.println("Немає такого дроїда");
                return new AttackDroid(name, health, damage, 0);
        }
    }

    /**
     * Метод для виведення характеристик дроїдів
     * @param numb - кількість дроїдів
     * @param yourDroids - всі дроїди гравця
     * @param opponentDroids - всі дроїди противника
     */
    private static void participantsDroids(int numb, Droid[] yourDroids, Droid[] opponentDroids){
        System.out.print(Design.BLUE + "\nХарактеристики ваших дроїдів: \n" + Design.RESET);
        printDataDroid(numb, yourDroids);

        System.out.print(Design.BLUE + "\nХарактеристики противників-дроїдів: \n" + Design.RESET);
        printDataDroid(numb, opponentDroids);
    }

    /**
     * Метод для виведення даних про дроїдів
     * @param numb - кількість дроїдів
     * @param droids - всі дроїди гравця або противника
     */
    private static void printDataDroid(int numb, Droid[] droids){
        for (int i = 0; i < numb; i++)
            System.out.println(Design.TYPE +droids[i]);
    }

    /**
     * Метод для виведення дроїдів-переможців, завершення битви
     * @param numb - кількість дроїдів
     * @param yourDroids - всі дроїди гравця
     * @param opponentDroids - всі дроїди противника
     */
    private static void endGame(int numb, Droid[] yourDroids, Droid[] opponentDroids){
        System.out.println(Design.RED + "\n---------------Битва завершена!---------------" + Design.RESET);
        System.out.println(Design.BLUE + "Вцілілі дроїди:" + Design.RESET);
        for(int i = 0; i < numb; i++){
            if(yourDroids[i].isAlive())
                System.out.println(Design.TYPE + yourDroids[i]);
            if(opponentDroids[i].isAlive())
                System.out.println(Design.TYPE + opponentDroids[i]);
        }
    }
}
