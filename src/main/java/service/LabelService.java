package service;

import exception.LabelNotFoundException;
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

    public Label getLabel(String labelId) throws LabelNotFoundException {
        return labelRepository.findById(labelId).orElseThrow(LabelNotFoundException::new);
    }

    public Label createLabel(Label label) {
        return labelRepository.save(label);
    }

    public void deleteLabel(String labelId) throws LabelNotFoundException {
        if (this.labelRepository.existsById(labelId)) {
            labelRepository.deleteById(labelId);
            return;
        }
        throw new LabelNotFoundException();
    }

}
