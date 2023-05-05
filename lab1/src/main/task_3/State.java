package main.task_3;

public class State {
    private final String name;
    private State  next;

    public State(String name) {
        if (null == name) {
            this.name = "";
        } else {
            this.name = name;
        }
    }

    public String getName() {
        return name;
    }

    public State getNext() {
        return next;
    }

    public void setNext(State  next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return name;
    }
}
