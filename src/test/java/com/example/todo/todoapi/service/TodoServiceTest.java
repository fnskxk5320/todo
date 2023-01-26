package com.example.todo.todoapi.service;

import com.example.todo.todoapi.dto.request.TodoCreateRequestDTO;
import com.example.todo.todoapi.dto.request.TodoModifyRequestDTO;
import com.example.todo.todoapi.dto.response.TodoDetailResponseDTO;
import com.example.todo.todoapi.dto.response.TodoListResponseDTO;
import com.example.todo.todoapi.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Commit
class TodoServiceTest {

    @Autowired
    TodoService todoService;
    @Autowired
    TodoRepository todoRepository;

    @BeforeEach
    void beforeInsert() {
        TodoCreateRequestDTO todo1 = TodoCreateRequestDTO.builder()
                .title("수업 마치기")
                .build();

        TodoCreateRequestDTO todo2 = TodoCreateRequestDTO.builder()
                .title("집까지 걸어가기")
                .build();

        TodoCreateRequestDTO todo3 = TodoCreateRequestDTO.builder()
                .title("편히 쉬기")
                .build();

        todoService.create(todo1);
        todoService.create(todo2);
        todoService.create(todo3);
    }

    @Test
    @DisplayName("새로운 할 일을 등록하면 생성되는 리슽는 할 일이 4개 들어있야 한다.")
    void createTest() {

        // given
        TodoCreateRequestDTO newTodo = TodoCreateRequestDTO.builder()
                .title("새롭게 할 일 있나?")
                .build();

        // when
        TodoListResponseDTO responseDTO = todoService.create(newTodo);

        // then
        List<TodoDetailResponseDTO> todos = responseDTO.getTodos();
        assertEquals(4, todos.size());

        System.out.println("=====================================");
        todos.forEach(System.out::println);
    }

    @Test
    @DisplayName("두 번째 할 일의 제목을 수정수정으로 수정하고 할 일 완료처리를 해야한다.")
    void updateTest() {
        // given
        String newTitle = "수정수정";
        boolean newDone = true;

        TodoModifyRequestDTO modifyRequestDTO = TodoModifyRequestDTO.builder()
                .title(newTitle)
                .done(newDone)
                .build();

        // when
        TodoDetailResponseDTO targetTodo = todoService.retrieve().getTodos().get(1);

        TodoListResponseDTO responseDTO = todoService.update(targetTodo.getId(), modifyRequestDTO);

        // then
        assertEquals("수정수정", responseDTO.getTodos().get(1).getTitle());
        assertTrue(responseDTO.getTodos().get(1).isDone());

        System.out.println("===========================================");
        responseDTO.getTodos().forEach(System.out::println);

    }

    @Test
    @DisplayName("두 번째꺼 삭제를 해봅세다")
    void deleteTest() {
        // given
        TodoDetailResponseDTO targetTodo = todoService.retrieve().getTodos().get(1);
        String id = targetTodo.getId();
        // when
        TodoListResponseDTO deleteDTO = todoService.delete(id);

        // then
        assertEquals(2, deleteDTO.getTodos().size());
    }

}