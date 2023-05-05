package main.task_3;


public class Actor {
    private final String name;
    private State state;

    public Actor(String name) {
        if (null == name) {
            this.name = "";
        } else {
            this.name = name;
        }
    }

    public String setState(State state) {
        String message;
        if (null == state) {
            return null;
        }

        if (null == this.state) {
            message = String.format("[%s] -> (%s)\n",
                    this.name,
                    state);
            System.out.println(message);
        }
        else {
            message = String.format("[%s] (%s) -> (%s)\n",
                    this.name,
                    this.state,
                    state);
            System.out.println(message);
        }
        this.state = state;

        if (null != this.state.getNext()) {
            return message + this.setState(this.state.getNext());
        } else {
            return message;
        }
    }

    public String getName() {
        return name;
    }

    public State getState() {
        return state;
    }

    @Override
    public String toString() {
        return name;
    }
}
