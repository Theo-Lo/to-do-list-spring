package model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public class Todo {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String name;
    private Integer age;
    private String gender;
    private Integer salary;

    public Todo() {
    }

    public Todo(String name, int age, String gender, int salary) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }
}

