package org.ioopm.calculator.ast;

public class Assignment extends Binary {
    public Assignment(SymbolicExpression lhs, SymbolicExpression rhs) {
        super(lhs, rhs);
    }

    public SymbolicExpression eval(Environment vars) {
        SymbolicExpression e_lhs = this.lhs.eval(vars);

        if (rhs.isNamedConstant()) {
            throw new IllegalAssignmentException("Error: cannot redefine named constant");
        }
        vars.put((Variable)rhs, e_lhs);
        return e_lhs;
    }

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

    public String toString() {
        return this.lhs + " = " + this.rhs;
    }
}
