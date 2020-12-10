package com.thoughtworks.todolist;

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

    private final String employeeId = "1";

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
}
