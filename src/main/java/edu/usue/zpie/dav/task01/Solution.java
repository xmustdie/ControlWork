package main.java.edu.usue.zpie.dav.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Solution {
    final static String FILEPATH = String.valueOf(Paths.get(".\\src\\main\\java\\edu\\usue\\zpie" +
            "\\dav\\task01"));
    final static String FILENAME = FILEPATH + File.separator + "input.txt";

    public static void main(String[] args) throws IOException {
        if (Files.notExists(Path.of(FILENAME))) {
            Helper01.inputFileGenerate(10);
        }

        try (BufferedReader fileReader = new BufferedReader(new FileReader(FILENAME))) {
            String inputString;
            while ((inputString = fileReader.readLine()) != null) {
                System.gc();
                System.out.println("\n\nВходная строка : " + inputString);
                System.out.printf(" Память %d мб%n", (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576);
                final long start = System.currentTimeMillis();
                List<Integer> result = createResult(inputString);
                System.out.print("Результат : ");
                result.stream().map(st -> st + " ").forEach(System.out::print);
                System.out.println("\n");
                final long stop = System.currentTimeMillis();
                System.out.printf(" Память %d мб%n", (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576);
                System.out.println(" Время выполнения " + (stop - start) + " мс");
            }
        }
    }

    private static List<Integer> createResult(String inputString) {
        String[] splitString = inputString.split(" 0");
        TreeSet<String> treeSet1 = new TreeSet<>(Arrays.asList(splitString[0].trim().split(" ")));
        TreeSet<String> treeSet2 = new TreeSet<>(Arrays.asList(splitString[1].trim().split(" ")));
        List<Integer> collect = new ArrayList<>();
        for (String number : treeSet1) {
            if (!treeSet2.contains(number)) {
                collect.add(Integer.parseInt(number));
            }
        }
        for (String number : treeSet2) {
            if (!treeSet1.contains(number)) {
                collect.add(Integer.parseInt(number));
            }
        }
        collect.sort(Comparator.naturalOrder());
        return collect;
    }
}
