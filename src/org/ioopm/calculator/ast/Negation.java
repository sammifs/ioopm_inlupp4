package org.ioopm.calculator.ast;

public class Negation extends Unary {
    public Negation(SymbolicExpression expression) {
        super(expression);
    }

    public SymbolicExpression eval(Environment vars) {
        SymbolicExpression arg = this.argument.eval(vars);
        if (arg.isConstant()) {
            return new Constant(-arg.getValue());
        } else {
            return new Negation(arg);
        }
    }

    public boolean equals(Object other) {
        if (other instanceof Negation) {
            return this.equals((Negation) other);
        } else {
            return false;
        }
    }
    
    public boolean equals(Negation other) {
        return this.getName().equals(other.getName()) && this.argument.equals(other.argument);
    }

    public String getName() {
        return "-";
    }
}
