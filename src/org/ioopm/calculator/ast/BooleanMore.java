package org.ioopm.calculator.ast;

public class BooleanMore extends Binary {
    public BooleanMore(SymbolicExpression lhs, SymbolicExpression rhs) {
        super(lhs, rhs);
    }
    
    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
}
