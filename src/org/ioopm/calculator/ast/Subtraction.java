package org.ioopm.calculator.ast;

public class Subtraction extends Binary {
    public Subtraction(SymbolicExpression lhs, SymbolicExpression rhs) {
        super(lhs, rhs);
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Subtraction) {
            return this.equals((Subtraction) other);
        } else {
            return false;
        }
    }
    
    public boolean equals(Subtraction other) {
        return this.lhs.equals(other.lhs) && this.rhs.equals(other.rhs);
    }

    @Override
    public String getName() {
        return "-";
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
