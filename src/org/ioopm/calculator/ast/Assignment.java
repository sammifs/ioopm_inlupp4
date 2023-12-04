package org.ioopm.calculator.ast;

public class Assignment extends Binary {
    public Assignment(SymbolicExpression lhs, SymbolicExpression rhs) {
        super(lhs, rhs);
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }

    public SymbolicExpression eval(Environment vars) {
        SymbolicExpression e_lhs = this.lhs.eval(vars);

        if (rhs.isNamedConstant()) {
            throw new IllegalAssignmentException("Error: cannot redefine named constant");
        }
        if (!(rhs instanceof Variable)) {
            throw new IllegalAssignmentException("Error: assignment needs variable");
        }
        vars.put((Variable)rhs, e_lhs);
        return e_lhs;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Assignment) {
            return this.equals((Assignment) other);
        } else {
            return false;
        }
    }
    
    public boolean equals(Assignment other) {
        return this.lhs.equals(other.lhs) && this.rhs.equals(other.rhs);
    }

    @Override
    public String toString() {
        return this.lhs + " = " + this.rhs;
    }
}
