/**
 *
 */
package interfaces;

import java.util.List;

/**
 * @author Jacek
 */
public interface IAutomata {
    void buildAutomata();

    boolean isFinal();

    List<ITransition> findAllTransitions(IState current, String ruleVal);

    void checkNext();

    void checkIfAccepts(IState currentState, String input);

    void compile();

    String getPath();

    int getInputLenght();
}
