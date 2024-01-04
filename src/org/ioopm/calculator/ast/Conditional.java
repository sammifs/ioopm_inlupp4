package org.ioopm.calculator.ast;

public class Conditional extends SymbolicExpression {
    private SymbolicExpression boolexp;
    private SymbolicExpression first_scope;
    private SymbolicExpression second_scope;

    public Conditional(SymbolicExpression boole, SymbolicExpression s1, SymbolicExpression s2) {
        this.boolexp = boole;
        this.first_scope = s1;
        this.second_scope = s2;
    }

    public SymbolicExpression boolexp() {
        return boolexp;
    }
    
    public SymbolicExpression first_scope() {
        return this.first_scope;
    }

    public SymbolicExpression second_scope() {
        return this.second_scope;
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
}
