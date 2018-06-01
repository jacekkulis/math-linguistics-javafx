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
	private int change;
	
	public State(String id) {
		this.transitions = new ArrayList<>();
		this.setFinal(false);
		this.id = id;
	}
	
	public State(String id, boolean isFinal) {
		this.transitions = new ArrayList<>();
		this.setFinal(isFinal);
		this.id = id;
	}

	@Override
	public IState addTransition(ITransition transition) {
		this.transitions.add(transition);
		return this;
	}


	@Override
	public IState transition(int ruleValue) {
		return transitions.stream()
				.filter(state -> state.compliesRule(ruleValue))
				.map(ITransition::getState)
				.findAny()
				.orElse(null);
		
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

	@Override
	public int getChange() {
		return change;
	}

	@Override
	public void setChange(int change) {
		this.change = change;
	}
}
