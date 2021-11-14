package main.java.edu.usue.zpie.dav.task02;

import java.util.Comparator;
import java.util.List;

class ComparatorByFields implements Comparator<Record> {
    private final List<Integer> priority;

    public ComparatorByFields(List<Integer> priority) {
        this.priority = priority;
    }

    @Override
    public int compare(Record o1, Record o2) {
        int flag = 0;
        for (Integer fieldNumber : priority) {
            flag = o2.getFields().get(fieldNumber - 1) - o1.getFields().get(fieldNumber - 1);
            if (flag != 0) break;
        }
        return flag;
    }
}
