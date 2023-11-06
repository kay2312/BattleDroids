package file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Клас для створення файлу та запису в нього інформацію про дроїдів
 */
public class DataFile {
    private static final String fileName = "dataFight.txt";
    private static final File file = new File(fileName);

    /**
     * Метод для створення файлу та запису кількості дроїдів
     * @param numb - кількість дроїдів
     */
    public PrintWriter createFile(int numb){
        try {
            if (!file.exists())
                file.createNewFile();

            PrintWriter pw = new PrintWriter(file);
            pw.println(numb);
            return pw;
        } catch (IOException e) {
            System.out.println("Помилка при роботі з файлом " + e);
        }
        return null;
    }

    /**
     * Метод для запису даних дроїда
     * @param yourType - тип дроїда гравця
     * @param opponentType - тип дроїда-противника
     * @param yourName - ім'я вашого дроїда
     * @param opponentName - ім'я дроїда-противника
     */
    public void writeToFile(PrintWriter pw, int yourType, int opponentType, String yourName, String opponentName){
        pw.println(yourType);
        pw.println(yourName);
        pw.println(opponentType);
        pw.println(opponentName);
    }

    /**
     * Метод для закриття файлу
     */
    public void closeFile(PrintWriter pw){
        pw.close();
    }
}
