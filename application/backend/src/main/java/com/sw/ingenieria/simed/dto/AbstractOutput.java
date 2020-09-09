package com.sw.ingenieria.simed.dto;
/**
 * @author Johan Céspedes Ortega at PUJ
 * @project SiMed
 * @date 07/09/2020
 */

public abstract class AbstractOutput {

    private int status;
    private String message;

    public AbstractOutput() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
