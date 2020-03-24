package com.roki.fashionfeed.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatUpdateDto {
    private Long id;
    private Long userId;
    private String content;

    @Builder
    public ChatUpdateDto(String content) {
        this.content = content;
    }

}
