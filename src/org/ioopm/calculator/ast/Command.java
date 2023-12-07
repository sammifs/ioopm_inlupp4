package org.ioopm.calculator.ast;

public abstract class Command extends SymbolicExpression{
    public SymbolicExpression eval(Environment vars) {
        throw new RuntimeException("eval() called on Command");
    }

    @Override
    public boolean isCommand() {
        return true;
    }
}
