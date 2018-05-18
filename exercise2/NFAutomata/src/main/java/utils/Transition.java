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
	private String rule;
	private IState nextState;
	
	
	public Transition(String rule, IState next) {
		this.rule = rule;
		this.nextState = next;
	}

	@Override
	public boolean compliesRule(String val) {
		return this.rule.equals(val);
	}
	
	@Override
	public IState getState() {
		return this.nextState;
	}

}
