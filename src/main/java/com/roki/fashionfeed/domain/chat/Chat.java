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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "feed_id")
    private Feed feed;

    @Column(length = 100, nullable = false)
    private String content;

    @Builder
    public Chat(String content) {
        this.content = content;
    }

    public void setFeed(Feed feed) {
        if (this.feed != null) {
            this.feed.getChats().remove(this);
        }
        this.feed = feed;
        feed.getChats().add(this);
    }
}
