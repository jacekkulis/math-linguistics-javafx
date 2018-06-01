package interfaces;

/**
 * @author Jacek
 *
 */
public interface IState {
	IState addTransition(ITransition transition);
	IState transition(int val);
	boolean isFinal();
	void setFinal(boolean isFinal);
	String getId();
	
	int getChange();
	void setChange(int change);
}
