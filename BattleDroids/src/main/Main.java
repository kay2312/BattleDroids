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
 * ���� ��� ��������� ����, ���������� �����, ������ �� �������������
 */
public class Main {
    private static final Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);

    /**
     *  �����, ���� ������� ������ ������� ��� ������ ���
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
     * ����� ��� ��������� ����� ��� �� ���� �����
     */
    private static void nameGame(){
        System.out.print("\n");
        for(int i=0; i<5; i++)
            System.out.print(Design.CYAN + Design.ROBOT + "��� ��ίĲ�" + Design.ROBOT + "\t\t" + Design.RESET);

        System.out.println("\n\n���� �����:");
        System.out.println(Design.TYPE +"��� ����� = AtackDroid, ������'� = 120-140, ���� = 30, �������� ���� = +30");
        System.out.println(Design.TYPE +"��� ����� = TankDroid, ������'� = 180-200, ���� = 20, ������� ������'� = +20");
        System.out.println(Design.TYPE +"��� ����� = LevelUpDroid, ������'� = 70-90, ���� = +20,"
                + " ������� ������'� = +20 �� �������� ���� = +10");

    }

    /**
     * ����� ��� ������ ������ ��� ��� ���������� ��
     * @return choice - ����� ��������� ������
     */
    private static int choiceActionGame(){
        int choice = 0;
        while(choice <= 0 || choice > 4) {
            System.out.println("\n������: \n" + Design.ARROW + "1 - ������ �������� ���� �����, \n" +
                    Design.ARROW + "2 - ����� ����� � ������ �����,\n" +
                    Design.ARROW + "3 - �������� �������� �����, \n"  +
                    Design.ARROW +"4 - ��������� ���.");
            System.out.print("��� ����: ");
            choice = scanner.nextInt();
        }
        // ���������� ���
        if (choice == 4) {
            System.out.println(Design.RED + "----------��� ���������----------" + Design.RESET);
            System.exit(0);
        }

        return choice;
    }

    /**
     * ����� ��� ���������� ������� �����
     * @param choice - ����� ��������� ������ ���
     * @return numb - ������� �����
     */
    private static int numbDroids(int choice){
        File file = new File("dataFight.txt");
        int numb = 0;
        if (choice == 1 || choice == 3) {
            while(numb <= 0) {
                System.out.print(Design.NUMBER + "������ ������� �����: ");
                numb = scanner.nextInt();
            }
        }
        // ���������� � ����� ������� �����
        if (choice == 2){
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                numb = Integer.parseInt(br.readLine());
            } catch (IOException e) {
                System.out.println("�� ������� ������� ������� �����" + e);
            }
        }

        return numb;
    }

    /**
     * ����� ��� ���������� �����
     * @param choice - ����� ��������� ������ ���
     * @param numb - ������� �����
     * @param yourDroids - �� ����� �����
     * @param opponentDroids - �� ����������-�����
     */
    private static void choiceDroids(int choice, int numb, Droid[] yourDroids, Droid[] opponentDroids){
        File file = new File("dataFight.txt");
        if (choice == 1 || choice == 3) {
            DataFile dataFile = new DataFile();
            PrintWriter pw = dataFile.createFile(numb);

            for (int i = 0; i < numb; i++) {
                int yourType = 0, opponentType = 0;
                String yourName = " ", opponentName = " ";
                // ������� ��� ����� ���� ����� �� ����������-�����
                if (choice == 1) {
                    while (yourType <= 0 || yourType > 3 || opponentType <= 0 || opponentType > 3) {
                        System.out.println(Design.ROBOT + "������� ��� �����, ����� ������ ��������" +
                                "(1 - AtackDroid, 2 - TankDroid, 3 - LevelUpDroid): ");
                        yourType = scanner.nextInt();

                        System.out.print(Design.NAME + "������ ��'� �����: ");
                        yourName = scanner.next();
                        yourDroids[i] = createDroid(yourType, yourName);

                        System.out.println(Design.SWORD + "������� ��� ����������-�����" +
                                "(1 - AtackDroid, 2 - TankDroid, 3 - LevelUpDroid): ");
                        opponentType = scanner.nextInt();
                        opponentName = "Bot_" + (i + 1);
                        opponentDroids[i] = createDroid(opponentType, opponentName);
                    }
                }
                // �������� ���������� ����� ������ �� ����������
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
        // ���������� ����� � �����
        if(choice == 2){
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                numb = Integer.parseInt(br.readLine()); // ʳ������ �����
                for (int i = 0; i < numb; i++) {
                    int type = Integer.parseInt(br.readLine()); // ��� �����
                    String name = br.readLine(); // ��'� �����

                    yourDroids[i] = createDroid(type, name);

                    type = Integer.parseInt(br.readLine()); // ��� ����� ��������
                    name = br.readLine(); // ��'� ����� ��������

                    opponentDroids[i] = createDroid(type, name);
                }
            } catch (IOException e) {
                System.out.println("������� ��� ��������� ����� " + e);
            }
        }
    }

    /**
     * ����� ��� ��������� ������� ���� �����
     * @param type - ��� �����
     * @param name - ��'� �����
     * @return ���������� �����
     */
    private static Droid createDroid(int type, String name){
        int health = 0, damage = 0, bonusHealth = 0, bonusDamage = 0;

        switch (type) {
            case 1:
                health = (random.nextInt(3) + 12) * 10; // 120, 130 ��� 140
                damage = 30;
                bonusDamage = 20; // ���������� ���������� ����

                return new AttackDroid(name, health, damage, bonusDamage);

            case 2:
                health = 180 + random.nextInt(3) * 10; // 180, 190 ��� 200
                damage = 20;
                bonusHealth = 20; // ��������� ������'�

                return new TankDroid(name, health, damage, bonusHealth);

            case 3:
                health = (random.nextInt(3) + 7) * 10; // 70, 80 ��� 90
                damage = 20;
                bonusHealth = 10; // ��������� ������'� �� ����
                bonusDamage = 20; // ���������� ����

                return new LevelUpDroid(name, health, damage, bonusHealth, bonusDamage);

            default:
                System.out.println("���� ������ �����");
                return new AttackDroid(name, health, damage, 0);
        }
    }

    /**
     * ����� ��� ��������� ������������� �����
     * @param numb - ������� �����
     * @param yourDroids - �� ����� ������
     * @param opponentDroids - �� ����� ����������
     */
    private static void participantsDroids(int numb, Droid[] yourDroids, Droid[] opponentDroids){
        System.out.print(Design.BLUE + "\n�������������� ����� �����: \n" + Design.RESET);
        printDataDroid(numb, yourDroids);

        System.out.print(Design.BLUE + "\n�������������� ����������-�����: \n" + Design.RESET);
        printDataDroid(numb, opponentDroids);
    }

    /**
     * ����� ��� ��������� ����� ��� �����
     * @param numb - ������� �����
     * @param droids - �� ����� ������ ��� ����������
     */
    private static void printDataDroid(int numb, Droid[] droids){
        for (int i = 0; i < numb; i++)
            System.out.println(Design.TYPE +droids[i]);
    }

    /**
     * ����� ��� ��������� �����-����������, ���������� �����
     * @param numb - ������� �����
     * @param yourDroids - �� ����� ������
     * @param opponentDroids - �� ����� ����������
     */
    private static void endGame(int numb, Droid[] yourDroids, Droid[] opponentDroids){
        System.out.println(Design.RED + "\n---------------����� ���������!---------------" + Design.RESET);
        System.out.println(Design.BLUE + "����� �����:" + Design.RESET);
        for(int i = 0; i < numb; i++){
            if(yourDroids[i].isAlive())
                System.out.println(Design.TYPE + yourDroids[i]);
            if(opponentDroids[i].isAlive())
                System.out.println(Design.TYPE + opponentDroids[i]);
        }
    }
}
