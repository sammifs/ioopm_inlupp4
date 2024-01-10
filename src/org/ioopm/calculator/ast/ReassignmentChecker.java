package org.ioopm.calculator.ast;

import java.util.ArrayList;

public class ReassignmentChecker implements Visitor {
    private ArrayList<Variable> vars = new ArrayList<>();

    public SymbolicExpression visit(Sequence f) {
        for (SymbolicExpression s : f.getExps()) {
            s.accept(this);
        }
        return null;
    }

    public SymbolicExpression visit(FunctionDeclaration f) {
        f.getBody().accept(this);
        return null;
    }

    public SymbolicExpression visit(End f) {
        return null;
    }

    public SymbolicExpression visit(FunctionCall f) {
        return null;
    }

    public SymbolicExpression visit(Conditional c) {
        return null;
    }

    public SymbolicExpression visit(Scope c) {
        return null;
    }

    public SymbolicExpression visit(Addition a) {
        a.lhs.accept(this);
        a.rhs.accept(this);
        return null;
    }

    public SymbolicExpression visit(Subtraction a) {
        a.lhs.accept(this);
        a.rhs.accept(this);
        return null;
    }

    public SymbolicExpression visit(Multiplication a) {
        a.lhs.accept(this);
        a.rhs.accept(this);
        return null;
    }

    public SymbolicExpression visit(BooleanEquals a) {
        a.lhs.accept(this);
        a.rhs.accept(this);
        return null;
    }

    public SymbolicExpression visit(BooleanLess a) {
        a.lhs.accept(this);
        a.rhs.accept(this);
        return null;
    }

    public SymbolicExpression visit(BooleanMore a) {
        a.lhs.accept(this);
        a.rhs.accept(this);
        return null;
    }

    public SymbolicExpression visit(Division a) {
        a.lhs.accept(this);
        a.rhs.accept(this);
        return null;
    }

    public SymbolicExpression visit(Assignment a) {
        a.lhs.accept(this);
        a.rhs.accept(this);
        if (a.rhs instanceof Variable) {
            if (this.vars.contains(a.rhs)) {
                throw new IllegalAssignmentException("Error: cannot assign same variable twice in expression");
            } else {
                this.vars.add((Variable)a.rhs);
            }
        }
        return null;
    }

    public SymbolicExpression visit(Vars v) {
        return null;
    }

    public SymbolicExpression visit(Quit v) {
        return null;
    }

    public SymbolicExpression visit(Clear v) {
        return null;
    }

    public SymbolicExpression visit(Constant c) {
        return null;
    }

    public SymbolicExpression visit(Variable c) {
        return null;
    }

    public SymbolicExpression visit(Cos c) {
        c.argument.accept(this);
        return null;
    }

    public SymbolicExpression visit(Sin c) {
        c.argument.accept(this);
        return null;
    }

    public SymbolicExpression visit(Log c) {
        c.argument.accept(this);
        return null;
    }

    public SymbolicExpression visit(Exp c) {
        c.argument.accept(this);
        return null;
    }

    public SymbolicExpression visit(Negation c) {
        c.argument.accept(this);
        return null;
    }
}
