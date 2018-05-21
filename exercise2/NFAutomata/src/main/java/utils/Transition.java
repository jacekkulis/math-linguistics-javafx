/**
 *
 */
package utils;

import interfaces.IState;
import interfaces.ITransition;

/**
 * @author Jacek
 *
 */
public class Transition implements ITransition {
    private String rule;
    private IState startState;
    private IState endState;


    public Transition(IState start, String rule, IState end) {
        this.rule = rule;
        this.startState = start;
        this.endState = end;
    }

    @Override
    public boolean hasRule(String val) {
        return this.rule.equals(val);
    }

    @Override
    public IState getStartState(){
        return this.startState;
    }

    @Override
    public IState getGetEndState() {
        return this.endState;
    }

    @Override
    public String toString() {
        return "{" + startState.getId() + "}[rule=" + rule + "]->{" +  endState.getId() + "}";
    }

    @Override
    public String getRule() {
        return this.rule;
    }
}
