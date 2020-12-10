package com.thoughtworks.todolist.dto;

import com.thoughtworks.todolist.model.Label;

import java.util.ArrayList;
import java.util.List;

public class TodoResponse {
    private String id;
    private String text;
    private Boolean done;
    private List<Label> labels = new ArrayList<>();

    public TodoResponse(String id, String text, Boolean done, List<Label> labels) {
        this.id = id;
        this.text = text;
        this.done = done;
        this.labels = labels;
    }

    public TodoResponse() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }
}
