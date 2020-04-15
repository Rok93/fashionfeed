package com.roki.fashionfeed.domain.share;

import com.roki.fashionfeed.domain.feed.Feed;
import com.roki.fashionfeed.domain.feed.FeedRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
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