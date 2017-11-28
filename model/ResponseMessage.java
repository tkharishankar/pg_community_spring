package com.pg.model;

public class ResponseMessage {

    public String message;
    public boolean status;

    public ResponseMessage(String msg, boolean state) {
        this.message = msg;
        this.status = state;
    }
}
