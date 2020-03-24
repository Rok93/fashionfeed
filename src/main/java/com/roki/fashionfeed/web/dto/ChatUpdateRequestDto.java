package com.roki.fashionfeed.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatUpdateRequestDto {
    private Long id;
    private Long userId;
    private String content;

    @Builder
    public ChatUpdateRequestDto(String content) {
        this.content = content;
    }

}
