package com.thoughtworks.todolist.repository;

import com.thoughtworks.todolist.model.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends MongoRepository<Todo, String> {
    List<Todo> findByLabelIdList(String labelId);
}
