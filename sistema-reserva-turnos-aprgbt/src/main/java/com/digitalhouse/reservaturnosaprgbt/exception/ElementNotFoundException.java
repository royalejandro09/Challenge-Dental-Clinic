package com.digitalhouse.reservaturnosaprgbt.exception;

public class ElementNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1l;

    public ElementNotFoundException(String message) {
        super(message);
    }
}
