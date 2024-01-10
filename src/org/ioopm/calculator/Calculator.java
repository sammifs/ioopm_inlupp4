package org.ioopm.calculator;

import java.io.IOException;
import java.util.ArrayList;

import org.ioopm.calculator.ast.*;
import org.ioopm.calculator.parser.CalculatorParser;
import org.ioopm.calculator.parser.IllegalExpressionException;
import org.ioopm.calculator.parser.SyntaxErrorException;

public class Calculator {
    public static void main(String[] args) throws IOException {
        final CalculatorParser cp = new CalculatorParser();
        final Environment vars = new Environment();

        ArrayList<FunctionDeclaration> funcs = new ArrayList<>();

        final EvaluationVisitor evaluator = new EvaluationVisitor(funcs);

        

        int expressions = 0;
        int success = 0;
        int full_evaluations = 0;

        boolean function_parsing_mode = false;

        Sequence seq = new Sequence();
        while (true) {
            String input = System.console().readLine();
            try {
                expressions++;
                SymbolicExpression se = cp.parse(input, vars);

                if (se.isFunctionDeclaration()) {
                    if (function_parsing_mode) {
                        System.out.println("Error, nested function definitions are not permitted.");
                    } else {
                        funcs.add((FunctionDeclaration) se);
                        function_parsing_mode = true;
                    }
                }
                else if (se.isCommand()) {
                    success++;
                    if (se instanceof Quit) {
                        break;
                    } else if (se instanceof Vars) {
                        System.out.println(vars);
                    } else if (se instanceof Clear) {
                        vars.clear();
                    } else {
                        if (!function_parsing_mode) {
                            System.out.println("Error, end can only occur as the end of a function definiton");
                        }
                        FunctionDeclaration fd = funcs.get(funcs.size()-1);
                        fd.setBody(seq);
                        seq = new Sequence();
                        function_parsing_mode = false;
                    }
                } else if (function_parsing_mode) {
                    seq.AddExpression(se);
                } 
                // else if (se.isFunctionCall()) {
                //     FunctionCall fc = (FunctionCall) se;
                //     boolean flag = true;
                //     for (FunctionDeclaration stored_function : funcs) {
                //         if (stored_function.name.equals(fc.name)) {
                //             flag = false;
                //             if (stored_function.arg_n != fc.arg_n) {
                //                 System.out.println("Error, function '"+ stored_function.name + "' called with wrong number of arguments.");
                //             } else {
                //                 for (int i=0; i<stored_function.arg_n; i++) {
                //                     stored_function.prependToBody(i, fc.getArgI(i));
                //                 }
                //                 evaluator.env.pushEnvironment(new Environment());
                //                 SymbolicExpression ans = stored_function.getBody().accept(evaluator);
                //                 System.out.println(ans);
                //                 evaluator.env.popEnvironment();
                //                 for (int i=0; i<stored_function.arg_n; i++) {
                //                     stored_function.removeFirstBody();
                //                 }
                //             }
                //         } 
                //     }
                //     if (flag) System.out.println("Error, no function of that name.");
                // } 
                else {
                    if (evaluator.check(se)) {
                        if (evaluator.reassign_check(se)) {
                            try {
                                SymbolicExpression ans = evaluator.evaluate(se, vars);
                                System.out.println(ans);
                                success++;
                                if (ans.isConstant()) {
                                    full_evaluations++;
                                }
                            } catch (IllegalAssignmentException e) {
                                System.out.println(e);
                            }
                        } else {
                            System.out.println("Error, variable assigned more than once in expression");
                        }
                    } else {
                        System.out.println("Error, assignments to named constants: ");
                        System.out.println(evaluator.error);
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
