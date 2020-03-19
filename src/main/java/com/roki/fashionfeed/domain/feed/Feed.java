package com.roki.fashionfeed.domain.feed;

import com.roki.fashionfeed.domain.BaseTimeEntity;
import com.roki.fashionfeed.domain.chat.Chat;
import com.roki.fashionfeed.domain.likefull.LikeType;
import com.roki.fashionfeed.domain.likefull.Likefull;
import com.roki.fashionfeed.domain.share.Share;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Feed extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //feedTag (List로 넣을 수 있나..?)

    @Column(length = 255, nullable = false)
    private String feedText;

    @Column(nullable = false)
    private String feedImage;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Chat> chats = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "likefull_id")
    private Likefull likefull; //좋아요 개수를 파악하는 방법 Linkfull 모두 조회해서 카운트하기?

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "share_id")
    private Share share;

    @Builder
    public Feed(String feedText, String feedImage, Chat feedChat, Likefull feedLikefull
            , Share feedShare) {
        this.feedText = feedText;
        this.feedImage = feedImage;
        addChats(feedChat);
        this.likefull = feedLikefull;
        this.share = feedShare;
    }

    public void addChats(Chat feedChat) {
        chats.add(feedChat);
        feedChat.setFeed(this);
    }

    //    private void setFeedLikefull(Likefull feedLikefull) {
//        this.likefull = feedLikefull;
//        feedLikefull.setFeed(this);
//    }
//
//    private void setFeedShare(Share share) {
//        this.share = share;
//        share.setFeed(this);
//    }
}

