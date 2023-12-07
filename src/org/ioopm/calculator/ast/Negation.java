package org.ioopm.calculator.ast;

public class Negation extends Unary {
    public Negation(SymbolicExpression expression) {
        super(expression);
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Negation) {
            return this.equals((Negation) other);
        } else {
            return false;
        }
    }
    
    public boolean equals(Negation other) {
        return this.getName().equals(other.getName()) && this.argument.equals(other.argument);
    }

    @Override
    public String getName() {
        return "-";
    }
}
