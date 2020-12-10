package com.thoughtworks.todolist.dto;

public class LabelResponse {
    private String id;
    private String label;
    private String color;

    public LabelResponse(String id, String label, String color) {
        this.id = id;
        this.label = label;
        this.color = color;
    }

    public LabelResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
