package com.thoughtworks.todolist.controller;

import com.thoughtworks.todolist.dto.TodoRequest;
import com.thoughtworks.todolist.dto.TodoResponse;
import com.thoughtworks.todolist.exception.LabelNotFoundException;
import com.thoughtworks.todolist.exception.TodoNotFoundException;
import com.thoughtworks.todolist.mapper.TodoMapper;
import com.thoughtworks.todolist.model.Todo;
import com.thoughtworks.todolist.service.TodoService;
import com.thoughtworks.todolist.mapper.LabelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todos")
@CrossOrigin
public class TodoController {
    @Autowired
    private TodoService todoService;
    @Autowired
    private TodoMapper todoMapper;
    @Autowired
    private LabelMapper labelMapper;

    @GetMapping
    public List<TodoResponse> getTodos() {
        List<Todo> todos = this.todoService.getTodos();
        return todos.stream().map(todoMapper::toResponse).collect(Collectors.toList());
    }

    @GetMapping("/{todoId}")
    public TodoResponse getTodo(@PathVariable String todoId) throws TodoNotFoundException {
        return todoMapper.toResponse(this.todoService.getTodo(todoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse createTodo(@RequestBody TodoRequest todoUpdate) throws LabelNotFoundException {
        Todo todo = todoService.createTodo(todoMapper.toEntity(todoUpdate));
        return todoMapper.toResponse(todoService.createTodo(todo));
    }

    @PutMapping("/{todoId}")
    public TodoResponse updateTodo(@PathVariable String todoId, @RequestBody TodoRequest todoUpdated) throws TodoNotFoundException, LabelNotFoundException {
        Todo todo = todoService.updateTodo(todoId, todoMapper.toEntity(todoUpdated));
        return todoMapper.toResponse(todoService.createTodo(todo));
    }

    @DeleteMapping("/{todoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable String todoId) throws TodoNotFoundException {
        todoService.deleteTodo(todoId);
    }
}
