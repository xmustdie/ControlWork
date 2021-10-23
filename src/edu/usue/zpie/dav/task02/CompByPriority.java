package edu.usue.zpie.dav.task02;

import java.util.Comparator;
import java.util.List;

class CompByPriority implements Comparator<Record> {
    private final List<Integer> priority;

    public CompByPriority(List<Integer> priority) {
        this.priority = priority;
    }

    @Override
    public int compare(Record o1, Record o2) {
        int flag = 0;
        for (Integer integer : priority) {
            flag = o1.getFields().get(integer) - o2.getFields().get(integer);
        }
        return flag;
    }
}
