package main.java.edu.usue.zpie.dav.task02;

import java.util.List;

public class Record {
    private final String name;
    private final List<Integer> fields;

    public Record(String name, List<Integer> fields) {
        this.name = name;
        this.fields = fields;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getFields() {
        return fields;
    }
}
