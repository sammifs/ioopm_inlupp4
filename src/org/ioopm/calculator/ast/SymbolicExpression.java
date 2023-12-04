package org.ioopm.calculator.ast; /// could place this in parser *for now*

public abstract class SymbolicExpression {

    public abstract SymbolicExpression eval(Environment vars);

    public boolean isConstant() {
        return false;
    }

    public boolean isNamedConstant() {
        return false;
    }

    public boolean isCommand() {
        return false;
    }

    public String getName(){
        throw new RuntimeException("getName() called on expression with no operator");
    }

    public int getPriority() {
        return 100;
    }

    public double getValue() {
        throw new RuntimeException("getValue() called on expression that wasnt constant");
    }
}