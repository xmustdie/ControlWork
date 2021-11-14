package main.java.edu.usue.zpie.dav.task05;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Solution {
    final static String FILEPATH = String.valueOf(Paths.get(".\\src\\main\\java\\edu\\usue\\zpie" +
            "\\dav\\task05"));
    final static String FILENAME = FILEPATH + File.separator + "input.txt";
    final static String FILE_PREFIX = FILEPATH + File.separator + "block_";
    final static String FILE_SUFFIX = ".txt";
    final static int STRING_BLOCK = 200;

    public static void main(String[] args) throws IOException {
        if (Files.notExists(Path.of(FILENAME))) {
            Helper.inputFileGenerate(25);
        }
        System.gc();
        System.out.printf(" Память %d мб%n", (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576);
        final long start = System.currentTimeMillis();
        externalSorting();
        final long stop = System.currentTimeMillis();
        System.out.printf(" Память %d мб%n", (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576);

        System.out.println(" Время выполнения " + (stop - start) + " мс");
    }

    private static void externalSorting() throws IOException {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(FILENAME))) {
            final Queue<String> fileQueue = new LinkedList<>();

            int indexFile = 0;
            String string;
            boolean eof = false;
            do {
                System.gc();
                final List<String> stringsBlock = new ArrayList<>();
                int indexString = 0;
                // read block of STRING_BLOCK string or less if EOF
                while (true) {
                    string = fileReader.readLine();
                    if (Objects.isNull(string)) {
                        eof = true;
                        break;
                    }
                    stringsBlock.add(string);
                    indexString++;
                    if (indexString == STRING_BLOCK) {
                        break;
                    }
                }
                System.gc();
                System.out.printf(" Память %d мб%n", (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576);
                // sorting block
                stringsBlock.sort(Comparator.naturalOrder());
                // write block to file
                final String outFilename = FILE_PREFIX + indexFile + FILE_SUFFIX;
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFilename, true))) {
                    for (int i = 0; i < indexString; i++) {
                        writer.write(stringsBlock.get(i));
                        writer.newLine();
                    }
                    System.gc();
//                System.out.printf(" Память %d мб%n", (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576);
                    indexFile++;
                    fileQueue.add(outFilename);
                }
            } while (!eof);

            String output;
            while (fileQueue.size() > 1) {
                output = fileQueue.size() > 2 ?
                        FILE_PREFIX + indexFile + FILE_SUFFIX :
                        FILEPATH + File.separator + "result.txt";
                sort(fileQueue.poll(), fileQueue.poll(), output);
                System.gc();
//                System.out.printf(" Память %d мб%n", (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576);
                System.gc();
                fileQueue.add(output);
                indexFile++;
            }
        }
    }

    private static void sort(final String file1, final String file2, final String outFile) throws IOException {
        try (final BufferedReader fileReader1 = new BufferedReader(new FileReader(file1));
             final BufferedReader fileReader2 = new BufferedReader(new FileReader(file2));
             final BufferedWriter fileWriter = new BufferedWriter(new FileWriter(outFile))) {
            String s1 = fileReader1.readLine();
            String s2 = fileReader2.readLine();
            while (true) {
                if (s1 == null && s2 == null) {
                    break; // файлы кончились - выходим
                }
                if (s1 != null && s2 == null) {
                    while (s1 != null) { //файл 1 кончился - доливаем остатки файла 2
                        fileWriter.write(s1);
                        fileWriter.newLine();
                        s1 = fileReader1.readLine();
                    }
                    break;
                }
                if (s1 == null) { //файл 2 кончился - доливаем остатки файла 1
                    while (s2 != null) {
                        fileWriter.write(s2);
                        fileWriter.newLine();
                        s2 = fileReader2.readLine();
                    }
                    break;
                }
                if (s1.compareTo(s2) < 0) { // сравниваем строки
                    fileWriter.write(s1); // пишем в выходной файл
                    s1 = fileReader1.readLine(); // сразу запрашиваем следующее значение
                } else if (s1.compareTo(s2) > 0) {
                    fileWriter.write(s2); // пишем в выходной файл
                    s2 = fileReader2.readLine(); // сразу запрашиваем следующее значение
                }
                fileWriter.newLine();
            }
        }
        Files.delete(Path.of(file1));
        Files.delete(Path.of(file2));
    }
}

