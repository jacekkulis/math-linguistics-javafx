package utils;

import interfaces.IState;
import interfaces.ITransition;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Jacek
 *
 */
public class State implements IState {

	private String id;
	private boolean isFinal;
	
	public State(String id) {
		this.setFinal(false);
		this.id = id;
	}
	
	public State(String id, boolean isFinal) {
		this.setFinal(isFinal);
		this.id = id;
	}

	@Override
	public boolean isFinal() {
		return this.isFinal;
	}
	
	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	public String getId() {
		return id;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(id, state.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
