/**
 * 
 */
package utils;

import interfaces.IAutomata;
import interfaces.IState;

/**
 * @author Jacek
 *
 */
public class Automata implements IAutomata{

	private IState current;

	public Automata(IState current) {
		super();
		this.current = current;
	}

	@Override
	public IAutomata switchState(int val) {
		System.out.println("Switching to " + current.getId());
		return new Automata(this.current.transit(val));
	}

	@Override
	public boolean canStop() {
		return this.current.isFinal();
	}
}
