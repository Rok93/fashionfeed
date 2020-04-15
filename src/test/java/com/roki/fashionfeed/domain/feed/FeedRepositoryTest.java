package com.roki.fashionfeed.domain.feed;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class FeedRepositoryTest {

    @Autowired
    FeedRepository feedRepository;

    @AfterEach
    void tearDown() {
        feedRepository.deleteAll();
    }

    @Test
    public void Feed를_저장_조회한다() {
        //given
        String feedTitle = "feedTitle";
        String feedContent = "feedText";
        String feedImage = "feedImage";
        feedRepository.save(Feed.builder()
                .feedTitle(feedTitle)
                .feedContent(feedContent)
                .feedImage(feedImage)
                .build());

        //when
        List<Feed> feeds = feedRepository.findAll();

        //then
        Feed findFeed = feeds.get(0);
        assertThat(feedTitle).isEqualTo(findFeed.getFeedTitle());
        assertThat(feedContent).isEqualTo(findFeed.getFeedContent());
        assertThat(feedImage).isEqualTo(findFeed.getFeedImage());
    }
}
