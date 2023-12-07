package org.ioopm.calculator.ast;

public class Multiplication extends Binary {
    public Multiplication(SymbolicExpression lhs, SymbolicExpression rhs) {
        super(lhs, rhs);
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Multiplication) {
            return this.equals((Multiplication) other);
        } else {
            return false;
        }
    }
    
    public boolean equals(Multiplication other) {
        return this.lhs.equals(other.lhs) && this.rhs.equals(other.rhs);
    }

    @Override
    public String getName() {
        return "*";
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
