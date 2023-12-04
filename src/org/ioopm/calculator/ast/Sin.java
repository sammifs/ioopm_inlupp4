package org.ioopm.calculator.ast;

public class Sin extends Unary {
    public Sin(SymbolicExpression expression) {
        super(expression);
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }

    public SymbolicExpression eval(Environment vars) {
        SymbolicExpression arg = this.argument.eval(vars);
        if (arg.isConstant()) {
            return new Constant(Math.sin(arg.getValue()));
        } else {
            return new Sin(arg);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Sin) {
            return this.equals((Sin) other);
        } else {
            return false;
        }
    }
    
    public boolean equals(Sin other) {
        return this.getName().equals(other.getName()) && this.argument.equals(other.argument);
    }

    @Override
    public String getName() {
        return "sin";
    }
}
