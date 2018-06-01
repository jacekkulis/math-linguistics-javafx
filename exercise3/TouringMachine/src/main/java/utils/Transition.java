/**
 *
 */
package utils;

import interfaces.IState;
import interfaces.ITransition;

/**
 * @author Jacek
 */
public class Transition implements ITransition {
    private String write;
    private String rule;
    private IState startState;
    private IState endState;
    private String direction;


    public Transition(IState start, String rule, IState end, String write, String direction) {
        this.rule = rule;
        this.startState = start;
        this.endState = end;
        this.write = write;
        this.direction = direction;
    }

    @Override
    public boolean hasRule(String val) {
        return this.rule.equals(val);
    }

    @Override
    public IState getStartState() {
        return this.startState;
    }

    @Override
    public IState getGetEndState() {
        return this.endState;
    }

    @Override
    public String toString() {
        return "{" + startState.getId() + "}->{" + endState.getId() + "}";
    }

    @Override
    public String getRule() {
        return this.rule;
    }

    @Override
    public String getWrite() {
        return this.write;
    }
}
