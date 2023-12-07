package org.ioopm.calculator.ast;

import java.util.HashMap;
import java.util.TreeSet;

public class Environment extends HashMap<Variable, SymbolicExpression>{
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Variables: ");
        TreeSet<Variable> vars = new TreeSet<Variable>(this.keySet());
        for (Variable v : vars) {
            sb.append(v.getName());
            sb.append(" = ");
            sb.append(this.get(v));
            sb.append(", ");
        }
        sb.replace(sb.length()-2, sb.length(), "");
        return sb.toString();
    }
}
