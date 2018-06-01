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
		IState q0 = new State("q0", false);
		IState q1 = new State("q1", false);
		IState q2 = new State("q2", false);
		IState q3 = new State("q3", false);
		IState q4 = new State("q4", false);
		IState q5 = new State("q5", false);
		IState q6 = new State("q6", false);
		IState q7 = new State("q7", false);
		IState q8 = new State("q8", false);
		IState q9 = new State("q9", false);
		IState q10 = new State("q10", false);
		IState q11 = new State("q11", false);
		IState q12 = new State("q12", false);
		
		if (this.product instanceof Tea) {
			System.out.println("Tea has been chosen");
			q5.setFinal(true);
			q6.setFinal(true);
			q7.setFinal(true);
			q8.setFinal(true);
			q9.setFinal(true);
			
			q6.setChange(1);
			q7.setChange(2);
			q8.setChange(3);
			q9.setChange(4);
		}
		else if (this.product instanceof Coffee) {
			System.out.println("Coffee has been chosen");
			q7.setFinal(true);
			q8.setFinal(true);
			q9.setFinal(true);
			q10.setFinal(true);
			q11.setFinal(true);
			
			q8.setChange(1);
			q9.setChange(2);
			q10.setChange(3);
			q11.setChange(4);
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
		q4.addTransition(new Transition(5, q9));

		q5.addTransition(new Transition(1, q6));
		q5.addTransition(new Transition(2, q7));
		q5.addTransition(new Transition(5, q10));

		q6.addTransition(new Transition(1, q7));
		q6.addTransition(new Transition(2, q8));
		q6.addTransition(new Transition(5, q11));

		q7.addTransition(new Transition(1, q12));
		q7.addTransition(new Transition(2, q12));
		q7.addTransition(new Transition(5, q12));

		q8.addTransition(new Transition(1, q12));
		q8.addTransition(new Transition(2, q12));
		q8.addTransition(new Transition(5, q12));
		
		q9.addTransition(new Transition(1, q12));
		q9.addTransition(new Transition(2, q12));
		q9.addTransition(new Transition(5, q12));
		
		q10.addTransition(new Transition(1, q12));
		q10.addTransition(new Transition(2, q12));
		q10.addTransition(new Transition(5, q12));
		
		q11.addTransition(new Transition(1, q12));
		q11.addTransition(new Transition(2, q12));
		q11.addTransition(new Transition(5, q12));
		
		q12.addTransition(new Transition(1, q12));
		q12.addTransition(new Transition(2, q12));
		q12.addTransition(new Transition(5, q12));

		this.current = q0;
	}
	

	@Override
	public IAutomata switchState(int val) {
		System.out.println("Previous state: " + current.getId());
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
			return false;
		}
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public IState getCurrentState() {
		return current;
	}
}
