package mapper;

import dto.LabelRequest;
import dto.LabelResponse;
import model.Label;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LabelMapper {

    public Label toEntity(LabelRequest labelRequest) {
        Label label = new Label();

        BeanUtils.copyProperties(labelRequest, label);

        return label;
    }

    public LabelResponse toResponse(Label label) {
        LabelResponse labelResponse = new LabelResponse();

        BeanUtils.copyProperties(label, labelResponse);
        //todoResponse.setLabels();

        return labelResponse;
    }
}
