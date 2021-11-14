package main.java.edu.usue.zpie.dav.task05;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Helper {
    final static String FILENAME = Solution.FILENAME;

    public static void inputFileGenerate(int fileSizeMb) throws IOException {
        final int stringNumber = (int) (fileSizeMb / 0.5 / 0.01);
        final Random random = new Random();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, true))) {
            for (int i = 0; i < stringNumber; i++) {
                final String someString =
                        RandomStringUtils.randomAlphanumeric(random.nextInt(9999) + 1);
                writer.write(someString);
                writer.newLine();
                System.gc();
            }
        }
        System.gc();
    }
}
