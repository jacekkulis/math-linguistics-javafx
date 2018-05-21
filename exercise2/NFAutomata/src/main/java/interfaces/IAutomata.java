/**
 * 
 */
package interfaces;

import java.util.List;

/**
 * @author Jacek
 *
 */
public interface IAutomata {
	void buildAutomata();
	List<ITransition> findAllTransitions(IState current, String ruleVal);

    boolean checkIfAccepts(IState currentState, String input, StringBuilder steps);

	void accepts(String input);
}
