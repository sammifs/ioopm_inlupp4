package org.ioopm.calculator.ast;

public class BooleanEquals extends Binary {
    public BooleanEquals(SymbolicExpression lhs, SymbolicExpression rhs) {
        super(lhs, rhs);
    }
    
    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
}
