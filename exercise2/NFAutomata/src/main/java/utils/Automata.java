package utils;

import interfaces.IAutomata;
import interfaces.IState;

/**
 * @author Jacek
 *
 */
public class Automata implements IAutomata {
	private IState current;
	private String path;
	
	public Automata() {
		super();
		this.path = "";
	}

	public Automata(IState current, String path) {
		super();
		this.current = current;
		this.path = path;
	}
	
	public void buildAutomata() {
		IState S = new State("S", false);
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

		//starting transitions
		S.addTransition(new Transition("0", q0));
		S.addTransition(new Transition("1", q1));
		S.addTransition(new Transition("2", q2));
        S.addTransition(new Transition("3", q3));
        S.addTransition(new Transition("4", q4));

        S.addTransition(new Transition("a", qA));
        S.addTransition(new Transition("b", qB));
        S.addTransition(new Transition("c", qC));
        S.addTransition(new Transition("e", qE));
        S.addTransition(new Transition("f", qF));

        //transitions to final states
        q0.addTransition(new Transition("0", QC));
        q1.addTransition(new Transition("1", QC));
        q2.addTransition(new Transition("2", QC));
        q3.addTransition(new Transition("3", QC));
        q4.addTransition(new Transition("4", QC));

        qA.addTransition(new Transition("a", QL));
        qB.addTransition(new Transition("b", QL));
        qC.addTransition(new Transition("c", QL));
        qE.addTransition(new Transition("e", QL));
        qF.addTransition(new Transition("f", QL));

        //q0 to other transitions
        q0.addTransition(new Transition("1", q1));
        q0.addTransition(new Transition("2", q2));
        q0.addTransition(new Transition("3", q3));
        q0.addTransition(new Transition("4", q4));

        //q1 to other transitions
        q1.addTransition(new Transition("0", q0));
        q1.addTransition(new Transition("2", q2));
        q1.addTransition(new Transition("3", q3));
        q1.addTransition(new Transition("4", q4));

        //q2 to other transitions
        q2.addTransition(new Transition("0", q0));
        q2.addTransition(new Transition("1", q1));
        q2.addTransition(new Transition("3", q3));
        q2.addTransition(new Transition("4", q4));

        //q3 to other transitions
        q3.addTransition(new Transition("0", q0));
        q3.addTransition(new Transition("1", q1));
        q3.addTransition(new Transition("2", q2));
        q3.addTransition(new Transition("4", q4));

        //q4 to other transitions
        q4.addTransition(new Transition("0", q0));
        q4.addTransition(new Transition("1", q1));
        q4.addTransition(new Transition("2", q2));
        q4.addTransition(new Transition("3", q3));

        //qA to other transitions
        qA.addTransition(new Transition("b", qB));
        qA.addTransition(new Transition("c", qC));
        qA.addTransition(new Transition("e", qE));
        qA.addTransition(new Transition("f", qF));

        //qB to other transitions
        qB.addTransition(new Transition("a", qA));
        qB.addTransition(new Transition("c", qC));
        qB.addTransition(new Transition("e", qE));
        qB.addTransition(new Transition("f", qF));

        //qC to other transitions
        qC.addTransition(new Transition("a", qA));
        qC.addTransition(new Transition("b", qB));
        qC.addTransition(new Transition("e", qE));
        qC.addTransition(new Transition("f", qF));

        //qE to other transitions
        qE.addTransition(new Transition("a", qA));
        qE.addTransition(new Transition("b", qB));
        qE.addTransition(new Transition("c", qC));
        qE.addTransition(new Transition("f", qF));

        //qF to other transitions
        qF.addTransition(new Transition("a", qA));
        qF.addTransition(new Transition("b", qB));
        qF.addTransition(new Transition("c", qC));
        qF.addTransition(new Transition("e", qE));

        this.current = S;
	}
	

	@Override
	public IAutomata switchState(String val) {
		path += "->" + current.getId();
		return new Automata(this.current.transition(val), path);
	}

	@Override
	public boolean isFinal() {
		if (this.current.isFinal()) {
			System.out.println("Automata is in final state: " + current.getId());
			path += "->" + current.getId();
	
			System.out.println("Full path: " + path);
			return true;
		}
		else {
            System.out.println("Automata is not in final state: " + current.getId());
            System.out.println("Full path: " + path);
			return false;
		}
	}

	@Override
	public IState getCurrentState() {
		return current;
	}
}
