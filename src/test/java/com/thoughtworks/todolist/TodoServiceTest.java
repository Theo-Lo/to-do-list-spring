package com.thoughtworks.todolist;

import com.thoughtworks.todolist.exception.LabelNotFoundException;
import com.thoughtworks.todolist.exception.TodoNotFoundException;
import com.thoughtworks.todolist.model.Todo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.thoughtworks.todolist.repository.TodoRepository;
import com.thoughtworks.todolist.service.LabelService;
import com.thoughtworks.todolist.service.TodoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {
    @InjectMocks
    TodoService todoService;

    @Mock
    TodoRepository todoRepository;

    @Mock
    LabelService labelService;

    private final String todoId = "1";

    @Test
    void should_return_all_todos_when_get_all_given_all_todos() {
        //given
        final List<Todo> expected = new ArrayList<>();
        expected.add(new Todo());
        expected.add(new Todo());
        when(todoRepository.findAll()).thenReturn(expected);

        //when
        final List<Todo> actual = todoService.getTodos();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_a_todo_when_get_given_todo_id() throws TodoNotFoundException {
        //given
        Todo expected = new Todo();
        when(todoRepository.findById(todoId)).thenReturn(Optional.of(expected));

        //when
        final Todo actual = todoService.getTodo(todoId);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_todo_not_found_exception_when_get_todo_given_a_wrong_todo_id() {
        //given
        //when
        final TodoNotFoundException TodoNotFoundException = assertThrows(TodoNotFoundException.class, () -> todoService.getTodo(todoId));

        //then
        assertEquals("Todo Not Found.", TodoNotFoundException.getMessage());
    }

    @Test
    void should_return_created_todo_when_create_todo_given_a_todo() throws LabelNotFoundException {
        //given
        Todo expected = new Todo();
        expected.setLabelIdList(new ArrayList<>());
        when(todoRepository.save(any())).thenReturn(expected);

        //when
        final Todo actual = todoService.createTodo(expected);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_label_not_found_exception_todo_when_create_todo_given_a_todo_with_non_existing_label_id() {
        //given
        Todo expected = new Todo();
        List<String> labelsId = new ArrayList<>();
        labelsId.add("123");
        expected.setLabelIdList(labelsId);

        //when
        final LabelNotFoundException LabelNotFoundException = assertThrows(LabelNotFoundException.class, () -> todoService.createTodo(expected));

        //then
        assertEquals("Label Not Found.", LabelNotFoundException.getMessage());
    }

    @Test
    public void should_return_updated_todo_when_updated_todo_given_todo_and_todo_id() throws TodoNotFoundException, LabelNotFoundException {
        //given
        final Todo expected = new Todo();
        expected.setLabelIdList(new ArrayList<>());
        when(todoRepository.existsById(any())).thenReturn(true);
        when(todoRepository.save(any())).thenReturn(expected);
        //when
        final Todo actual = todoService.updateTodo(expected.getId(), expected);
        //then
        assertEquals(expected, actual);
    }

    @Test
    public void should_throw_todo_not_found_exception_when_update_todo_given_wrong_id() {
        //when
        final TodoNotFoundException TodoNotFoundException = assertThrows(TodoNotFoundException.class, () -> todoService.updateTodo("", new Todo()));
        //then
        assertEquals("Todo Not Found.", TodoNotFoundException.getMessage());
    }

    @Test
    public void should_throw_label_not_found_exception_when_update_todo_given_invalid_label() {
        //given
        List<String> labelsId = new ArrayList<>();
        labelsId.add("123");
        final Todo expected = new Todo("Test1", false, labelsId);
        when(todoRepository.existsById(any())).thenReturn(true);
        //when
        final LabelNotFoundException LabelNotFoundException = assertThrows(LabelNotFoundException.class, () -> todoService.updateTodo(expected.getId(), expected));
        //then
        assertEquals("Label Not Found.", LabelNotFoundException.getMessage());
    }

    @Test
    void should_call_repository_delete_by_id_when_delete_todo_given_a_todo_id() throws TodoNotFoundException {
        //given
        when(todoRepository.existsById(any())).thenReturn(true);

        //when
        todoService.deleteTodo(todoId);

        //then
        verify(todoRepository, times(1)).deleteById(todoId);
    }

    @Test
    void should_return_todo_not_found_exception_when_delete_todo_given_a_wrong_todo_id() {
        //given
        //when
        final TodoNotFoundException TodoNotFoundException = assertThrows(TodoNotFoundException.class, () -> todoService.deleteTodo(todoId));

        //then
        assertEquals("Todo Not Found.", TodoNotFoundException.getMessage());
    }
}
