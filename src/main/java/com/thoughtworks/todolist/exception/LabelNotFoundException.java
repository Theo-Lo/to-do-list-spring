package com.thoughtworks.todolist.exception;

public class LabelNotFoundException extends Exception {
    public LabelNotFoundException() {
        super("Label Not Found.");
    }
}
