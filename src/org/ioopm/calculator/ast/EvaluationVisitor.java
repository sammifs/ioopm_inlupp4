package org.ioopm.calculator.ast;

import java.util.ArrayList;

public class EvaluationVisitor implements Visitor {
    public StackEnvironment env = null;
    public SymbolicExpression error;
    ArrayList<FunctionDeclaration> funcs;

    public EvaluationVisitor(ArrayList<FunctionDeclaration> funs) {
        this.funcs = funs;
    }

    public SymbolicExpression evaluate(SymbolicExpression topLevel, Environment env) {
        this.env = new StackEnvironment(env);
        return topLevel.accept(this);
    }

    public boolean check(SymbolicExpression s) {
        NamedConstantChecker checker = new NamedConstantChecker();
        try {
            s.accept(checker);
            return true;
        } catch (IllegalAssignmentException e){
            error = checker.error;
            return false;
        }
    }

    public boolean reassign_check(SymbolicExpression s) {
        ReassignmentChecker r = new ReassignmentChecker();
        try {
            s.accept(r);
            return true;
        } catch (IllegalAssignmentException e) {
            return false;
        }
    }

    public SymbolicExpression visit(Conditional c) {
        FreeBoundVariableChecker f = new FreeBoundVariableChecker(this.env);
        c.boolexp().accept(f);
        SymbolicExpression boolexp = c.boolexp().accept(this);
        
        if (boolexp.getValue() == 1) {
            return c.first_scope().accept(this);
        } else {
            return c.second_scope().accept(this);
        }
    }

    public SymbolicExpression visit(Sequence f) {
        for (SymbolicExpression s : f.getExpsExceptLast()) {
            s.accept(this);
        }
        return f.getLast().accept(this);
    }

    public SymbolicExpression visit(FunctionDeclaration f) {
        f.getBody().accept(this);
        return null;
    }

    public SymbolicExpression visit(End f) {
        return f;
    }

    public SymbolicExpression visit(FunctionCall f) {
        for (FunctionDeclaration stored_function : funcs) {
            if (stored_function.name.equals(f.name)) {
                if (stored_function.arg_n != f.arg_n) {
                    System.out.println("Error, function '"+ stored_function.name + "' called with wrong number of arguments.");
                } else {
                    //this.env.pushEnvironment(new Environment());
                    for (int i=0; i<stored_function.arg_n; i++) {
                        Assignment a = new Assignment(f.getArgI(i), stored_function.getParamI(i));
                        a.accept(this);
                    }
                    SymbolicExpression ans = stored_function.getBody().accept(this);
                    //this.env.popEnvironment();
                    return ans;
                }
            } 
        }
        System.out.println("Error, no function of that name.");
        return f;
    }

    public SymbolicExpression visit(Scope s) {
        this.env.pushEnvironment(new Environment());

        SymbolicExpression arg = s.arg().accept(this);

        this.env.popEnvironment();
        return arg;
    }

    public SymbolicExpression visit(Addition n) {
        SymbolicExpression left = n.lhs().accept(this);
        SymbolicExpression right = n.rhs().accept(this);

        if (left.isConstant() && right.isConstant()) {
            return new Constant(left.getValue() + right.getValue());
        } else {
            return new Addition(left, right);
        }
    }

    public SymbolicExpression visit(Subtraction n) {
        SymbolicExpression left = n.lhs().accept(this);
        SymbolicExpression right = n.rhs().accept(this);

        if (left.isConstant() && right.isConstant()) {
            return new Constant(left.getValue() - right.getValue());
        } else {
            return new Subtraction(left, right);
        }
    }

    public SymbolicExpression visit(Multiplication n) {
        SymbolicExpression left = n.lhs().accept(this);
        SymbolicExpression right = n.rhs().accept(this);

        if (left.isConstant() && right.isConstant()) {
            return new Constant(left.getValue() * right.getValue());
        } else {
            return new Multiplication(left, right);
        }
    }

    public SymbolicExpression visit(BooleanEquals n) {
        SymbolicExpression left = n.lhs().accept(this);
        SymbolicExpression right = n.rhs().accept(this);

        if (left.isConstant() && right.isConstant()) {
            if (left.getValue() == right.getValue()) {
                return new Constant(1);
            } else {
                return new Constant(0);
            }
        } else {
            return new BooleanEquals(left, right);
        }
    }

    public SymbolicExpression visit(BooleanLess n) {
        SymbolicExpression left = n.lhs().accept(this);
        SymbolicExpression right = n.rhs().accept(this);

        if (left.isConstant() && right.isConstant()) {
            if (left.getValue() < right.getValue()) {
                return new Constant(1);
            } else {
                return new Constant(0);
            }
        } else {
            return new BooleanLess(left, right);
        }
    }

    public SymbolicExpression visit(BooleanMore n) {
        SymbolicExpression left = n.lhs().accept(this);
        SymbolicExpression right = n.rhs().accept(this);

        if (left.isConstant() && right.isConstant()) {
            if (left.getValue() > right.getValue()) {
                return new Constant(1);
            } else {
                return new Constant(0);
            }
        } else {
            return new BooleanMore(left, right);
        }
    }

    public SymbolicExpression visit(Division n) {
        SymbolicExpression left = n.lhs().accept(this);
        SymbolicExpression right = n.rhs().accept(this);

        if (left.isConstant() && right.isConstant()) {
            return new Constant(left.getValue() / right.getValue());
        } else {
            return new Division(left, right);
        }
    }

    public SymbolicExpression visit(Constant n) {
        return n;
    }

    public SymbolicExpression visit(Variable n) {
        if (this.env.get(n) != null) {
            return env.get(n);
        } else {
            return n;
        }
    }

    public SymbolicExpression visit(Negation n) {
        SymbolicExpression arg = n.arg().accept(this);

        if (arg.isConstant()) {
            return new Constant(-(arg.getValue()));
        } else {
            return new Negation(arg);
        }
    }

    public SymbolicExpression visit(Sin n) {
        SymbolicExpression arg = n.arg().accept(this);

        if (arg.isConstant()) {
            return new Constant(Math.sin(arg.getValue()));
        } else {
            return new Sin(arg);
        }
    }

    public SymbolicExpression visit(Cos n) {
        SymbolicExpression arg = n.arg().accept(this);

        if (arg.isConstant()) {
            return new Constant(Math.cos(arg.getValue()));
        } else {
            return new Cos(arg);
        }
    }

    public SymbolicExpression visit(Exp n) {
        SymbolicExpression arg = n.arg().accept(this);

        if (arg.isConstant()) {
            return new Constant(Math.exp(arg.getValue()));
        } else {
            return new Exp(arg);
        }
    }

    public SymbolicExpression visit(Log n) {
        SymbolicExpression arg = n.arg().accept(this);

        if (arg.isConstant()) {
            return new Constant(Math.log(arg.getValue()));
        } else {
            return new Log(arg);
        }
    }

    public SymbolicExpression visit(Assignment n) {
        SymbolicExpression e_lhs = n.lhs.accept(this);

        if (!(n.rhs instanceof Variable)) {
            throw new IllegalAssignmentException("Error: assignment needs variable");
        }
        this.env.stack_put((Variable)n.rhs, e_lhs);
        return e_lhs;
    }

    public SymbolicExpression visit(Clear n) {
        return n;
    }

    public SymbolicExpression visit(Quit n) {
        return n;
    }

    public SymbolicExpression visit(Vars n) {
        return n;
    }
}
