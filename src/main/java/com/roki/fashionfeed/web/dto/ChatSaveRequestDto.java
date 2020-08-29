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
    private String userEmail;
    private String content;

    @Builder
    public ChatSaveRequestDto(String userEmail, String content) {
        this.userEmail = userEmail;
        this.content = content;
    }

    public Chat toEntity() {
        return Chat.builder()
                .userEmail(userEmail)
                .content(content)
                .build();
    }
}
