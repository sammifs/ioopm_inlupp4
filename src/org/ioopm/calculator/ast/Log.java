package org.ioopm.calculator.ast;

public class Log extends Unary{
    public Log(SymbolicExpression expression) {
        super(expression);
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }

    public SymbolicExpression eval(Environment vars) {
        SymbolicExpression arg = this.argument.eval(vars);
        if (arg.isConstant()) {
            return new Constant(Math.log(arg.getValue()));
        } else {
            return new Sin(arg);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Log) {
            return this.equals((Log) other);
        } else {
            return false;
        }
    }
    
    public boolean equals(Log other) {
        return this.getName().equals(other.getName()) && this.argument.equals(other.argument);
    }

    @Override
    public String getName() {
        return "log";
    }
}
