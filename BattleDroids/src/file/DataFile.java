package file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ���� ��� ��������� ����� �� ������ � ����� ���������� ��� �����
 */
public class DataFile {
    private static final String fileName = "dataFight.txt";
    private static final File file = new File(fileName);

    /**
     * ����� ��� ��������� ����� �� ������ ������� �����
     * @param numb - ������� �����
     */
    public PrintWriter createFile(int numb){
        try {
            if (!file.exists())
                file.createNewFile();

            PrintWriter pw = new PrintWriter(file);
            pw.println(numb);
            return pw;
        } catch (IOException e) {
            System.out.println("������� ��� ����� � ������ " + e);
        }
        return null;
    }

    /**
     * ����� ��� ������ ����� �����
     * @param yourType - ��� ����� ������
     * @param opponentType - ��� �����-����������
     * @param yourName - ��'� ������ �����
     * @param opponentName - ��'� �����-����������
     */
    public void writeToFile(PrintWriter pw, int yourType, int opponentType, String yourName, String opponentName){
        pw.println(yourType);
        pw.println(yourName);
        pw.println(opponentType);
        pw.println(opponentName);
    }

    /**
     * ����� ��� �������� �����
     */
    public void closeFile(PrintWriter pw){
        pw.close();
    }
}
