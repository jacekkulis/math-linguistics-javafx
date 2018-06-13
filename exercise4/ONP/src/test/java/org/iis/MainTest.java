package org.iis;

import org.junit.Assert;
import org.junit.Test;

public class MainTest {

    private IRPN rpn = new RPN();

    @Test
    public void shouldReturnRNPStringOfComputedExpression() {
        Assert.assertEquals("25*1+2/", rpn.compute("((2*5+1)/2)"));
        Assert.assertEquals("5-2*", rpn.compute("5*[-2]"));
        Assert.assertEquals("22/2*", rpn.compute("2/2*2"));
        Assert.assertEquals("xy+", rpn.compute("x+y"));
        Assert.assertEquals("xy-z+", rpn.compute("(x-y)+z"));
        Assert.assertEquals("xyz+-", rpn.compute("x-(y+z)"));
        Assert.assertEquals("xyz+*w*", rpn.compute("x*(y+z)*w"));
        Assert.assertEquals("2342/+*", rpn.compute("2*(3+4/2)"));
        Assert.assertEquals("342*15-2^/+", rpn.compute("3+4*2/(1-5)^2"));
        Assert.assertEquals("12abc*de/+*+", rpn.compute("12+a*(b*c+d/e)"));
    }

    @Test
    public void multiplicationWhenNoOperatorBehindBracket() {
        Assert.assertEquals("222+*", rpn.compute("2(2+2)"));
        Assert.assertEquals("2225*1+*2/*", rpn.compute("2(2(2*5+1)/2)"));
    }

    @Test
    public void acceptNegativeNumbers() {
        Assert.assertEquals("x-y+", rpn.compute("x+[-y]"));
        Assert.assertEquals("xy--z+", rpn.compute("(x-y)+[-z]"));
        Assert.assertEquals("2225*1+*-2/*", rpn.compute("2(2(2*5+1)/[-2])"));
    }
}