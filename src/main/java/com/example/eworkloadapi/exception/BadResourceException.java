package com.example.eworkloadapi.exception;

import java.util.ArrayList;
import java.util.List;

public class BadResourceException extends Exception {

    List<String> errorMessages = new ArrayList<>();

    public BadResourceException() {
    }

    public BadResourceException(String msg) {
        super(msg);
    }

    /**
     *
     * @return errorMessages
     */
    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public void addErrorMessages(String msg) {
        this.errorMessages.add(msg);
    }

}
