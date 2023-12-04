package org.ioopm.calculator.ast;

public abstract class Unary extends SymbolicExpression{
    protected SymbolicExpression argument;

    public Unary(SymbolicExpression argument) {
        this.argument = argument;
    }

    public String toString() {
        return this.getName() + " " + this.argument.toString();
    }
}
