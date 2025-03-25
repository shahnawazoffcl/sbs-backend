package com.shah.sbsbackend.exceptions;

public class MessageNotSentException extends RuntimeException {
    public MessageNotSentException(String s) {
        super(s);
    }
}
