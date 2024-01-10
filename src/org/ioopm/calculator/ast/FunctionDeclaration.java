package org.ioopm.calculator.ast;

import java.util.ArrayList;

public class FunctionDeclaration extends SymbolicExpression {
    public String name;
    private ArrayList<Variable> parameters;
    private Sequence body;
    public int arg_n;

    public FunctionDeclaration(String name, ArrayList<Variable> paramaters) {
        this.name = name;
        this.parameters = paramaters;
        this.arg_n = paramaters.size();
    }

    public void prependToBody(int i, SymbolicExpression value) {
        Assignment a = new Assignment(value, parameters.get(i));
        body.prependExpression(a);
    }

    public Variable getParamI(int i) {
        return parameters.get(i);
    }

    public void removeFirstBody() {
        body.removeFirstExpression();
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }

    public void setBody(Sequence s) {
        this.body = s;
    }

    public Sequence getBody() {
        return this.body;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isFunctionDeclaration() {
        return true;
    }

    @Override
    public String toString() {
        String result = "";
        for (Variable v: this.parameters) {
            result += v.getName();
        }
        return (name + result);
    }
}
