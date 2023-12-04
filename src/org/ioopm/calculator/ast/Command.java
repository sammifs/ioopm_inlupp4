package org.ioopm.calculator.ast;

public class Command extends SymbolicExpression{
    public SymbolicExpression eval(Environment vars) {
        throw new RuntimeException("eval() called on Command");
    }

    public boolean isCommand() {
        return true;
    }
}
