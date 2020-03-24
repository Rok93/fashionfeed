package com.roki.fashionfeed.web.dto;

import com.roki.fashionfeed.domain.chat.Chat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatSaveRequestDto {
    private Long userId;
    private String content;

    @Builder
    public ChatSaveRequestDto(Long userId, String content) {
        this.userId = userId;
        this.content = content;
    }

    public Chat toEntity() {
        return Chat.builder()
                .userId(userId)
                .content(content)
                .build();
    }
}
