package org.ioopm.calculator.ast;

import java.util.Stack;

public class StackEnvironment extends Environment {
    private Stack<Environment> stack;

    public StackEnvironment(Environment global) {
        Stack<Environment> s = new Stack<>();
        s.push(global);
        this.stack = s;
    }

    @Override
    public SymbolicExpression get(Object key) {
        @SuppressWarnings("unchecked") Stack<Environment> copy = (Stack<Environment>)this.stack.clone();
        while (!copy.empty()) {
            Environment env = copy.pop();
            if (env.containsKey(key)) {
                return env.get(key);
            }
        }
        return null;
    }

    public void stack_put(Variable key, SymbolicExpression value) {
        Environment env = this.stack.peek();
        env.put(key, value);
    }

    public StackEnvironment pushEnvironment(Environment env) {
        this.stack.push(env);
        return this;
    }

    public StackEnvironment popEnvironment() {
        this.stack.pop();
        return this;
    }
}
