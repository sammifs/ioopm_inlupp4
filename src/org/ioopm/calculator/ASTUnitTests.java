package org.ioopm.calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.ioopm.calculator.ast.*;;

public class ASTUnitTests {
    @Test
    void ConstantTests() {
        Constant c = new Constant(5);
        assertTrue(c.getValue() == 5);
        assertTrue(c.isConstant());
        assertFalse(c.isCommand());
        assertTrue(c.getPriority() == 100);
    }

    @Test
    void VariableTests() {
        Variable v = new Variable("hej");
        assertFalse(v.isConstant());
        assertTrue(v.getName().equals("hej"));
        assertFalse(v.isCommand());
        assertTrue(v.getPriority() == 100);
    }

    @Test
    void BinaryTests() {
        Constant five = new Constant(5);
        Constant two = new Constant(2);

        Addition a = new Addition(five, two);
        assertFalse(a.isConstant());
        assertTrue(a.getName().equals("+"));
        assertFalse(a.isCommand());
        assertTrue(a.getPriority() == 0);

        Multiplication m = new Multiplication(five, two);
        assertTrue(m.getPriority() == 1);
    }

    @Test
    void AssignmentTests() {
        Constant five = new Constant(5);
        Variable eks = new Variable("x");

        Environment vars = new Environment();
        Assignment a = new Assignment(five, eks);
        assertTrue(five.equals(a.eval(vars)));
        assertTrue(five == a.eval(vars));
    }

    @Test
    void UnaryTests() {
        Constant five = new Constant(5);

        Sin s = new Sin(five);
        assertFalse(s.isConstant());
        assertTrue(s.getName().equals("sin"));
        assertFalse(s.isCommand());
        assertTrue(s.getPriority() == 100);
    }

    @Test
    void CommandTests() {
        Command c = new Command();
        assertFalse(c.isConstant());
        assertTrue(c.isCommand());
        assertTrue(c.getPriority() == 100);
    }
}
