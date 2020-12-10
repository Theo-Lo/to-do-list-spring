package model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document
public class Todo {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String text;
    private Boolean done;
    private List<String> labelIdList;

    public Todo() {
    }

    public Todo(String text, Boolean done, List<String> labelIdList) {
        this.text = text;
        this.done = done;
        this.labelIdList = labelIdList;
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

    public List<String> getLabelIdList() {
        return labelIdList;
    }

    public void setLabelIdList(List<String> labelIdList) {
        this.labelIdList = labelIdList;
    }
}

