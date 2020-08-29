package com.roki.fashionfeed.domain.chat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
        String userEmail = "rok93@naver.com";


        //when
        Chat chat = chatRepository.save(Chat.builder()
                .userEmail(userEmail)
                .content(content)
                .build());

        //then
        List<Chat> chats = chatRepository.findAll();
        Chat findChat = chats.get(0);
        assertThat(chat).isEqualTo(findChat);
    }
}
