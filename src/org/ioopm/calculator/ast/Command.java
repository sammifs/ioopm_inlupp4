package org.ioopm.calculator.ast;

public class Command extends SymbolicExpression{
    public SymbolicExpression eval(Environment vars) {
        throw new RuntimeException("eval() called on Command");
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
        return null; // TODO: THROW ERROR;
    }

    @Override
    public boolean isCommand() {
        return true;
    }
}
