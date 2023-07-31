package test.task_3;

import main.task_3.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class DomainModelTest {
    Actor ford;
    Actor fish ;
    Actor arthur;

    State disgust;
    State fright;
    State surprise;

    Interaction tap;
    Interaction slide;

    @BeforeEach
    public void createAll() {
        arthur  = new Actor("Артур");
        ford = new Actor("Форд");
        fish = new Actor("Рыбка");

        disgust = new State("отвращение");
        fright = new State("ужас");
        surprise = new State("удивление");

        tap = new Interaction("хлопнуть по уху");
        slide = new Interaction("проскользнуть в слуховой канал");
    }

    @Test
    @DisplayName("Test Arthur tapping Ford")
    void testTapping() {
        tap.setSource(arthur);
        tap.setTarget(ford);
        assertEquals("[Артур] -> хлопнуть по уху -> [Форд]\n", tap.make());
    }

    @Test
    @DisplayName("Test Arthur change state after tapping")
    void testTappingStateChange() {
        tap.setSource(arthur);
        tap.setTarget(ford);
        tap.setState(disgust);
        assertEquals("[Артур] -> хлопнуть по уху -> [Форд]\n" +
                "[Форд] -> (отвращение)\n", tap.make());
    }

    @Test
    @DisplayName("Test state sequence change after fish slides")
    void testStateSequenceChange() {
        fright.setNext(surprise);
        slide.setState(fright);
        slide.setSource(fish);
        slide.setTarget(ford);

        assertEquals("[Рыбка] -> проскользнуть в слуховой канал -> [Форд]\n" +
                "[Форд] -> (ужас)\n" +
                "[Форд] (ужас) -> (удивление)\n", slide.make());
    }


    @Test
    @DisplayName("Instantiating domain model State class")
    void instantiateStateClass() {
        /* Checking initialised States objects */
        State state = new State("test name");
        assertEquals("test name", state.getName());
        state = new State("");
        assertEquals("", state.getName());
        state = new State(null);
        assertEquals("", state.getName());

        /* Checking States objects field assignments */
        state = new State("test name");
        State state2 = new State("test name next");
        state.setNext(state2);
        assertEquals("test name next", state.getNext().getName());

        /* Checking States objects field assignments with null */
        state = new State("test name");
        state.setNext(null);
        assertNull(state.getNext());
    }

    @Test
    @DisplayName("Instantiating domain model Actor class")
    void instantiateActorClass() {
        /* Checking initialised Actor objects */
        Actor actor = new Actor("test name");
        assertEquals("test name", actor.getName());
        actor = new Actor("");
        assertEquals("", actor.getName());
        actor = new Actor(null);
        assertEquals("", actor.getName());

        /* Checking States objects field assignments */
        actor = new Actor("test name");
        State state = new State("test name state");
        actor.setState(state);
        assertEquals("test name state", actor.getState().getName());

        /* Checking States objects field assignments with null */
        actor = new Actor("test name");
        actor.setState(null);
        assertNull(actor.getState());

        /* Checking States objects field assignments with state sequence */
        state = new State("test name state");
        State state2 = new State("test name state next");
        state.setNext(state2);
        actor = new Actor("test name");
        actor.setState(state);
        assertEquals("test name state next", actor.getState().getName());
    }

    @Test
    @DisplayName("Instantiating domain model Interaction class")
    void instantiateInteractionClass() {
        /* Checking initialised Actor objects */
        Interaction interaction = new Interaction("test name");
        assertEquals("test name", interaction.getName());
        interaction = new Interaction("");
        assertEquals("", interaction.getName());
        interaction = new Interaction(null);
        assertEquals("", interaction.getName());

        /* Checking States objects field assignments */
        interaction.setSource(new Actor("test source actor"));
        interaction.setTarget(new Actor("test target actor"));
        interaction.setState(new State("test state"));
        assertEquals("test source actor", interaction.getSource().getName());
        assertEquals("test target actor", interaction.getTarget().getName());
        assertEquals("test state", interaction.getState().getName());

        /* Checking States objects field assignments with null */
        interaction.setSource(null);
        interaction.setTarget(null);
        interaction.setState(null);
        assertNull(interaction.getSource());
        assertNull(interaction.getTarget());
        assertNull(interaction.getState());
    }

    @Test
    @DisplayName("Actor instances interactions and messages")
    void getActorMessages() {
        /* Checking Actor changing states */
        State state = new State("test name state");
        State state2 = new State("test name state next");
        state.setNext(state2);
        Actor actor = new Actor("test name");
        assertEquals("[test name] -> (test name state)\n"
                + "[test name] (test name state) -> (test name state next)\n", actor.setState(state));

        /* Checking Actor changing null states */
        actor = new Actor("test name");
        assertNull(actor.setState(null));

        /* Checking Actor changing null states */
        state = new State("test name state");
        state.setNext(null);
        actor = new Actor("test name");
        assertEquals("[test name] -> (test name state)\n" , actor.setState(state));
    }

    @Test
    @DisplayName("Interaction instances interactions and messages")
    void geInteractionMessages() {
        /* Checking Interaction making messages */
        Interaction interaction = new Interaction("test interaction");
        interaction.setSource(new Actor("test source actor"));
        interaction.setTarget(new Actor("test target actor"));
        interaction.setState(new State("test state"));
        assertEquals("[test source actor] -> test interaction -> [test target actor]\n" +
                "[test target actor] -> (test state)\n" , interaction.make());

        /* Checking Interaction making messages with all nulls */
        interaction = new Interaction("test interaction");
        assertEquals("test interaction\n" , interaction.make());

        /* Checking Interaction making messages with null target */
        interaction = new Interaction("test interaction");
        interaction.setSource(new Actor("test source actor"));
        assertEquals("[test source actor] -> test interaction\n" , interaction.make());

        /* Checking Interaction making messages with null source */
        interaction = new Interaction("test interaction");
        interaction.setTarget(new Actor("test target actor"));
        assertEquals("test interaction -> [test target actor]\n" , interaction.make());
    }
}