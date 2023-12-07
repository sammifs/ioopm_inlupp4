package org.ioopm.calculator.ast;

public class NamedConstant extends SymbolicExpression{
    private String name;
    private double db;

    public NamedConstant(String sv, Double db) {
        this.name = sv;
        this.db = db;
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
        return null; // TODO: THROW ERROR
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof NamedConstant) {
            return this.equals((NamedConstant) other);
        } else {
            return false;
        }
    }
    
    public boolean equals(NamedConstant other) {
        return this.name.equals(other.name) && this.db == other.db;
    }

    @Override
    public boolean isNamedConstant() {
        return true;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
