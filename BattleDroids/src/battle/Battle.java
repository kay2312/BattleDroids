package battle;

import droids.*;
import design.Design;
import java.util.Random;

/**
 * ���� ��� ���������� ����� �� �������
 */
public class Battle {
    /**
     * ����� ��� ���������� ���������� ���������� �� ��� ������ ������ �����
     * @param yourDroids - �� ����� ������
     * @param opponentDroids - �� ����� ����������
     */
    public void startRandomBattle(Droid[] yourDroids, Droid[] opponentDroids) {
        Random random = new Random();
        System.out.println();

        boolean firstAttacks = random.nextBoolean();

        int numbDeathYourDroids = 0, numbDeathOpponentDroids = 0, numb = 0;

        System.out.println(Design.RED + "\n---------------����� ����������!---------------" + Design.RESET);

        while(true) {

            int randomYourDroid = random.nextInt(yourDroids.length);
            int randomOpponentDroid = random.nextInt(opponentDroids.length);

            if (yourDroids[randomYourDroid].isAlive() && opponentDroids[randomOpponentDroid].isAlive()) {
                numb++;
                System.out.println(Design.PURPLE + "\n----------------��� " + numb + "\n" + Design.RESET);
                System.out.println(Design.BLUE + "��������:" + Design.RESET);
                System.out.println(Design.TYPE + yourDroids[randomYourDroid]);
                System.out.println(Design.TYPE + opponentDroids[randomOpponentDroid]);

                // ��� ������ ������ �����
                if (firstAttacks)
                    battle(yourDroids[randomYourDroid], opponentDroids[randomOpponentDroid]);
                else
                    battle(opponentDroids[randomOpponentDroid], yourDroids[randomYourDroid]);

                // ��������, ��� �����
                if (!yourDroids[randomYourDroid].isAlive())
                    numbDeathYourDroids++;
                else
                    numbDeathOpponentDroids++;


                System.out.println(Design.YELLOW + "\n--------------������� "
                        + numbDeathOpponentDroids + ":" + numbDeathYourDroids + Design.RESET);

            }

            if(numbDeathYourDroids == yourDroids.length || numbDeathOpponentDroids == opponentDroids.length)
                break;

        }

    }

    /**
     * ����� ��� ���������� ��� �� ����� �������
     * @param firstDroid - ������ ����
     * @param secondDroid - ��������� ������� �����
     */
    private static void battle(Droid firstDroid, Droid secondDroid) {

        int round = 0;

        while (firstDroid.isAlive() && secondDroid.isAlive()) {
            // ����� ���
            round++;
            System.out.println(Design.GREEN + "------------����� " + round + "------------\n" + Design.RESET);
            attackDroid(firstDroid, secondDroid);


            // ��������, �� ��������� �� �����
            if (!secondDroid.isAlive()) {
                printVictory (firstDroid);
                break;
            }

            attackDroid(secondDroid, firstDroid);

            if (!firstDroid.isAlive()) {
                printVictory (secondDroid);
                break;
            }
        }
    }

    /**
     * ������ ��� ����� ������ ����� �� ������
     * @param firstDroid - ����, ���� �����
     * @param secondDroid - ����, ����� ��������
     */
    private static void attackDroid(Droid firstDroid, Droid secondDroid){
        firstDroid.attack();
        secondDroid.setHealth(secondDroid.getHealth()-firstDroid.getDamage());
        System.out.println(Design.HEALTH + "���� " + secondDroid.getName() + " �� " + secondDroid.getHealth()
                + " ������'�.\n");
    }

    /**
     * ����� ��� ��������� ��������� �����
     * @param droid - ����-����������
     */
    private static void printVictory (Droid droid) {
        System.out.println(Design.VICTORY + "���� - " + droid.getName() + " ������!");
        System.out.println(Design.TYPE + droid);
    }
}

