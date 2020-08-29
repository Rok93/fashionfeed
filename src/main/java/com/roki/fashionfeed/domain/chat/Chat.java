package com.roki.fashionfeed.domain.chat;

import com.roki.fashionfeed.domain.BaseTimeEntity;
import com.roki.fashionfeed.domain.feed.Feed;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Chat extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userEmail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id")
    private Feed feed;

    @Column(length = 100, nullable = false)
    private String content;

    @Builder
    public Chat(String content, String userEmail) {
        this.content = content;
        this.userEmail = userEmail;
    }

    public void setFeed(Feed feed) {
        if (this.feed != null) {
            this.feed.getChats().remove(this);
        }
        this.feed = feed;
        feed.getChats().add(this);
    }

    public void setContent(String content) {
        this.content = content;
    }
}
