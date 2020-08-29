package com.roki.fashionfeed.domain.share;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ShareRepositoryTest {

    @Autowired
    ShareRepository shareRepository;

    @AfterEach
    void tearDown() {
        shareRepository.deleteAll();
    }

    @Test
    @Transactional
    public void Share를_조회한다() {
        //given
        Long userId = 1L;
        ShareStatus shareStatus = ShareStatus.UNSHARE;

        shareRepository.save(Share.builder()
                .userId(userId)
                .shareStatus(shareStatus)
                .build());

        //when
        List<Share> shares = shareRepository.findAll();

        //then
        Share share = shares.get(0);
        assertThat(userId).isEqualTo(share.getUserId());
        assertThat(shareStatus).isEqualTo(share.getShareStatus());
    }
}
