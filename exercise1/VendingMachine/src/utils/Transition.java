/**
 * 
 */
package utils;

import interfaces.IState;
import interfaces.ITransition;

/**
 * @author Jacek
 *
 */
public class Transition implements ITransition {
	private int rule;
	private IState nextState;
	
	
	public Transition(int rule, IState next) {
		this.rule = rule;
		this.nextState = next;
	}

	@Override
	public boolean compliesRule(int val) {
		return this.rule == val;
	}
	
	@Override
	public IState getState() {
		return this.nextState;
	}

}
