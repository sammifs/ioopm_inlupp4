package org.ioopm.calculator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import java.io.IOException;

import org.ioopm.calculator.ast.*;
import org.ioopm.calculator.parser.*;

public class ParserTests {
    static CalculatorParser parser;

    @BeforeAll
    public static void initParser() {
        parser = new CalculatorParser();
    }

    static SymbolicExpression parse(String input) throws IOException {
        Environment vars = new Environment();
        return parser.parse(input, vars);
    }

    static void runTest(SymbolicExpression e) throws IOException {
        String input = e.toString();
        SymbolicExpression result = parse(input);
        assertEquals(e, result);
    }

    static SymbolicExpression con(double d) {
        return new Constant(d);
    }

    static SymbolicExpression var(String s) {
        return new Variable(s);
    }

    static SymbolicExpression add(SymbolicExpression l, SymbolicExpression r) {
        return new Addition(l, r);
    }

    @Test
    void testConstant() throws IOException{
        runTest(con(42.0));
        runTest(con(0.0));
        runTest(con(3.14159));
        runTest(con(-3.14159));
    }

    @Test
    void testVariable() throws IOException{
        runTest(var("x"));
        runTest(var("xyz"));
        runTest(var("aVeryLongVariableNameThatNeverSeemsToEnd"));
    }

    @Test
    void testAddition() throws IOException{
        runTest(add(con(1.0), con(2.0)));
        runTest(add(var("x"), con(2.0)));
        runTest(add(add(con(1.0), con(2.0)),
                    add(var("Three"), var("Four"))));
    }

    @Test
    void testParentheses() throws IOException {
        assertEquals(parse("(1)"), new Constant(1.0));
        assertEquals(parse("((1))"), new Constant(1.0));
        assertEquals(parse("(((1)))"), new Constant(1.0));
        assertEquals(parse("(((1 + 2) + 3) + 4)"),
                     add(add(add(con(1), con(2)), con(3)), con(4)));
        assertEquals(parse("(1 + (2 + (3 + 4)))"),
                     add(con(1), add(con(2), add(con(3), con(4)))));
        assertThrows(SyntaxErrorException.class, () -> {parse("(1 + ");});
    }
}