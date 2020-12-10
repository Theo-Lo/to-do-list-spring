package com.thoughtworks.todolist.repository;

import com.thoughtworks.todolist.model.Label;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends MongoRepository<Label, String> {
}