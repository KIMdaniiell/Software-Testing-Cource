package main.task_2;

import java.util.Objects;

public class ComparableUnit implements Comparable<ComparableUnit>{
    private final String name;
    private final int priority;

    public ComparableUnit(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    @Override
    public int compareTo(ComparableUnit o) {
        return this.priority - o.priority;
    }

    @Override
    public String toString() {
        return String.format("%s[%s]", this.name, this.priority);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComparableUnit that = (ComparableUnit) o;
        return priority == that.priority && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, priority);
    }
}
