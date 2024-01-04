package org.ioopm.calculator.ast;

public class BooleanLess extends Binary {
    public BooleanLess(SymbolicExpression lhs, SymbolicExpression rhs) {
        super(lhs, rhs);
    }
    
    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
}
