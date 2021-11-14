package main.java.edu.usue.zpie.dav.task01;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class Helper01 {
    final static String FILENAME = Solution.FILENAME;

    public static void inputFileGenerate(int countNumbers) throws IOException {

        final Random random = new Random();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, true))) {
            for (int j = 1; j < 11; j++) {
                StringBuilder someString = new StringBuilder();
                for (int i = 0; i < countNumbers * j; i++) {
                    if (i == (countNumbers * j / 2)) {
                        someString.append(" 0");
                    }
                    someString.append(i != 0 ? " " : "").append(random.nextInt(2000 * j));
                }
                writer.write(someString.toString());
                writer.newLine();
                System.gc();
            }
        }
        System.gc();
    }
}

