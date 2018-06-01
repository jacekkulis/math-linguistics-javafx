package utils;

import application.MachineController;
import interfaces.IAutomata;
import interfaces.IState;
import interfaces.ITransition;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jacek
 */
public class Automata implements IAutomata {
    private String input;
    private IState current;

    private List<IState> Q;
    private List<ITransition> transitons;

    private boolean isFinal = false;

    private MachineController machineController;

    private StringBuilder builder;

    public Automata(MachineController machineController, String input) {
        super();
        Q = new ArrayList<>();
        transitons = new ArrayList<>();
        this.machineController = machineController;
        this.input = input;

    }

    public void buildAutomata() {
        IState q0 = new State("q0", false);
        IState q1 = new State("q1", false);
        IState q2 = new State("q2", false);
        IState q3 = new State("q3", true);
        IState q4 = new State("q4", false);

        Q.add(q0);
        Q.add(q1);
        Q.add(q2);
        Q.add(q3);
        Q.add(q4);

        transitons.add(new Transition(q0, "0", q1, "1", "L"));
        transitons.add(new Transition(q0, "1", q2, "0", "L"));
        transitons.add(new Transition(q0, "ϴ", q2, "1", "L"));

        transitons.add(new Transition(q1, "0", q3, "1", "L"));
        transitons.add(new Transition(q1, "1", q4, "0", "L"));
        transitons.add(new Transition(q1, "ϴ", q3, "1", "-"));

        transitons.add(new Transition(q2, "0", q4, "0", "L"));
        transitons.add(new Transition(q2, "1", q4, "1", "L"));
        transitons.add(new Transition(q2, "ϴ", q4, "0", "L"));

        transitons.add(new Transition(q3, "0", q3, "0", "L"));
        transitons.add(new Transition(q3, "1", q3, "1", "L"));
        transitons.add(new Transition(q3, "ϴ", q3, "-", "-"));

        transitons.add(new Transition(q4, "0", q3, "1", "L"));
        transitons.add(new Transition(q4, "1", q4, "0", "L"));
        transitons.add(new Transition(q4, "ϴ", q3, "1", "-"));

        this.current = q0;

        builder = new StringBuilder();
    }

    @Override
    public void compile() {
//        System.out.println("Checking input: " + input);
//        if (checkIfAccepts(current, input, builder)) {
//            return;
//        }
    }

    @Override
    public String getPath() {
        return builder.toString();
    }

    @Override
    public int getInputLenght() {
        return input.length();
    }

    @Override
    public void checkNext() {
        System.out.println("Checking input: " + input);
        checkIfAccepts(current, this.input);
        isFinal();
    }

    @Override
    public void checkIfAccepts(IState currentState, String input) {
        if (input.length() > 0) {
            List<ITransition> transitionList = findAllTransitions(currentState, String.valueOf(input.charAt(input.length() - 1)));
            for (ITransition t : transitionList) {
                this.builder = this.builder.append(" " + t);

                StringBuilder builder = new StringBuilder(input);
                builder.setCharAt(input.length() - 1, t.getWrite().charAt(0));
                input = builder.toString();

                machineController.setBoldLabelText(input.length() - 1, String.valueOf(input.charAt(input.length() - 1)));

                this.input = input.substring(0, input.length() - 1);
                this.current = t.getGetEndState();

//                if (checkIfAccepts(t.getGetEndState(), input.substring(0, input.length() - 1), currentSteps)) {
//                    return true;
//                }
            }
        }
    }

    @Override
    public boolean isFinal() {
        if (current.isFinal()) {
            System.out.println("Automata is in final state. Current state: " + current.getId() + ", full path: " + builder.toString() + "\n\n");
            isFinal = true;
            return isFinal;
        } else {
            return false;
        }
    }


    @Override
    public List<ITransition> findAllTransitions(IState currentState, String ruleValue) {
        return transitons.stream()
                .filter(t -> t.getStartState() == currentState && t.hasRule(ruleValue))
                .collect(Collectors.toList());
    }
}
