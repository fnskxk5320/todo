package com.example.todo.todoapi.dto.response;

import lombok.*;

import java.util.List;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoListResponseDTO {

    private String error; // 에러 발생 시 클라이언트에게 전달할 메시지

    private List<TodoDetailResponseDTO> todos;
}
