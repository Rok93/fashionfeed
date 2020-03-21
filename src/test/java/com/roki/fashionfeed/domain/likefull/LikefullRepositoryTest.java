package com.roki.fashionfeed.domain.likefull;

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
class LikefullRepositoryTest {

    @Autowired
    LikefullRepository likefullRepository;

    @Autowired
    FeedRepository feedRepository;

    @AfterEach
    void tearDown() {
        likefullRepository.deleteAll();
        feedRepository.deleteAll();
    }

    @Test
    @Transactional
    public void likefull을_조회한다() {
        LikeType likeType = LikeType.UNLIKE;
        Long userId = 1L;
        Likefull likefull = Likefull.builder()
                .userId(userId)
                .likeType(likeType)
                .build();

        String feedTitle = "feedTitle";
        String feedImage = "feedImage";
        String feedContent = "content";
        Feed feed = Feed.builder()
                .feedTitle(feedTitle)
                .feedImage(feedImage)
                .feedContent(feedContent)
                .build();

        likefull.setFeed(feed);
        feedRepository.save(feed);

        Feed findFeed = feedRepository.findAll().get(0);
        assertThat(likeType).isEqualTo(findFeed.getLikefulls().get(0).getLikeType());
        assertThat(userId).isEqualTo(findFeed.getLikefulls().get(0).getUserId());
        assertThat(feedTitle).isEqualTo(findFeed.getFeedTitle());
        assertThat(feedImage).isEqualTo(findFeed.getFeedImage());
        assertThat(feedContent).isEqualTo(findFeed.getFeedContent());

        Likefull findLikefull = likefullRepository.findAll().get(0);
        assertThat(likeType).isEqualTo(findLikefull.getLikeType());
        assertThat(userId).isEqualTo(findLikefull.getUserId());
        assertThat(feedTitle).isEqualTo(findLikefull.getFeed().getFeedTitle());
        assertThat(feedImage).isEqualTo(findLikefull.getFeed().getFeedImage());
        assertThat(feedContent).isEqualTo(findLikefull.getFeed().getFeedContent());

    }
}