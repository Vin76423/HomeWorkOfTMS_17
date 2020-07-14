package application.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Input {
    private static Scanner scanner = new Scanner(System.in);



    public static int getInt() {
        if (scanner.hasNextInt()) {
            int number = scanner.nextInt();
            scanner.nextLine();
            return number;
        }
        System.out.println(scanner.nextLine() + " - это не целое число. Поаторите ввод.");
        return getInt();
    }

    public static int getInt(String massage) {
        System.out.println(massage);
        return getInt();
    }



    public static double getDouble() {
        if (scanner.hasNextDouble()) {
            double number = scanner.nextDouble();
            scanner.nextLine();
            return number;
        }
        System.out.println(scanner.nextLine() + " - это не число с плавающей точкой. Поаторите ввод.");
        return getDouble();
    }

    public static double getDouble(String massage) {
        System.out.println(massage);
        return getDouble();
    }



    public static String getString() {
        return scanner.nextLine();
    }

    public static String getString(String massage) {
        System.out.println(massage);
        return getString();
    }



    public static List<Integer> getMarks(String msg) {
        List<Integer> marks = new ArrayList<>();
        for (String strValueMark : getString(msg).split(" ")) {
            int mark;
            try {
                mark = Integer.parseInt(strValueMark.trim());
            }catch (NumberFormatException e){
                System.out.println("Вы ввели не верное значение. Повторите ввод.");
                return getMarks(msg);
            }

            if ( mark < 0 ) {
                System.out.println("Оценка не может быть отрицательным значением. Повторите ввод.");
                return getMarks(msg);
            }

            marks.add(mark);
        }
        return marks;
    }
}
