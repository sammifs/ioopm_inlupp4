package org.ioopm.calculator.ast;

public class Assignment extends Binary {
    public Assignment(SymbolicExpression lhs, SymbolicExpression rhs) {
        super(lhs, rhs);
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Assignment) {
            return this.equals((Assignment) other);
        } else {
            return false;
        }
    }
    
    public boolean equals(Assignment other) {
        return this.lhs.equals(other.lhs) && this.rhs.equals(other.rhs);
    }

    @Override
    public String toString() {
        return this.lhs + " = " + this.rhs;
    }
}
