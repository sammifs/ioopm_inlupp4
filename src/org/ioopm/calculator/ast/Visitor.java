package org.ioopm.calculator.ast;

public interface Visitor {
    public SymbolicExpression visit(Addition n);
    public SymbolicExpression visit(Assignment n);
    public SymbolicExpression visit(Clear n);
    public SymbolicExpression visit(Constant n);
    public SymbolicExpression visit(Cos n);
    public SymbolicExpression visit(Division n);
    public SymbolicExpression visit(Exp n);
    public SymbolicExpression visit(Log n);
    public SymbolicExpression visit(Multiplication n);
    public SymbolicExpression visit(BooleanEquals n);
    public SymbolicExpression visit(BooleanLess n);
    public SymbolicExpression visit(BooleanMore n);
    public SymbolicExpression visit(Negation n);
    public SymbolicExpression visit(Quit n);
    public SymbolicExpression visit(Sin n);
    public SymbolicExpression visit(Subtraction n);
    public SymbolicExpression visit(Variable n);
    public SymbolicExpression visit(Vars n);
    public SymbolicExpression visit(Scope n);
    public SymbolicExpression visit(Conditional n);
    public SymbolicExpression visit(FunctionDeclaration n);
    public SymbolicExpression visit(Sequence n);
    public SymbolicExpression visit(FunctionCall n);
    public SymbolicExpression visit(End n);
}
