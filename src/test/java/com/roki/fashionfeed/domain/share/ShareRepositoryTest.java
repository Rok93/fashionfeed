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

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class ShareRepositoryTest {

    @Autowired
    FeedRepository feedRepository;

    @Autowired
    ShareRepository shareRepository;

    @AfterEach
    void tearDown() {
        feedRepository.deleteAll();
        shareRepository.deleteAll();
    }

    @Test
    @Transactional
    public void Share를_조회한다() {
        Long userId = 1L;
        ShareStatus shareStatus = ShareStatus.UNSHARE;
        Share share = Share.builder()
                .userId(userId)
                .shareStatus(shareStatus)
                .build();

        String feedTitle = "feedTitle";
        String feedImage = "feedImage";
        String feedContent = "content";
        Feed feed = Feed.builder()
                .feedTitle(feedTitle)
                .feedImage(feedImage)
                .feedContent(feedContent)
                .build();

        share.setFeed(feed);
        feedRepository.save(feed);

        Feed findFeed = feedRepository.findAll().get(0);
        assertThat(userId).isEqualTo(findFeed.getShares().get(0).getUserId());
        assertThat(shareStatus).isEqualTo(findFeed.getShares().get(0).getShareStatus());
        assertThat(feedTitle).isEqualTo(findFeed.getFeedTitle());
        assertThat(feedImage).isEqualTo(findFeed.getFeedImage());
        assertThat(feedContent).isEqualTo(findFeed.getFeedContent());

        Share findShare = shareRepository.findAll().get(0);
        assertThat(userId).isEqualTo(findShare.getUserId());
        assertThat(shareStatus).isEqualTo(findShare.getShareStatus());
        assertThat(feedTitle).isEqualTo(findShare.getFeed().getFeedTitle());
        assertThat(feedImage).isEqualTo(findShare.getFeed().getFeedImage());
        assertThat(feedContent).isEqualTo(findShare.getFeed().getFeedContent());
    }
}