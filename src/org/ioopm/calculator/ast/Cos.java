package org.ioopm.calculator.ast;

public class Cos extends Unary{
    public Cos(SymbolicExpression expression) {
        super(expression);
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Cos) {
            return this.equals((Cos) other);
        } else {
            return false;
        }
    }
    
    public boolean equals(Cos other) {
        return this.getName().equals(other.getName()) && this.argument.equals(other.argument);
    }

    @Override
    public String getName() {
        return "cos";
    }
}
