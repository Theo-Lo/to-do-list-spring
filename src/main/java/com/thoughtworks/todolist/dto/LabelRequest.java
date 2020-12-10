package com.thoughtworks.todolist.dto;

public class LabelRequest {
    private String label;
    private String color;

    public LabelRequest(String label, String color) {
        this.label = label;
        this.color = color;
    }

    public LabelRequest() {

    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
