package service;

import model.Label;
import org.springframework.beans.factory.annotation.Autowired;
import repository.LabelRepository;

import java.util.List;

public class LabelService {
    @Autowired
    private LabelRepository labelRepository;

    public List<Label> getLabels() {
        return labelRepository.findAll();
    }

}
