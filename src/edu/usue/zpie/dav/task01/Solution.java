package edu.usue.zpie.dav.task01;

import java.util.TreeSet;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {
        if (args.length == 0) {
            args = new String[]{"1", "2", "6", "8", "7", "3", "0", "4", "1", "6", "2", "3", "9", "0"}; //test data
            //todo generate test data and save to file
        }
        boolean firstArray = true;
        TreeSet<Integer> treeSet1 = new TreeSet<>();
        TreeSet<Integer> treeSet2 = new TreeSet<>();
        for (String arg : args) {
            Integer number = Integer.parseInt(arg);
            if (number == 0) {
                firstArray = false;
            } else {
                if (firstArray) {
                    treeSet1.add(number);
                } else {
                    treeSet2.add(number);
                }
            }
        }
        TreeSet<String> resultSet = createResult(treeSet1, treeSet2);
        resultSet.addAll(createResult(treeSet2, treeSet1));
        System.out.println(resultSet);
    }

    private static TreeSet<String> createResult(TreeSet<Integer> treeSet1,
                                                TreeSet<Integer> treeSet2) {
        return treeSet1.stream().filter(number -> !treeSet2.contains(number)).map(String::valueOf)
                .collect(Collectors.toCollection(TreeSet::new));
    }
}
