package org.ioopm.calculator.ast; /// could place this in parser *for now*

public abstract class SymbolicExpression {

    public abstract SymbolicExpression accept(Visitor v);

    public boolean isConstant() {
        return false;
    }

    public boolean isNamedConstant() {
        return false;
    }

    public boolean isCommand() {
        return false;
    }

    public boolean isFunctionDeclaration() {
        return false;
    }

    public boolean isFunctionCall() {
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