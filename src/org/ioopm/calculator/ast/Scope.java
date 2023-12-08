package org.ioopm.calculator.ast;

public class Scope extends Unary {
    public Scope(SymbolicExpression expression) {
        super(expression);
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
}
