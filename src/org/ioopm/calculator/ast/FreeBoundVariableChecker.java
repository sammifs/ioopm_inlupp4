package org.ioopm.calculator.ast;

public class FreeBoundVariableChecker implements Visitor {
    private StackEnvironment vars;

    public FreeBoundVariableChecker(StackEnvironment vars) {
        this.vars = vars;
    }

    public SymbolicExpression visit(Conditional c) {
        c.boolexp().accept(this);
        c.first_scope().accept(this);
        c.second_scope().accept(this);
        return null;
    }

    public SymbolicExpression visit(Scope c) {
        c.argument.accept(this);
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
        if (this.vars.get(c) == null) {
            throw new IllegalAssignmentException("Error, free bound variable in condition");
        }
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
