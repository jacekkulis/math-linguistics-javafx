/**
 * 
 */
package utils;

import interfaces.IAutomata;
import interfaces.IState;
import models.Coffee;
import models.Product;
import models.Tea;

/**
 * @author Jacek
 *
 */
public class Automata implements IAutomata {
	private IState current;
	private Product product;
	
	public Automata() {
		super();
	}

	public Automata(IState current) {
		super();
		this.current = current;
	}
	
	public void buildAutomata() {
		IState q0 = new State("q0", false);
		IState q1 = new State("q1", false);
		IState q2 = new State("q2", false);
		IState q3 = new State("q3", false);
		IState q4 = new State("q4", false);
		IState q5 = new State("q5", false);
		IState q6 = new State("q6", false);
		IState q7 = new State("q7", false);
		IState q8 = new State("q8", false);
		
		if (this.product instanceof Tea) {
			System.out.println("It is tea");
			q5.setFinal(true);
			q6.setFinal(true);
			q7.setFinal(true);
			q8.setFinal(true);
		}
		else if (this.product instanceof Coffee) {
			System.out.println("It is coffee");
			q7.setFinal(true);
			q8.setFinal(true);
		}

		q0.addTransition(new Transition(1, q1));
		q0.addTransition(new Transition(2, q2));
		q0.addTransition(new Transition(5, q5));

		q1.addTransition(new Transition(1, q2));
		q1.addTransition(new Transition(2, q3));
		q1.addTransition(new Transition(5, q6));

		q2.addTransition(new Transition(1, q3));
		q2.addTransition(new Transition(2, q4));
		q2.addTransition(new Transition(5, q7));

		q3.addTransition(new Transition(1, q4));
		q3.addTransition(new Transition(2, q5));
		q3.addTransition(new Transition(5, q8));

		q4.addTransition(new Transition(1, q5));
		q4.addTransition(new Transition(2, q6));
		q4.addTransition(new Transition(5, q8));

		q5.addTransition(new Transition(1, q6));
		q5.addTransition(new Transition(2, q7));
		q5.addTransition(new Transition(5, q8));

		q6.addTransition(new Transition(1, q7));
		q6.addTransition(new Transition(2, q8));
		q6.addTransition(new Transition(5, q8));

		q7.addTransition(new Transition(1, q8));
		q7.addTransition(new Transition(2, q8));
		q7.addTransition(new Transition(5, q8));

		q8.addTransition(new Transition(1, q8));
		q8.addTransition(new Transition(2, q8));
		q8.addTransition(new Transition(5, q8));

		this.current = q0;
	}
	

	@Override
	public IAutomata switchState(int val) {
		System.out.println("Switching to " + current.getId());
		return new Automata(this.current.transition(val));
	}

	@Override
	public boolean isFinal() {
		if (this.current.isFinal()) {
			System.out.println("Automata is in final state: " + current.getId());
			return true;
		}
		else {
			return false;
		}
	}

	public IState getCurrent() {
		return current;
	}

	public void setCurrent(IState current) {
		this.current = current;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
