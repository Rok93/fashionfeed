package com.roki.fashionfeed.web.dto;

import com.roki.fashionfeed.domain.chat.Chat;
import com.roki.fashionfeed.domain.feed.Feed;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
