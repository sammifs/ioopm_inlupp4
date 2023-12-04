package org.ioopm.calculator.ast;

public class Multiplication extends Binary {
    public Multiplication(SymbolicExpression lhs, SymbolicExpression rhs) {
        super(lhs, rhs);
    }

    public SymbolicExpression eval(Environment vars) {
        SymbolicExpression e_lhs = this.lhs.eval(vars);
        SymbolicExpression e_rhs = this.rhs.eval(vars);

        if (e_lhs.isConstant() && e_rhs.isConstant()) {
            return new Constant(e_lhs.getValue() * e_rhs.getValue());
        } else {
            return new Multiplication(e_lhs, e_rhs);
        }
    }

    public boolean equals(Object other) {
        if (other instanceof Multiplication) {
            return this.equals((Multiplication) other);
        } else {
            return false;
        }
    }
    
    public boolean equals(Multiplication other) {
        return this.lhs.equals(other.lhs) && this.rhs.equals(other.rhs);
    }

    public String getName() {
        return "*";
    }

    public int getPriority() {
        return 1;
    }
}
