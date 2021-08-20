package com.learncoding.eworkloadapi.exception;

import lombok.Getter;

@Getter
public class ResourceAlreadyExists extends Exception {

    public ResourceAlreadyExists() {
    }

    public ResourceAlreadyExists(String msg) {
        super(msg);
    }
}
