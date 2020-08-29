package com.roki.fashionfeed.web;

import com.roki.fashionfeed.config.auth.LoginUser;
import com.roki.fashionfeed.config.auth.dto.SessionUser;
import com.roki.fashionfeed.service.ChatService;
import com.roki.fashionfeed.service.UserService;
import com.roki.fashionfeed.web.dto.ChatSaveRequestDto;
import com.roki.fashionfeed.web.dto.ChatUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class FeedChatApiController {
    private final ChatService chatService;
    private final UserService userService;

    @PostMapping("/api/{feedId}/chats")
    public Long save(@PathVariable Long feedId, @RequestBody ChatSaveRequestDto requestDto, @LoginUser SessionUser user) { //todo: @LoginUser 넣기
        requestDto.setUserEmail(user.getEmail());
        return chatService.save(feedId, requestDto);
    }

    @PutMapping("/api/{feedId}/chats/{chatId}")
    public Long update(@PathVariable Long feedId, @PathVariable Long chatId,
                       @RequestBody ChatUpdateRequestDto requestDto) {
        return chatService.update(chatId, feedId, requestDto);
    }

    @DeleteMapping("/api/chats/{chatId}")
    public Long delete(@PathVariable Long chatId) {
        chatService.delete(chatId);
        return chatId;
    }
}
