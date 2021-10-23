package edu.usue.zpie.dav.task02;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int amountRecords = scanner.nextInt();
        final int numberOfFields = scanner.nextInt();
        final List<Integer> priority;
        final ArrayList<Record> records;
        priority = IntStream.range(0, numberOfFields).mapToObj(i -> scanner.nextInt()).collect(Collectors.toList());
        records = IntStream.range(0, amountRecords).mapToObj(i -> scanner.nextLine())
                .map(row -> new Record(row.substring(0, row.indexOf(" ")),
                Arrays.stream(row.substring(row.indexOf(" ")).split(" "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList())))
                .collect(Collectors.toCollection(ArrayList::new));
        Comparator<Record> multiComparator = new CompByPriority(priority);
        records.sort(multiComparator);
        records.forEach(m -> System.out.println(m.getName()));
    }

}
