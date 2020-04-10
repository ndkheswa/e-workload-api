package com.learncoding.eworkloadjava.exception;

import java.util.ArrayList;
import java.util.List;

public class BadResourceException extends Exception {

    List<String> errorMessages = new ArrayList<>();

    public BadResourceException() {
    }

    public BadResourceException(String message) { super(message); }

    public void addErrorMessage(String message) {
        errorMessages.add(message);
    }

}
