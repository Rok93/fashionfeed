package com.roki.fashionfeed.domain.chat;

import com.roki.fashionfeed.domain.feed.Feed;
import com.roki.fashionfeed.domain.feed.FeedRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class ChatRepositoryTest {

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    FeedRepository feedRepository;

    @AfterEach
    void tearDown() {
        chatRepository.deleteAll();
        feedRepository.deleteAll();
    }

    @Test
    @Transactional
    public void Chat을_조회하다() {
        String content = "content";
        Long userId = 1L; //임의로 지정
        Chat chat = Chat.builder()
                .userId(userId)
                .content(content)
                .build();

        String feedTitle = "feedTitle";
        String feedImage = "feedImage";
        String feedContent = "content";
        Feed feed = Feed.builder()
                .feedTitle(feedTitle)
                .feedImage(feedImage)
                .feedContent(feedContent)
                .build();

        chat.setFeed(feed);
        feedRepository.save(feed);

        Feed findFeed = feedRepository.findAll().get(0);
        assertThat(feedTitle).isEqualTo(findFeed.getFeedTitle());
        assertThat(feedImage).isEqualTo(findFeed.getFeedImage());
        assertThat(feedContent).isEqualTo(findFeed.getFeedContent());
        assertThat(content).isEqualTo(findFeed.getChats().get(0).getContent());
        assertThat(userId).isEqualTo(findFeed.getChats().get(0).getUserId());

        Chat findChat = chatRepository.findAll().get(0);
        assertThat(feedTitle).isEqualTo(findChat.getFeed().getFeedTitle());
        assertThat(feedImage).isEqualTo(findChat.getFeed().getFeedImage());
        assertThat(feedContent).isEqualTo(findChat.getFeed().getFeedContent());
        assertThat(content).isEqualTo(findChat.getContent());
        assertThat(userId).isEqualTo(findChat.getUserId());
    }

    @Test
    @Transactional
    public void feedChat을_수정하다() {
        saveNumberOfFeed(2);
        Feed feed = feedRepository.findAll().get(1);
        Long chatId = feed.getChats().get(0).getId();
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다 댓글id=" + chatId));
        Long userId = chat.getUserId();
        String expetedChatContent = "바뀐 댓글입니다.";
        chat.setContent(expetedChatContent);
        chatRepository.save(chat);

        Feed findFeed = feedRepository.findAll().get(1);
        assertThat(expetedChatContent).isEqualTo(findFeed.getChats().get(0).getContent());
        assertThat(userId).isEqualTo(findFeed.getChats().get(0).getUserId());
    }

    // Feed를 number개 생성한다. (필요하다면 테스트 시작전에 개별적으로 실행시킬 것)
    private void saveNumberOfFeed(int number) {
        IntStream.rangeClosed(1, number)
                .forEach(n -> {
                    Chat chat = Chat.builder()
                            .userId(Long.parseLong(String.valueOf(n)))
                            .content("feedContent" + n)
                            .build();

                    Feed feed = Feed.builder()
                            .feedTitle("feedTitle" + n)
                            .feedContent("feedContent" + n)
                            .feedImage("feedImage" + n)
                            .build();

                    chat.setFeed(feed);
                    feedRepository.save(feed);
                });
    }
}