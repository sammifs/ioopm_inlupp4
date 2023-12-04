package org.ioopm.calculator.ast;

public abstract class Binary extends SymbolicExpression {
    protected SymbolicExpression lhs = null;
    protected SymbolicExpression rhs = null;
    
    public Binary(SymbolicExpression lhs, SymbolicExpression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public String toString() {
        String lhs_string;
        if (this.lhs.getPriority() < this.getPriority()) {
            lhs_string = "(" + this.lhs.toString() + ")";
        } else {
            lhs_string = this.lhs.toString();
        }

        String rhs_string;
        if (this.rhs.getPriority() < this.getPriority()) {
            rhs_string = "(" + this.rhs.toString() + ")";
        } else {
            rhs_string = this.rhs.toString();
        }

        return lhs_string + " " + this.getName() + " " + rhs_string;
    }
}
