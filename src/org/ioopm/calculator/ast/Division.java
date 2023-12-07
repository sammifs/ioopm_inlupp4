package org.ioopm.calculator.ast;

public class Division extends Binary {
    public Division(SymbolicExpression lhs, SymbolicExpression rhs) {
        super(lhs, rhs);
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Division) {
            return this.equals((Division) other);
        } else {
            return false;
        }
    }
    
    public boolean equals(Division other) {
        return this.lhs.equals(other.lhs) && this.rhs.equals(other.rhs);
    }

    @Override
    public String getName() {
        return "/";
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
