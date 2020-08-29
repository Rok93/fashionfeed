package com.roki.fashionfeed.domain.likefull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LikefullRepositoryTest {

    @Autowired
    LikefullRepository likefullRepository;

    @AfterEach
    void tearDown() {
        likefullRepository.deleteAll();
    }

    @Test
    @Transactional
    public void 좋아요를_저장_조회한다() {
        //given
        LikeType likeType = LikeType.UNLIKE;
        Long userId = 1L;
        likefullRepository.save(Likefull.builder()
                .userId(userId)
                .likeType(likeType)
                .build());

        //when
        List<Likefull> likefulls = likefullRepository.findAll();

        //then
        Likefull likefull = likefulls.get(0);
        assertThat(likeType).isEqualTo(likefull.getLikeType());
        assertThat(userId).isEqualTo(likefull.getUserId());
    }
}
