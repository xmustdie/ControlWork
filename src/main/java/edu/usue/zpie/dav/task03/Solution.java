package main.java.edu.usue.zpie.dav.task03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int length = 0;
        int count = 0;
        List<Long> dots = new ArrayList<>();
        String inputParams = reader.readLine();
        String[] paramsArray = inputParams.split(" ");
        if (paramsArray.length != 2) throw new NumberFormatException();
        else {
            try {
                length = Integer.parseInt(paramsArray[0]);
                count = Integer.parseInt(paramsArray[1]);
                String[] inputString = reader.readLine().split(" ");
                for (String s : inputString) {
                    dots.add(Long.parseLong(s));
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        int counter = getCounter(length, count, dots);
        System.out.printf("Кол-во точек %d", counter);
    }

    private static int getCounter(int length, int count, List<Long> dots) {
        dots.sort(Comparator.naturalOrder());
        long refDot = dots.get(0);
        int counter = 1;
        for (int i = 1; i < count; i++) {
            if (dots.get(i) - refDot > length * 2L) {
                refDot = dots.get(i);
                counter++;
            }
        }
        return counter;
    }
}
