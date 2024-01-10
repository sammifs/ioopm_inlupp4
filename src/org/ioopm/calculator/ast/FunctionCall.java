package org.ioopm.calculator.ast;

import java.util.ArrayList;

public class FunctionCall extends SymbolicExpression {
    public String name;
    public ArrayList<SymbolicExpression> arguments;
    public int arg_n;

    public FunctionCall(String name, ArrayList<SymbolicExpression> arguments) {
        this.name = name;
        this.arguments = arguments;
        this.arg_n = arguments.size();
    }
    
    public boolean isFunctionCall() {
        return true;
    }

    public SymbolicExpression getArgI(int i) {
        return arguments.get(i);
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }

    @Override
    public String toString() {
        return name;
    }
}
