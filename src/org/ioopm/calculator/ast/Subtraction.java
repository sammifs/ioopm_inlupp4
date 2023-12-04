package org.ioopm.calculator.ast;

public class Subtraction extends Binary {
    public Subtraction(SymbolicExpression lhs, SymbolicExpression rhs) {
        super(lhs, rhs);
    }

    public SymbolicExpression eval(Environment vars) {
        SymbolicExpression e_lhs = this.lhs.eval(vars);
        SymbolicExpression e_rhs = this.rhs.eval(vars);

        if (e_lhs.isConstant() && e_rhs.isConstant()) {
            return new Constant(e_lhs.getValue() - e_rhs.getValue());
        } else {
            return new Subtraction(e_lhs, e_rhs);
        }
    }

    public boolean equals(Object other) {
        if (other instanceof Subtraction) {
            return this.equals((Subtraction) other);
        } else {
            return false;
        }
    }
    
    public boolean equals(Subtraction other) {
        return this.lhs.equals(other.lhs) && this.rhs.equals(other.rhs);
    }

    public String getName() {
        return "-";
    }

    public int getPriority() {
        return 0;
    }
}
