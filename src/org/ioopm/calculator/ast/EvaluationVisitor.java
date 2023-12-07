package org.ioopm.calculator.ast;

public class EvaluationVisitor implements Visitor {
    private Environment env = null;
    public SymbolicExpression error;

    public SymbolicExpression evaluate(SymbolicExpression topLevel, Environment env) {
        this.env = env;
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

        if (n.rhs.isNamedConstant()) {
            throw new IllegalAssignmentException("Error: cannot redefine named constant");
        }
        if (!(n.rhs instanceof Variable)) {
            throw new IllegalAssignmentException("Error: assignment needs variable");
        }
        this.env.put((Variable)n.rhs, e_lhs);
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
