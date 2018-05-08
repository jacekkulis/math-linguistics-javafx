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
	private IState next;
	
	
	public Transition(int rule, IState next) {
		this.rule = rule;
		this.next = next;
	}

	@Override
	public boolean isPossible(int val) {
		return this.rule == val;
	}
	
	@Override
	public IState state() {
		return this.next;
	}

}
