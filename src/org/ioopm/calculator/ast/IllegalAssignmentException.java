package org.ioopm.calculator.ast;

public class IllegalAssignmentException extends RuntimeException  {
    public IllegalAssignmentException(String string) {
        super(string);
    }
}
