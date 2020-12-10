package service;

import exception.LabelNotFoundException;
import exception.TodoNotFoundException;
import model.Label;
import model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import repository.TodoRepository;

import java.util.List;

public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private LabelService labelService;

    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    public Todo getTodo(String todoId) throws TodoNotFoundException {
        return todoRepository.findById(todoId).orElseThrow(TodoNotFoundException::new);
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo updateTodo(String todoId, Todo todoUpdated) throws TodoNotFoundException, LabelNotFoundException {
        if (this.todoRepository.existsById(todoId)) {
            if (todoUpdated.getLabelIdList().stream().allMatch(labelService::labelExists)) {
                todoUpdated.setId(todoId);
                return todoRepository.save(todoUpdated);
            }
            throw new LabelNotFoundException();
        }
        throw new TodoNotFoundException();
    }

    public void deleteTodo(String labelId) throws TodoNotFoundException {
        if (this.todoRepository.existsById(labelId)) {
            todoRepository.deleteById(labelId);
            return;
        }
        throw new TodoNotFoundException();
    }
}
