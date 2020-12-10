package com.thoughtworks.todolist.service;

import com.thoughtworks.todolist.exception.LabelNotFoundException;
import com.thoughtworks.todolist.exception.TodoNotFoundException;
import com.thoughtworks.todolist.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import com.thoughtworks.todolist.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    public Todo createTodo(Todo todo) throws LabelNotFoundException {
        if (todo.getLabelIdList().stream().allMatch(labelService::labelExists)) {
            return todoRepository.save(todo);
        }
        throw new LabelNotFoundException();
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

    public void removeLabelFromTodo(String labelId){
        for(Todo todo: todoRepository.findByLabelIdList(labelId)){
            List<String> labelIdList = todo.getLabelIdList();
            labelIdList.remove(labelId);
            todo.setLabelIdList(labelIdList);
            todoRepository.save(todo);
        }
    }
}
