package com.thoughtworks.todolist;

import exception.LabelNotFoundException;
import model.Label;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.LabelRepository;
import service.LabelService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LabelServiceTest {
    @InjectMocks
    LabelService labelService;

    @Mock
    LabelRepository labelRepository;

    private final String labelId = "1";

    @Test
    void should_return_all_labels_when_get_all_given_all_labels() {
        //given
        final List<Label> expected = new ArrayList<>();
        expected.add(new Label());
        expected.add(new Label());
        when(labelRepository.findAll()).thenReturn(expected);

        //when
        final List<Label> actual = labelService.getLabels();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_a_label_when_get_given_label_id() throws LabelNotFoundException {
        //given
        Label expected = new Label();
        when(labelRepository.findById(labelId)).thenReturn(Optional.of(expected));

        //when
        final Label actual = labelService.getLabel(labelId);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_label_not_found_exception_when_get_label_given_a_wrong_label_id() {
        //given
        //when
        final LabelNotFoundException LabelNotFoundException = assertThrows(LabelNotFoundException.class, () -> labelService.getLabel(labelId));

        //then
        assertEquals("Label Not Found.", LabelNotFoundException.getMessage());
    }
}
