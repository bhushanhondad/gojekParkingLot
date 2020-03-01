package com.gojek.validator;

public interface Validator {
    void checkIfCommandIsValid(String commands) throws Exception;
}
