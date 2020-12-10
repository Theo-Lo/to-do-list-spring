package com.thoughtworks.todolist.controller;

import com.thoughtworks.todolist.dto.LabelRequest;
import com.thoughtworks.todolist.exception.LabelNotFoundException;
import com.thoughtworks.todolist.dto.LabelResponse;
import com.thoughtworks.todolist.mapper.LabelMapper;
import com.thoughtworks.todolist.model.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.thoughtworks.todolist.service.LabelService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/labels")
@CrossOrigin
public class LabelController {
    @Autowired
    private LabelService labelService;
    @Autowired
    private LabelMapper labelMapper;

    @GetMapping
    public List<LabelResponse> getLabels() {
        return labelService.getLabels().stream().map(labelMapper::toResponse).collect(Collectors.toList());
    }

    @GetMapping("/{labelId}")
    public LabelResponse getLabel(@PathVariable String labelId) throws LabelNotFoundException {
        return labelMapper.toResponse(labelService.getLabel(labelId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LabelResponse createLabel(@RequestBody LabelRequest labelRequest) {
        Label label = labelService.createLabel(labelMapper.toEntity(labelRequest));
        return labelMapper.toResponse(label);
    }

    @DeleteMapping("/{labelId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLabel(@PathVariable String labelId) throws LabelNotFoundException {
        labelService.deleteLabel(labelId);
    }
}
