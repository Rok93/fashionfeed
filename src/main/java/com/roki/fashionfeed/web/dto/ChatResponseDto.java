package com.roki.fashionfeed.web.dto;

import com.roki.fashionfeed.domain.chat.Chat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
public class ChatResponseDto {
    private Long id;
    private Long userId;
    private Long feedId;
    private String content;
    private String createdDate;
    private LocalDateTime modifiedDate;

    public ChatResponseDto(Chat entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.feedId = entity.getFeed().getId();
        this.content = entity.getContent();
        this.createdDate = entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        this.modifiedDate = entity.getModifiedDate();
    }
}
