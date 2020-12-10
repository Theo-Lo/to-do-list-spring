package com.thoughtworks.todolist.mapper;

import com.thoughtworks.todolist.dto.TodoRequest;
import com.thoughtworks.todolist.dto.TodoResponse;
import com.thoughtworks.todolist.model.Label;
import com.thoughtworks.todolist.model.Todo;
import com.thoughtworks.todolist.service.LabelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TodoMapper {
    @Autowired
    private LabelService labelService;

    public Todo toEntity(TodoRequest todoRequest) {
        Todo todo = new Todo();

        BeanUtils.copyProperties(todoRequest, todo);

        return todo;
    }

    public TodoResponse toResponse(Todo todo) {
        TodoResponse todoResponse = new TodoResponse();

        BeanUtils.copyProperties(todo, todoResponse);
        List<Label> labels = new ArrayList<>();
        for(String labelId: todo.getLabelIdList()){
            try{
                labels.add(labelService.getLabel(labelId));
            }catch(Exception e){
                // do nothing
            }
        }
        todoResponse.setLabels(labels);

        return todoResponse;
    }
}
