import java.util.ArrayList;
import java.util.Scanner;

public class Security {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Где находится исходный файл для поиска? ");
        String sourceFile = in.nextLine().trim();

        System.out.println("Введите путь для поиска");
        String path = in.nextLine().trim();

        System.out.println("Поиск по контрольной сумме (1) или сигнатуре (0)? ");
        int what = in.nextInt();

        ArrayList<String> answer = new ArrayList<String>();
        ParentSecure work;

        if (what != 1 && what != 0) {
            System.out.println("Неправильный ввод");
        } else {
            if (what == 1) {
                work = new ControlSumm(sourceFile);
            } else {
                System.out.println("Введите смещение для подсчета сигнатуры: ");
                int offset = in.nextInt();
                work = new Signature(sourceFile, offset);
            }
            answer = work.findFile(path, true);
        }

        for (String option : answer) {
            System.out.println("Вполне возможно, что файл " + option + " - наш искомый файл, который мы и ищем..");
        }
    }
}
