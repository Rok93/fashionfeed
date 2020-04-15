package com.roki.fashionfeed.domain.chat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class ChatRepositoryTest {

    @Autowired
    ChatRepository chatRepository;

    @AfterEach
    void tearDown() {
        chatRepository.deleteAll();
    }

    @Test
    @Transactional
    public void 댓글을_저장_조회한다() {
        //given
        String content = "content";
        Long userId = 1L;

        chatRepository.save(Chat.builder()
                .userId(userId)
                .content(content)
                .build());

        //when
        List<Chat> chats = chatRepository.findAll();

        //then
        Chat chat = chats.get(0);
        assertThat(content).isEqualTo(chat.getContent());
        assertThat(userId).isEqualTo(chat.getUserId());
    }
}