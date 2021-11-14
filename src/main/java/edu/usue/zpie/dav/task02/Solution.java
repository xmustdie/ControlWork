package main.java.edu.usue.zpie.dav.task02;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int amountRecords = scanner.nextInt();
        final int numberOfFields = scanner.nextInt();
        final List<Integer> priority;
        final ArrayList<Record> records = new ArrayList<>();

        scanner.nextLine();
        final String[] splitOrder =  scanner.nextLine().split(" ");
        long usedBytes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println(" Память " + (usedBytes / 1048576) + " мб");
        priority = IntStream.range(0, numberOfFields).mapToObj(i -> Integer.parseInt(splitOrder[i])).collect(Collectors.toList());

        IntStream.range(0, amountRecords).mapToObj(i -> scanner.nextLine()).map(row -> row.split(" ")).forEach(split -> {
            String name = split[0];
            List<Integer> list =
                    Arrays.stream(split, 1, split.length).map(Integer::parseInt).collect(Collectors.toList());
            records.add(new Record(name, list));
        });

        long start = System.currentTimeMillis();
        Comparator<Record> multiComparator = new ComparatorByFields(priority);
        records.sort(multiComparator);
        records.forEach(m -> System.out.println(m.getName()));
        long stop = System.currentTimeMillis();
        usedBytes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        System.out.println(" Память " + (usedBytes / 1048576) + " мб");
        System.out.println(" Время выполнения " + (stop - start) + " мс");
    }
}
