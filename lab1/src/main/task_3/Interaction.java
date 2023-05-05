package main.task_3;

public class Interaction {
    private final String name;
    private Actor source;
    private Actor target;
    private State state;

    public Interaction(String name) {
        if (null == name) {
            this.name = "";
        } else {
            this.name = name;
        }
    }

    public String make() {
        String message = String.format( "%s%s%s\n",
                null != this.getSource() ? String.format("[%s] -> ", this.getSource()) : "",
                this.getName(),
                null != this.getTarget() ? String.format(" -> [%s]", this.getTarget()) : "");
        System.out.println(message);
        if (null != this.state) {
            return message + target.setState(this.state);
        } else {
            return message;
        }
    }

    public void setSource(Actor source) {
        this.source = source;
    }

    public void setTarget(Actor target) {
        this.target = target;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public Actor getSource() {
        return source;
    }

    public Actor getTarget() {
        return target;
    }

    public State getState() {
        return state;
    }

    @Override
    public String toString() {
        return name;
    }
}
