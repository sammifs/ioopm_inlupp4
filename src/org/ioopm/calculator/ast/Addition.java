package org.ioopm.calculator.ast;

public class Addition extends Binary {
    public Addition(SymbolicExpression lhs, SymbolicExpression rhs) {
        super(lhs, rhs);
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Addition) {
            return this.equals((Addition) other);
        } else {
            return false;
        }
    }
    
    public boolean equals(Addition other) {
        return this.lhs.equals(other.lhs) && this.rhs.equals(other.rhs);
    }

    @Override
    public String getName() {
        return "+";
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
