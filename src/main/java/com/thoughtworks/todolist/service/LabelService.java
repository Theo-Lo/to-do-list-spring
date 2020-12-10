package com.thoughtworks.todolist.service;

import com.thoughtworks.todolist.exception.LabelNotFoundException;
import com.thoughtworks.todolist.model.Label;
import com.thoughtworks.todolist.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelService {
    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private TodoService todoService;

    public List<Label> getLabels() {
        return labelRepository.findAll();
    }

    public Label getLabel(String labelId) throws LabelNotFoundException {
        return labelRepository.findById(labelId).orElseThrow(LabelNotFoundException::new);
    }

    public Label createLabel(Label label) {
        return labelRepository.save(label);
    }

    public void deleteLabel(String labelId) throws LabelNotFoundException {
        if (this.labelRepository.existsById(labelId)) {
            labelRepository.deleteById(labelId);
            todoService.removeLabelFromTodo(labelId);
            return;
        }
        throw new LabelNotFoundException();
    }

    public boolean labelExists(String labelId) {
        return labelRepository.existsById(labelId);
    }
}
