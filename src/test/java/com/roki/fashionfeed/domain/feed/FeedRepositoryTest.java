package com.roki.fashionfeed.domain.feed;

import com.roki.fashionfeed.domain.chat.Chat;
import com.roki.fashionfeed.domain.chat.ChatRepository;
import com.roki.fashionfeed.domain.likefull.LikeType;
import com.roki.fashionfeed.domain.likefull.Likefull;
import com.roki.fashionfeed.domain.likefull.LikefullRepository;
import com.roki.fashionfeed.domain.share.Share;
import com.roki.fashionfeed.domain.share.ShareRepository;
import com.roki.fashionfeed.domain.share.ShareStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class FeedRepositoryTest {

    @Autowired
    FeedRepository feedRepository;

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    LikefullRepository likefullRepository;

    @Autowired
    ShareRepository shareRepository;

    @AfterEach
    void tearDown() {
        feedRepository.deleteAll();
        chatRepository.deleteAll();
        likefullRepository.deleteAll();
        shareRepository.deleteAll();
    }

    @Test
    public void Feed를_저장하다() {
        String feedTitle = "feedTitle";
        String feedContent = "feedText";
        String feedImage = "feedImage";
        Feed feed = Feed.builder()
                .feedTitle(feedTitle)
                .feedContent(feedContent)
                .feedImage(feedImage)
                .build();
        feedRepository.save(feed);

        Feed findFeed = feedRepository.findAll().get(0);
        assertThat(feedTitle).isEqualTo(findFeed.getFeedTitle());
        assertThat(feedContent).isEqualTo(findFeed.getFeedContent());
        assertThat(feedImage).isEqualTo(findFeed.getFeedImage());
        System.out.println("feed id는 " + feed.getId());
    }

    @Test
    @Transactional
    public void Feed를_상세_조회한다() {
        String feedTitle = "feedTitle";
        String feedContent = "feedText";
        String feedImage = "feedImage";
        Feed feed = Feed.builder()
                .feedTitle(feedTitle)
                .feedContent(feedContent)
                .feedImage(feedImage)
                .build();

        Long userId = 1L;
        String feedChatContent = "feedContent";
        Chat chat = Chat.builder()
                .userId(userId)
                .content(feedChatContent)
                .build();

        ShareStatus shareStatus = ShareStatus.UNSHARE;
        Share share = Share.builder()
                .userId(userId)
                .shareStatus(shareStatus)
                .build();

        LikeType likeType = LikeType.UNLIKE;
        Likefull likefull = Likefull.builder()
                .userId(userId)
                .likeType(likeType)
                .build();

        likefull.setFeed(feed);
        chat.setFeed(feed);
        share.setFeed(feed);
        feedRepository.save(feed);

        Feed findFeed = feedRepository.findAll().get(0);
        assertThat(feedTitle).isEqualTo(findFeed.getFeedTitle());
        assertThat(feedContent).isEqualTo(findFeed.getFeedContent());
        assertThat(feedImage).isEqualTo(findFeed.getFeedImage());
        assertThat(userId).isEqualTo(findFeed.getLikefulls().get(0).getUserId());
        assertThat(likeType).isEqualTo(findFeed.getLikefulls().get(0).getLikeType());
        assertThat(userId).isEqualTo(findFeed.getChats().get(0).getUserId());
        assertThat(feedChatContent).isEqualTo(findFeed.getChats().get(0).getContent());
        assertThat(userId).isEqualTo(findFeed.getShares().get(0).getUserId());
        assertThat(shareStatus).isEqualTo(findFeed.getShares().get(0).getShareStatus());

    }

    /**
     * JPA가 제공하는 기능들에 대해서는 경험적인 데이터가 충분하기 때문에 테스트 할 필요가 없으나
     * 페이징 처리를 제대로 써보는 것은 처음이라 공부하면서 테스트 진행.
     */
    @Test
    void Feed_Page를_조회한다() {
        int totalElements = 7;
        saveNumberOfFeed(totalElements); //Feed 7개 저장

        int pageNo = 0;
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        Page<Feed> result = feedRepository.findAll(pageable);

        int totalPage = (totalElements / pageSize) + 1;

        assertThat(totalElements).isEqualTo(result.getTotalElements());
        assertThat(pageSize).isEqualTo(result.getSize());
        assertThat(totalPage).isEqualTo(result.getTotalPages());

        System.out.println("*********************************************************************");
        System.out.println("PAGE SIZE: " + result.getSize());
        System.out.println("TOTAL PAGE: " + result.getTotalPages());
        System.out.println("TOTAL COUNT: " + result.getTotalElements());
        System.out.println("NEXT: " + result.nextPageable());
        System.out.println(result.nextPageable().isPaged());
        System.out.println(result.nextPageable().isUnpaged()); //다음 페이지가 없다면 이걸로 파악해야함 !
        System.out.println("*********************************************************************");
    }

    // Feed를 number개 생성한다. (필요하다면 테스트 시작전에 개별적으로 실행시킬 것)
    private void saveNumberOfFeed(int number) {
        IntStream.rangeClosed(1, number)
                .forEach(n -> {
                    Long userId = Long.parseLong(String.valueOf(n));
                    Chat chat = Chat.builder()
                            .userId(userId)
                            .content("chatContent" + n)
                            .build();

                    Share share = Share.builder()
                            .userId(userId)
                            .shareStatus(ShareStatus.UNSHARE)
                            .build();

                    Likefull likefull = Likefull.builder()
                            .userId(userId)
                            .likeType(LikeType.UNLIKE)
                            .build();

                    Feed feed = Feed.builder()
                            .feedTitle("feedTitle" + n)
                            .feedContent("feedContent" + n)
                            .feedImage("feedImage" + n)
                            .build();

                    chat.setFeed(feed);
                    share.setFeed(feed);
                    likefull.setFeed(feed);
                    feedRepository.save(feed);
                });
    }
}
