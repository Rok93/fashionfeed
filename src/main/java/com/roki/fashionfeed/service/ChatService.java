package com.roki.fashionfeed.service;

import com.roki.fashionfeed.domain.chat.Chat;
import com.roki.fashionfeed.domain.chat.ChatRepository;
import com.roki.fashionfeed.domain.feed.Feed;
import com.roki.fashionfeed.domain.feed.FeedRepository;
import com.roki.fashionfeed.web.dto.ChatSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final FeedRepository feedRepository;

    @Transactional
    public Long save(Long feedId, ChatSaveRequestDto requestDto) {
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 피드입니다. id=" + feedId));
        Chat chatToFeed = requestDto.toEntity();

        chatToFeed.setFeed(feed);
        chatRepository.save(chatToFeed);
        return chatToFeed.getId();
    }

    @Transactional
    public Long update(Long chatId, Long feedId, String content) {
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 피드가 없습니다. id=" + feedId));
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 댓글이 없습니다. id=" + chatId));

        chat.setContent(content);
        chat.setFeed(feed);
        chatRepository.save(chat);

        return chat.getId();
    }

    @Transactional
    public void delete(Long chatId) {
        chatRepository.deleteById(chatId);
    }
}
