package com.example.todo.todoapi.dto.request;


import com.example.todo.todoapi.entity.TodoEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode      // @Data <- 쓰지말자
@Builder
public class TodoCreateRequestDTO {

    @NotBlank
    @Size(min = 2, max = 10)
    private String title;

    // 이 dto를 entity로 변환
    public TodoEntity toEntity() {
        return TodoEntity.builder()
                .title(this.title)
                .build();
    }

}
