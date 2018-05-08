/**
 * 
 */
package utils;

import java.util.ArrayList;
import java.util.List;

import interfaces.IState;
import interfaces.ITransition;

/**
 * @author Jacek
 *
 */
public class State implements IState{

	private String id;
	private List<ITransition> transitions;
	private boolean isFinal;
	
	public State(String id) {
		super();
		this.transitions = new ArrayList<>();
		this.setFinal(false);
		this.id = id;
	}
	
	public State(String id, boolean isFinal) {
		super();
		this.transitions = new ArrayList<>();
		this.setFinal(isFinal);
		this.id = id;
	}


	@Override
	public IState with(ITransition transition) {
		this.transitions.add(transition);
		return this;
	}


	@Override
	public IState transit(int val) {
		return transitions.stream().filter(t -> t.isPossible(val)).map(ITransition::state).findAny()
				.orElseThrow(() -> new IllegalArgumentException("Input not accepted: " + val));
	}

	@Override
	public boolean isFinal() {
		return this.isFinal;
	}
	
	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	public String getId() {
		return id;
	}
}
