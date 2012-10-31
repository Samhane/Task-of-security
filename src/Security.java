import java.util.ArrayList;
import java.util.Scanner;

public class Security {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Где находится исходный файл для поиска? ");
        String sourceFile = in.nextLine().trim();

        System.out.println("Введите путь для поиска");
        String path = in.nextLine().trim();

        ControlSumm controlSumm = new ControlSumm();
        controlSumm.setOriginalControlSumm(sourceFile);
        ArrayList<String> answer = controlSumm.findFile(path, false);
        for (String option : answer) {
            System.out.println("Вполне возможно, что файл " + option + " - наш искомый файл, который мы и ищем..");
        }
    }
}
