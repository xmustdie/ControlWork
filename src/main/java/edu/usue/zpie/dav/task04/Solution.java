package main.java.edu.usue.zpie.dav.task04;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Solution {
//    private static final Map<String, Integer> posMapBegin = new HashMap<>();
    private static final Map<String, Integer> commentCount = new HashMap<>();

    static {
//        posMapBegin.put("(*", null);
//        posMapBegin.put("{", null);
//        posMapBegin.put("//", null);
//        posMapBegin.put("'", null);

        commentCount.put("(*", 0);
        commentCount.put("{", 0);
        commentCount.put("//", 0);
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String filename = scanner.nextLine();
        List<String> allLines = Files.readAllLines(Path.of(filename), StandardCharsets.US_ASCII);
        for (String line : allLines) {
            findTextBlock(line);
        }
        System.out.println(commentCount);
    }

    private static void findTextBlock(String line) {
        int end;
        for (int i = 0; i < line.length(); i++) {
            char myChar = line.charAt(i);
            switch (myChar) {
                case '(':
                    if (line.charAt(i + 1) == '*') {
                        end = line.indexOf("*)", i + 1);
                        if (end > i + 1) {
                            commentCount.computeIfPresent("(*", (a, b) -> b + 1);
                            i = end;
                        }
                    }
                    break;
                case '/':
                    if (line.charAt(i + 1) == '/') {
                        end = line.indexOf(System.lineSeparator(), i + 1);
                        if (end > i + 1) {
                            commentCount.computeIfPresent("//", (a, b) -> b + 1);
                            i = line.length() - 1;
                        }
                    }
                    break;
                case (char) (39):
                    end = line.indexOf((char)39, i + 1);
                    if (end > i + 1) {
                        commentCount.computeIfPresent("'", (a, b) -> b + 1);
                        i = end;
                    }
                    break;
                case '{':
                    end = line.indexOf("}", i + 1);
                    if (end > i + 1) {
                        commentCount.computeIfPresent("}", (a, b) -> b + 1);
                        i = end;
                    }
                    break;
            }
        }
    }
}
