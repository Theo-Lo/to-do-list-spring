package service;

import model.Label;
import model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import repository.TodoRepository;

import java.util.List;

public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }
}
