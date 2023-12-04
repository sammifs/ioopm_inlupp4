package org.ioopm.calculator;

import java.io.IOException;

import org.ioopm.calculator.ast.*;
import org.ioopm.calculator.parser.CalculatorParser;
import org.ioopm.calculator.parser.IllegalExpressionException;
import org.ioopm.calculator.parser.SyntaxErrorException;

public class Calculator {
    public static void main(String[] args) throws IOException {
        final CalculatorParser cp = new CalculatorParser();
        final Environment vars = new Environment();

        int expressions = 0;
        int success = 0;
        int full_evaluations = 0;

        while (true) {
            String input = System.console().readLine();
            try {
                SymbolicExpression se = cp.parse(input, vars);
                expressions++;
                if (se.isCommand()) {
                    if (se instanceof Quit) {
                        break;
                    } else if (se instanceof Vars) {
                        System.out.println(vars);
                    } else {
                        vars.clear();
                    }
                } else {
                    try { 
                        SymbolicExpression ans = se.eval(vars);
                        System.out.println(ans);
                        success++;
                        if (ans.isConstant()) {
                            full_evaluations++;
                        }
                    } catch (IllegalAssignmentException e) {
                        System.out.println(e);
                    }
                }
            } catch (SyntaxErrorException e) {
                System.out.println(e);
            } catch (IllegalExpressionException e) {
                System.out.println(e);
            }
        }
        System.out.println("Thank you!\n" + "expressions: " + expressions + "\n"
                            + "successfull evaluations: " + success + "\n"
                            + "full evaluations: " + full_evaluations);  
    }
}
