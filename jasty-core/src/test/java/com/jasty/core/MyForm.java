package com.jasty.core;

public class MyForm extends Form {

    public String output;

    public void successfulEvent(EventArgs e) {
        output = e.getSrcId() + "/" + e.get("someParameter");
    }

    public void erroneousEvent(EventArgs e) {
        throw new RuntimeException("some error");
    }
}
