package org.ioopm.calculator.ast;

public abstract class Unary extends SymbolicExpression{
    protected SymbolicExpression argument;

    public Unary(SymbolicExpression argument) {
        this.argument = argument;
    }

    public SymbolicExpression arg() {
        return this.argument;
    }

    @Override
    public String toString() {
        return this.getName() + " " + this.argument.toString();
    }
}
