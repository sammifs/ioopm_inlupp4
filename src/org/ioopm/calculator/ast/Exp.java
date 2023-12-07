package org.ioopm.calculator.ast;

public class Exp extends Unary {
    public Exp(SymbolicExpression expression) {
        super(expression);
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Exp) {
            return this.equals((Exp) other);
        } else {
            return false;
        }
    }
    
    public boolean equals(Exp other) {
        return this.getName().equals(other.getName()) && this.argument.equals(other.argument);
    }

    @Override
    public String getName() {
        return "exp";
    }
}
