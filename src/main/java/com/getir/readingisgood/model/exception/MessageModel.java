package com.getir.readingisgood.model.exception;

import javax.print.attribute.standard.Severity;
import java.io.Serializable;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
public class MessageModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private Severity severity;
    private String code;
    private String message;

    public MessageModel() {
    }

    public MessageModel(Severity severity, String code, String message) {
        this.severity = severity;
        this.code = code;
        this.message = message;
    }

    public Severity getSeverity() {
        return this.severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
