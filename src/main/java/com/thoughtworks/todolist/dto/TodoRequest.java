package com.thoughtworks.todolist.dto;

import java.util.List;

public class TodoRequest {
    private String text;
    private Boolean done;
    private List<String> labelIdList;

    public TodoRequest(String text, Boolean done, List<String> labelIdList) {
        this.text = text;
        this.done = done;
        this.labelIdList = labelIdList;
    }

    public TodoRequest() {

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

    public List<String> getLabelIdList() {
        return labelIdList;
    }

    public void setLabelIdList(List<String> labelIdList) {
        this.labelIdList = labelIdList;
    }
}
