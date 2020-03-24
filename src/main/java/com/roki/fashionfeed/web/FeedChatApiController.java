package com.roki.fashionfeed.web;

import com.roki.fashionfeed.service.ChatService;
import com.roki.fashionfeed.web.dto.ChatSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class FeedChatApiController {
    private final ChatService chatService;

    @PostMapping("/api/{feedId}/chat")
    public Long save(@PathVariable int feedId, @RequestBody ChatSaveRequestDto requestDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (long) (int) session.getAttribute("sessionUser");
        Long longFeedId = (long) feedId;
        requestDto.setUserId(userId);
        return chatService.save(longFeedId, requestDto);
    }

    @PutMapping("/api/{feedId}/chat/{chatId}")
    public Long update(@PathVariable Long feedId, @PathVariable Long chatId, @RequestBody String content) {
        return chatService.update(chatId, feedId, content);
    }

    @DeleteMapping("/api/chat/{chatId}")
    public Long delete(@PathVariable Long chatId) {
        chatService.delete(chatId);
        return chatId;
    }
}
