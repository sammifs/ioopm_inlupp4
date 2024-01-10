package org.ioopm.calculator.ast;

import java.util.ArrayList;

public class Sequence extends SymbolicExpression {
    private ArrayList<SymbolicExpression> exps = new ArrayList<>();

    public ArrayList<SymbolicExpression> getExps() {
        return exps;
    }

    public ArrayList<SymbolicExpression> getExpsExceptLast() {
        ArrayList<SymbolicExpression> newList = new ArrayList<>(exps);
        newList.remove(newList.size() - 1);
        return newList;
    }

    public SymbolicExpression getLast() {
        return exps.get(exps.size() - 1);
    }

    public void AddExpression(SymbolicExpression s) {
        exps.add(s);
    }
    
    public void prependExpression(SymbolicExpression s) {
        exps.add(0, s);
    }

    public void removeFirstExpression() {
        exps.remove(0);
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
}
