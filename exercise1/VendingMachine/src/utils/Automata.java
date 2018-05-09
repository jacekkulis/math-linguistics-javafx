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
public class Automata implements IAutomata {
	private IState current;

	public Automata(IState current) {
		super();
		this.current = current;
	}

	@Override
	public IAutomata switchState(int val) {
		System.out.println("Switching to " + current.getId());
		return new Automata(this.current.transition(val));
	}

	@Override
	public boolean isFinal() {
		System.out.println("Automata is in final state: " + current.getId());
		return this.current.isFinal();
	}
}
