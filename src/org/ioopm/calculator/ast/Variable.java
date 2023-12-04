package org.ioopm.calculator.ast;

public class Variable extends Atom {
    private String identifier;

    public Variable(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }

    public SymbolicExpression eval(Environment vars) {
        if (vars.get(this) != null) {
            return vars.get(this);
        } else {
            return this;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Variable) {
            return this.equals((Variable) other);
        } else {
            return false;
        }
    }
    
    public boolean equals(Variable other) {
        return this.identifier.equals(other.identifier);
    }

    @Override
    public String toString() {
        return this.identifier;
    }

    @Override
    public String getName() {
        return this.identifier;
    }

    @Override
    public int hashCode() {
        return this.identifier.hashCode();
    }
}
