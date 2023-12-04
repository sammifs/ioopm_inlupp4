package org.ioopm.calculator;

import org.ioopm.calculator.ast.*;

public class Test { 
    private static void testPrinting(String expected, SymbolicExpression e) {
        if (expected.equals("" + e)) {
            System.out.println("Passed: " + e);
        } else {
            System.out.println("Error: expected '" + expected + "' but got '" + e + "'");
        }
    }

    private static void testEvaluating(SymbolicExpression expected, SymbolicExpression e, Environment vars) {
        SymbolicExpression r = e.eval(vars);
        if (r.equals(expected)) {
            System.out.println("Passed: " + e);
        } else {
            System.out.println("Error: expected '" + expected + "' but got '" + e + "'");
        }
    }

    private static void testDoubleEvaluating(SymbolicExpression expected, SymbolicExpression e, Environment vars) {
        SymbolicExpression r = e.eval(vars);
        SymbolicExpression v = expected.eval(vars);
        if (r.equals(v)) {
            System.out.println("Passed: " + e);
        } else {
            System.out.println("Error: expected '" + v + "' but got '" + e + "'");
        }
    }

    private static void constPrintTest() {
        Constant c = new Constant(5);
        testPrinting("5.0", c);
    }

    private static void constVariablePrintTest() {
        Constant c1 = new Constant(5);
        Constant c2 = new Constant(2);
        Variable v = new Variable("x");
        Addition a = new Addition(c1, v);
        Multiplication m = new Multiplication(a, c2);

        testPrinting("(5.0 + x) * 2.0", m);
    }

    private static void sinPrintTest() {
        Constant c1 = new Constant(5);
        Sin s = new Sin(c1);
        testPrinting("sin 5.0", s);
    }

    private static void negatePrintTest() {
        Constant c = new Constant(5);
        Sin s = new Sin(c);
        Negation n = new Negation(s);
        testPrinting("- sin 5.0", n);
    }

    private static void multiDiviPrintTest() {
        Sin c1 = new Sin(new Constant(5));
        Constant c2 = new Constant(2);
        Division d = new Division(new Constant(5), c2);
        Multiplication m = new Multiplication(c1, d);

        testPrinting("sin 5.0 * 5.0 / 2.0", m);
    }

    private static void simpleEvalTest() {
        Environment dumb = new Environment();
        SymbolicExpression x = new Addition(new Constant(5), new Constant(37));
        SymbolicExpression y = new Constant(42);
        testEvaluating(y, x, dumb);
    }

    private static void sinEvalTest() {
        Environment dumb = new Environment();
        Addition c3 = new Addition(new Constant(3), new Constant(2));
        Constant u1 = new Constant(-0.9589242746631385);
        Sin u2 = new Sin(c3);
        testEvaluating(u1, u2, dumb);
    }

    private static void simpleAssignmentEvalTest() {
        Environment var = new Environment();
        Variable v = new Variable("x");
        Constant c = new Constant(5);
        Assignment a = new Assignment(c, v);

        testEvaluating(c, a, var);
    }

    private static void assignmentHashTest() {
        Variable v = new Variable("x");

        Variable v2 = new Variable("x");

        System.out.println("a = " + v.hashCode() + "\nb = " + v2.hashCode());
    }

    private static void failedAssignmentTest() {
        Environment var = new Environment();

        NamedConstant v = new NamedConstant("x", 25.0);
        Assignment a = new Assignment(new Constant(5), v);
        Variable v2 = new Variable("x");
        Assignment a2 = new Assignment(new Constant(5), v2);
        
        //testDoubleEvaluating(a, a2, var);
        //^ will fail with IllegalAssignmentException as it should.
    }
    public static void main(String[] args) {
        constPrintTest();

        constVariablePrintTest();

        sinPrintTest();

        negatePrintTest();

        multiDiviPrintTest();

        simpleEvalTest();

        sinEvalTest();

        simpleAssignmentEvalTest();

        assignmentHashTest();

        failedAssignmentTest();
    }
}