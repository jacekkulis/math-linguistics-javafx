package interfaces;

/**
 * @author Jacek
 *
 */
public interface IState {
	IState addTransition(ITransition transition);
	IState transition(String val);
	boolean isFinal();
	void setFinal(boolean isFinal);
	String getId();
}
