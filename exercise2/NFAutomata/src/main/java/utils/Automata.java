package utils;

import interfaces.IAutomata;
import interfaces.IState;
import interfaces.ITransition;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jacek
 *
 */
public class Automata implements IAutomata {
	private IState current;
	private String path;

	private List<IState> Q;
    private List<ITransition> transitons;
    private IState S;
	
	public Automata() {
		super();
		this.path = "";
        Q = new ArrayList<>();
        transitons = new ArrayList<>();
	}
	
	public void buildAutomata() {
        S = new State("S", false);
		IState q0 = new State("q0", false);
		IState q1 = new State("q1", false);
		IState q2 = new State("q2", false);
		IState q3 = new State("q3", false);
		IState q4 = new State("q4", false);
		IState qA = new State("qA", false);
		IState qB = new State("qB", false);
		IState qC = new State("qC", false);
		IState qE = new State("qE", false);
		IState qF = new State("qF", false);
		IState QL = new State("QL", true);
		IState QC = new State("QC", true);

		Q.add(S);
		Q.add(q0);
        Q.add(q1);
        Q.add(q2);
        Q.add(q3);
        Q.add(q4);
        Q.add(qA);
        Q.add(qB);
        Q.add(qC);
        Q.add(qE);
        Q.add(qF);
        Q.add(QL);
        Q.add(QC);

		//starting transitions
        transitons.add(new Transition(S, "0", q0));
        //transitons.add(new Transition(S, "0", S));
        transitons.add(new Transition(S, "1", q1));
        //transitons.add(new Transition(S, "1", S));
        transitons.add(new Transition(S, "2", q2));
        transitons.add(new Transition(S, "3", q3));
        transitons.add(new Transition(S, "4", q4));

        transitons.add(new Transition(S, "a", qA));
        transitons.add(new Transition(S, "b", qB));
        transitons.add(new Transition(S, "c", qC));
        transitons.add(new Transition(S, "e", qE));
        transitons.add(new Transition(S, "f", qF));

        //transitions to final states
        transitons.add(new Transition(q0, "0", QC));
        transitons.add(new Transition(q1, "1", QC));
        transitons.add(new Transition(q2, "2", QC));
        transitons.add(new Transition(q3, "3", QC));
        transitons.add(new Transition(q4, "4", QC));

        transitons.add(new Transition(qA, "a", QL));
        transitons.add(new Transition(qB, "b", QL));
        transitons.add(new Transition(qC, "c", QL));
        transitons.add(new Transition(qE, "e", QL));
        transitons.add(new Transition(qF, "f", QL));

        //q0 to other transitions
        transitons.add(new Transition(q0, "1", q1));
        transitons.add(new Transition(q0, "2", q2));
        transitons.add(new Transition(q0, "3", q3));
        transitons.add(new Transition(q0, "4", q4));

        //q1 to other transitions
        transitons.add(new Transition(q1, "0", q1));
        transitons.add(new Transition(q1, "2", q2));
        transitons.add(new Transition(q1, "3", q3));
        transitons.add(new Transition(q1, "4", q4));

        //q2 to other transitions
        transitons.add(new Transition(q2, "0", q1));
        transitons.add(new Transition(q2, "1", q1));
        transitons.add(new Transition(q2, "3", q3));
        transitons.add(new Transition(q2, "4", q4));

        //q3 to other transitions
        transitons.add(new Transition(q3, "0", q1));
        transitons.add(new Transition(q3, "1", q1));
        transitons.add(new Transition(q3, "2", q2));
        transitons.add(new Transition(q3, "4", q4));

        //q4 to other transitions
        transitons.add(new Transition(q4, "0", q1));
        transitons.add(new Transition(q4, "1", q1));
        transitons.add(new Transition(q4, "3", q3));
        transitons.add(new Transition(q4, "4", q4));

        //qA to other transitions
        transitons.add(new Transition(qA, "b", qB));
        transitons.add(new Transition(qA, "c", qC));
        transitons.add(new Transition(qA, "e", qE));
        transitons.add(new Transition(qA, "f", qF));

        //qB to other transitions
        transitons.add(new Transition(qB, "a", qA));
        transitons.add(new Transition(qB, "c", qC));
        transitons.add(new Transition(qB, "e", qE));
        transitons.add(new Transition(qB, "f", qF));

        //qC to other transitions
        transitons.add(new Transition(qC, "a", qA));
        transitons.add(new Transition(qC, "b", qB));
        transitons.add(new Transition(qC, "e", qE));
        transitons.add(new Transition(qC, "f", qF));

        //qE to other transitions
        transitons.add(new Transition(qE, "a", qA));
        transitons.add(new Transition(qE, "b", qB));
        transitons.add(new Transition(qE, "c", qC));
        transitons.add(new Transition(qE, "f", qF));

        //qF to other transitions
        transitons.add(new Transition(qF, "a", qA));
        transitons.add(new Transition(qF, "b", qB));
        transitons.add(new Transition(qF, "c", qC));
        transitons.add(new Transition(qF, "e", qE));

        transitons.add(new Transition(QL, "a", QL));
        transitons.add(new Transition(QL, "b", QL));
        transitons.add(new Transition(QL, "c", QL));
        transitons.add(new Transition(QL, "e", QL));
        transitons.add(new Transition(QL, "f", QL));

        transitons.add(new Transition(QC, "0", QC));
        transitons.add(new Transition(QC, "1", QC));
        transitons.add(new Transition(QC, "2", QC));
        transitons.add(new Transition(QC, "3", QC));
        transitons.add(new Transition(QC, "4", QC));

        this.current = S;
	}

    @Override
    public void accepts(String input) {
        System.out.println("Checking input: " + input);
        if (checkIfAccepts(S, input, new StringBuilder())){
            return;
        }

        System.out.println("Input is not accepted.\n\n");
    }

    @Override
	public boolean checkIfAccepts(IState currentState, String input, StringBuilder steps) {
	    if (input.length() > 0){
            List<ITransition> transitionList = findAllTransitions(currentState, String.valueOf(input.charAt(0)));
            System.out.println("Possible paths: ");
            transitionList.stream().forEach(System.out::println);
            for(ITransition t : transitionList){
                StringBuilder currentSteps = new StringBuilder(steps.toString() + " " + t);
                if (checkIfAccepts(t.getGetEndState(), input.substring(1), currentSteps)){
                    return true;
                }
            }

            return false;
        }

        if(currentState.isFinal()){
	        if (currentState.getId().equals("QC")){
                System.out.println("Accepted input [digits]. Current state: " + currentState.   getId() + ", full path: " + steps.toString() + "\n\n");
            } else if (currentState.getId().equals("QL")){
                System.out.println("Accepted input [letters]. Current state: " + currentState.getId() + ", full path: " + steps.toString() + "\n\n");
            }

	        return true;
        }
        else {
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
